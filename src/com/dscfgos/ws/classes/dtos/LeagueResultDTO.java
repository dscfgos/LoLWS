package com.dscfgos.ws.classes.dtos;

import java.util.List;
import java.util.Map;

import com.dscfgos.api.model.dtos.league.League;

public class LeagueResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private Map<String, List<League>> leagues = null;
	public Map<String, List<League>> getLeagues() {
		return leagues;
	}

	public void setLeagues(Map<String, List<League>> leagues) {
		this.leagues = leagues;
	}
}
