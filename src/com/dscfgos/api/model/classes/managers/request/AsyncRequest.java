package com.dscfgos.api.model.classes.managers.request;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.ApiMethod;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.classes.managers.RiotApiV3;

public class AsyncRequest extends Request implements Runnable 
{
	protected final Object signal = new Object();

	private Collection<RequestListener> listeners = new CopyOnWriteArrayList<RequestListener>();
	private Thread executionThread = null;
	private boolean sent = false;

	/**
	 * Constructs an asynchronous request
	 * 
	 * @param config
	 *            Configuration to use
	 * @param method
	 *            Api method to call
	 * @see ApiConfig
	 * @see ApiMethod
	 */
	public AsyncRequest(ApiConfig config, ApiMethod object) {
		super();
		init(config, object);
	}

	/**
	 * Adds one or more {@link RequestListener} to this request
	 * 
	 * @param listeners
	 *            One or more request listeners
	 * @see RequestListener
	 */
	public void addListeners(RequestListener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	/**
	 * Waits indefinitely until the request completes.
	 * <p>
	 * If the thread is interrupted while waiting for the request to complete, this method will throw an {@code InterruptedException} and
	 * the thread's interrupt flag will be cleared.
	 * </p>
	 * <p>
	 * <i>Please note that this method is blocking and thus negates the advantage of the asynchronous nature of this class. Consider using a
	 * {@link RequestListener} instead.</i>
	 * </p>
	 * 
	 * @throws InterruptedException
	 *             If the method is interrupted by calling {@link Thread#interrupt()}. The interrupt flag will be cleared
	 */
	public void await() throws InterruptedException {
		if (!isDone()) {
			synchronized (signal) {
				while (!isDone()) {
					signal.wait();
				}
			}
		}
	}

	/**
	 * Waits for at most the given time until the request completes.
	 * <p>
	 * If the thread is interrupted while waiting for the request to complete, this method will throw an {@code InterruptedException} and
	 * the thread's interrupt flag will be cleared.
	 * </p>
	 * <p>
	 * <i>Please note that this method is blocking and thus negates the advantage of the asynchronous nature of this class. Consider using a
	 * {@link RequestListener} instead.</i>
	 * </p>
	 *
	 * @param timeout
	 *            The maximum amount of the given time unit to wait
	 * @param unit
	 *            The time unit of the {@code timeout} argument
	 * @throws InterruptedException
	 *             If the method is interrupted by calling {@link Thread#interrupt()}. The interrupt flag will be cleared
	 * @throws TimeoutException
	 *             If the given time elapsed without the request completing
	 */
	public void await(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
		await(timeout, unit, false);
	}

	/**
	 * Waits for at most the given time until the request completes.
	 * <p>
	 * If the thread is interrupted while waiting for the request to complete, this method will throw an {@code InterruptedException} and
	 * the thread's interrupt flag will be cleared.
	 * </p>
	 * <p>
	 * <i>Please note that this method is blocking and thus negates the advantage of the asynchronous nature of this class. Consider using a
	 * {@link RequestListener} instead.</i>
	 * </p>
	 *
	 * @param timeout
	 *            The maximum amount of the given time unit to wait
	 * @param unit
	 *            The time unit of the {@code timeout} argument
	 * @param cancelOnTimeout
	 *            Whether or not the request should be cancelled, if the given {@code timeout} is elapsed without the request completing
	 * @throws InterruptedException
	 *             If the method is interrupted by calling {@link Thread#interrupt()}. The interrupt flag will be cleared
	 * @throws TimeoutException
	 *             If the given time elapsed without the request completing
	 */
	public void await(long timeout, TimeUnit unit, boolean cancelOnTimeout) throws InterruptedException, TimeoutException {
		final long end = System.currentTimeMillis() + unit.toMillis(timeout);
		if (!isDone() && System.currentTimeMillis() < end) {
			synchronized (signal) {
				while (!isDone() && System.currentTimeMillis() < end) {
					signal.wait(end - System.currentTimeMillis());
				}
			}
		}
		if (!isDone()) {
			if (cancelOnTimeout) {
				cancel();
			}
			throw new TimeoutException();
		}
	}

	@Override
	public boolean cancel() {
		synchronized (signal) {
			boolean cancelled = super.cancel();
			if (!cancelled) {
				return false;
			}
			signal.notifyAll();
			// Try to force-quit the connection
			if (connection != null) {
				setTimeout(1);
				connection.disconnect();
			}
		}
		return true;
	}

	@Override
	public synchronized void execute() {
		if (isSent()) {
			return;
		}
		sent = true;
		executionThread = new Thread(this);
		executionThread.setName("AsyncRequest - " + object);
		executionThread.start();
	}

	/**
	 * Retrieves the request's result. If an exception would be thrown, it is swallowed, since you should only call this method, if the
	 * request succeeded.
	 * 
	 * <p>
	 * If you want this method to throw exceptions, please use {@link #getDtoAndThrowException()} instead.
	 * </p>
	 * 
	 * @return The object returned by the api call, or {@code null} if the request did not finish yet
	 */
	@Override
	public <T> T getDto() {
		try {
			return super.getDto(true);
		} catch (RiotApiException e) {
			RiotApiV3.log.log(Level.FINE, "Retrieving Dto Failed", e);
		}
		return null;
	}

	/**
	 * Retrieves the request's result. If an exception occures, it is thrown.
	 * 
	 * <p>
	 * If you do not want this method to throw exceptions, please use {@link #getDto()} instead.
	 * </p>
	 * 
	 * @return The object returned by the api call, or {@code null} if the request did not finish yet
	 * @throws RiotApiException
	 *             If an exception occurs while parsing the Riot Api's response
	 */
	public <T> T getDtoAndThrowException() throws RiotApiException {
		return super.getDto(true);
	}

	/**
	 * Returns {@code true} if this request has started execution
	 * 
	 * @return {@code true} if this request has started execution
	 */
	public boolean isSent() {
		return sent;
	}

	/**
	 * Notifies the listeners about the given {@code state}.
	 * 
	 * @param state
	 *            The state to notify the listeners about
	 */
	protected synchronized void notifyListeners(RequestState state) {
		for (RequestListener listener : listeners) {
			if (state == RequestState.Succeeded) {
				listener.onRequestSucceeded(this);
			} else if (state == RequestState.Failed) {
				listener.onRequestFailed(getException());
			} else if (state == RequestState.Timeout) {
				listener.onRequestTimeout(this);
			}
		}
	}

	/**
	 * Removes all {@link RequestListener} from this request
	 * 
	 * @see RequestListener
	 */
	public void removeAllListeners() {
		listeners.clear();
	}

	/**
	 * Removes one or more {@link RequestListener} from this request
	 * 
	 * @param listener
	 *            One or more listeners to remove
	 * @see RequestListener
	 */
	public void removeListener(RequestListener listeners) {
		this.listeners.removeAll(Arrays.asList(listeners));
	}

	@Override
	public void run() {
		try {
			super.execute();
		} catch (RiotApiException e) {
			setException(e);
		}
	}

	@Override
	protected boolean setState(RequestState state) {
		if (isDone()) {
			return false;
		}
		notifyListeners(state);
		super.setState(state);
		if (isDone()) {
			synchronized (signal) {
				signal.notifyAll();
			}
		}
		return true;
	}

	@Override
	protected void setTimeout() {
		setTimeout(config.getAsyncRequestTimeout());
	}

}
