package com.dscfgos.ws.classes.dtos;

import com.dscfgos.api.model.dtos.game.RecentGames;

public class RecentGamesResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private RecentGames recentGames = null;
	public RecentGames getRecentGames() {
		return recentGames;
	}

	public void setRecentGames(RecentGames recentGames) {
		this.recentGames = recentGames;
	}
}
