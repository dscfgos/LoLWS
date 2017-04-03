package com.dscfgos.ws.classes.constants;

import java.awt.Dimension;

public enum ProfilesImagesSizes 
{
	XSMALL(new Dimension(16, 16)), 
	SMALL(new Dimension(32, 32)), 
	MEDIUM(new Dimension(64, 64)) , 
	LARGE(new Dimension(128, 128));
	
	private Dimension value = null;
	private ProfilesImagesSizes(Dimension value) 
	{
		
		this.value = value;
	}
	
	public Dimension getValue()
	{
		return value;
	}
	
}
