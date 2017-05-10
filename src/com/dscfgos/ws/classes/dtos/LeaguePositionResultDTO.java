package com.dscfgos.ws.classes.dtos;

import java.util.List;

import com.dscfgos.api.model.dtos.v3.league.LeaguePosition;

public class LeaguePositionResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private List<LeaguePosition> leaguePosition = null;

	public List<LeaguePosition> getLeaguePosition() {
		return leaguePosition;
	}

	public void setLeaguePosition(List<LeaguePosition> leaguePosition) {
		this.leaguePosition = leaguePosition;
	}
	
}
