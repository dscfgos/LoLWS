package com.dscfgos.api.model.dtos.v3.spectator;

/**
 * @author dscfgos
 * @version 2.0
 * @category SPECTATOR-V3
 */
public class BannedChampion 
{
	private long championId ; 	// - The ID of the banned champion
	private int pickTurn ; 		// - The turn during which the champion was banned
	private long teamId ; 		// - The ID of the team that banned the champion

	public long getChampionId() {
		return championId;
	}
	public void setChampionId(long championId) {
		this.championId = championId;
	}
	public int getPickTurn() {
		return pickTurn;
	}
	public void setPickTurn(int pickTurn) {
		this.pickTurn = pickTurn;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
}
