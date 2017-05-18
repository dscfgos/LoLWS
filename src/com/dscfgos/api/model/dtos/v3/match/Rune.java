package com.dscfgos.api.model.dtos.v3.match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class Rune
{
	private long   rank;	// - Rune rank
	private long   runeId;	// - Rune ID
	
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	public long getRuneId() {
		return runeId;
	}
	public void setRuneId(long runeId) {
		this.runeId = runeId;
	}
	
    
}
