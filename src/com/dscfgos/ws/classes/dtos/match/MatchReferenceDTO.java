package com.dscfgos.ws.classes.dtos.match;

import com.dscfgos.api.model.dtos.v3.match.Match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class MatchReferenceDTO
{
	private String lane	;			// - Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM
	private long gameId ;
	private Match	game ;
	private long champion ;	
	private String platformId ;	
	private int season;			
	private String queue;			// - Legal values: RANKED_FLEX_SR, RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5, TEAM_BUILDER_DRAFT_RANKED_5x5, TEAM_BUILDER_RANKED_SOLO
	private String role	;			// - Legal values: DUO, NONE, SOLO, DUO_CARRY, DUO_SUPPORT
	private long timestamp ;
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public long getChampion() {
		return champion;
	}
	public void setChampion(long champion) {
		this.champion = champion;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public Match getGame() {
		return game;
	}
	public void setGame(Match game) {
		this.game = game;
	}

	
	   
}
