package com.dscfgos.ws.classes.dtos.match;

import com.dscfgos.ws.classes.dtos.BaseResultDTO;

public class RecentGamesResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private MatchlistDTO matchlist = null;

	public MatchlistDTO getMatchlist() {
		return matchlist;
	}

	public void setMatchlist(MatchlistDTO matchlist) {
		this.matchlist = matchlist;
	}

}
