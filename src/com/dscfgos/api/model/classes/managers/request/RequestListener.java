package com.dscfgos.api.model.classes.managers.request;

import com.dscfgos.api.model.classes.managers.RiotApiException;

public interface RequestListener 
{
	public void onRequestFailed(RiotApiException e);
	public void onRequestSucceeded(AsyncRequest request);
	public void onRequestTimeout(AsyncRequest request);
}
