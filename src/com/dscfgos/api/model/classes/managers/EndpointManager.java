package com.dscfgos.api.model.classes.managers;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dscfgos.api.model.classes.managers.rate.RateLimitException;
import com.dscfgos.api.model.classes.managers.request.AsyncRequest;
import com.dscfgos.api.model.classes.managers.request.Request;
import com.dscfgos.api.model.classes.managers.request.RequestListener;

public class EndpointManager {
	private final ApiConfig config;
	private final AsyncRequestPool pool;
	private final Collection<RequestListener> listeners = new CopyOnWriteArrayList<RequestListener>();

	EndpointManager(ApiConfig config) {
		this.config = config;
		pool = new AsyncRequestPool(config);
	}

	void addListeners(RequestListener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	void awaitAll() throws InterruptedException {
		pool.awaitAll();
	}

	void callMethod(ApiMethod method) throws RateLimitException, RiotApiException {
		new Request(config, method);
	}

	<T> T callMethodAndReturnDto(ApiMethod method) throws RateLimitException, RiotApiException {
		Request request = new Request(config, method);
		return request.getDto();
	}

	AsyncRequest callMethodAsynchronously(ApiMethod method) {
		AsyncRequest request = new AsyncRequest(config, method);
		request.addListeners(listeners.toArray(new RequestListener[listeners.size()]));
		pool.add(request);
		return request;
	}

	int getPoolSize() {
		return pool.getPoolSize();
	}

	int getQueueSize() {
		return pool.getQueueSize();
	}

	void removeListeners(RequestListener... listeners) {
		this.listeners.removeAll(Arrays.asList(listeners));
	}
}
