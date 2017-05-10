package com.dscfgos.api.model.dtos.v3.league;

/**
 * @author dscfgos
 * @version 2.0
 * @category league-v3
 */
public class MiniSeries 
{

	private int 	wins	 ;	
	private int 	losses	 ;	
	private int 	target	 ;	
	private String 	progress ;

	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
}
