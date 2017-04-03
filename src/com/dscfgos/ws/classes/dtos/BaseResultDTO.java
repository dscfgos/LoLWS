package com.dscfgos.ws.classes.dtos;

import java.io.Serializable;

public class BaseResultDTO implements Serializable 
{
	private static final long serialVersionUID = -5063185318268069815L;
	
	private String resultCode = "0";
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
