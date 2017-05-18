package com.dscfgos.api.model.dtos.v3.spectator;

/**
 * @author dscfgos
 * @version 2.0
 * @category SPECTATOR-V3
 */
public class Mastery 
{
	private long masteryId ; 	// - The ID of the mastery
	private int rank ; 			// - The number of points put into this mastery by the user

	public long getMasteryId() {
		return masteryId;
	}
	public void setMasteryId(long masteryId) {
		this.masteryId = masteryId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
