package com.dscfgos.api.model.dtos.v3.match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class MatchReference
{
	private String lane	;			// - Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM
	private long gameId ;	
	private long champion ;	
	private String platformId ;	
	private int season;			// - Legal values: PRESEASON3, SEASON3, PRESEASON2014, SEASON2014, PRESEASON2015, SEASON2015, PRESEASON2016, SEASON2016, PRESEASON2017, SEASON2017
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

	
	   
}
