package com.dscfgos.ws.classes.dtos;

import com.dscfgos.api.model.dtos.static_data.Champion;

/**
 * @author dscfgos
 * @version 1.0
 * @category match-v2.2
 * <br />
 * This object contains information about banned champions
 */
public class BannedChampionDTO
{
	private int   championId;	// - Banned champion ID
	private Champion champion;
	private int   pickTurn;	// - Turn during which the champion was banned
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
	public int getPickTurn() {
		return pickTurn;
	}
	public void setPickTurn(int pickTurn) {
		this.pickTurn = pickTurn;
	}
	public Champion getChampion() {
		return champion;
	}
	public void setChampion(Champion champion) {
		this.champion = champion;
	}
	
    
}
