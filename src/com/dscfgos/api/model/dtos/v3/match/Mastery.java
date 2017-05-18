package com.dscfgos.api.model.dtos.v3.match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class Mastery
{
	private int    masteryId;	// - Mastery ID
    private int rank;			// - Mastery rank
	public int getMasteryId() {
		return masteryId;
	}
	public void setMasteryId(int masteryId) {
		this.masteryId = masteryId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
