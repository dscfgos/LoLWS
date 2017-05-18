package com.dscfgos.ws.classes.dtos.match;

import java.util.List;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class MatchlistDTO 
{
	private List<MatchReferenceDTO> matchesDTO ;	
	private int 	totalGames	;	
	private int 	startIndex	;
	private int 	endIndex	;
	
	public List<MatchReferenceDTO> getMatchesDTO() {
		return matchesDTO;
	}
	public void setMatchesDTO(List<MatchReferenceDTO> matches) {
		this.matchesDTO = matches;
	}
	public int getTotalGames() {
		return totalGames;
	}
	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
}
