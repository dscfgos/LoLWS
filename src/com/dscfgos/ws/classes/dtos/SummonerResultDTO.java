package com.dscfgos.ws.classes.dtos;

import com.dscfgos.ws.classes.wrappers.Summoner;

public class SummonerResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private Summoner summoner = null;
	public Summoner getSummoner() {
		return summoner;
	}

	public void setSummoner(Summoner summoner) {
		this.summoner = summoner;
	}
}
