package com.dscfgos.api.model.dtos.v3.match;

import java.util.Map;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class ParticipantTimeline 
{
	private String lane ;	
	private int participantId ;	
	private Map<String, Double> csDiffPerMinDeltas ;	
	private Map<String, Double> goldPerMinDeltas ;	
	private Map<String, Double> xpDiffPerMinDeltas ;	
	private Map<String, Double> creepsPerMinDeltas ;	
	private Map<String, Double> xpPerMinDeltas ;	
	private String role	;	
	private Map<String, Double> damageTakenDiffPerMinDeltas ;	
	private Map<String, Double> damageTakenPerMinDeltas ;
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public Map<String, Double> getCsDiffPerMinDeltas() {
		return csDiffPerMinDeltas;
	}
	public void setCsDiffPerMinDeltas(Map<String, Double> csDiffPerMinDeltas) {
		this.csDiffPerMinDeltas = csDiffPerMinDeltas;
	}
	public Map<String, Double> getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}
	public void setGoldPerMinDeltas(Map<String, Double> goldPerMinDeltas) {
		this.goldPerMinDeltas = goldPerMinDeltas;
	}
	public Map<String, Double> getXpDiffPerMinDeltas() {
		return xpDiffPerMinDeltas;
	}
	public void setXpDiffPerMinDeltas(Map<String, Double> xpDiffPerMinDeltas) {
		this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
	}
	public Map<String, Double> getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}
	public void setCreepsPerMinDeltas(Map<String, Double> creepsPerMinDeltas) {
		this.creepsPerMinDeltas = creepsPerMinDeltas;
	}
	public Map<String, Double> getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}
	public void setXpPerMinDeltas(Map<String, Double> xpPerMinDeltas) {
		this.xpPerMinDeltas = xpPerMinDeltas;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
		return damageTakenDiffPerMinDeltas;
	}
	public void setDamageTakenDiffPerMinDeltas(Map<String, Double> damageTakenDiffPerMinDeltas) {
		this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
	}
	public Map<String, Double> getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}
	public void setDamageTakenPerMinDeltas(Map<String, Double> damageTakenPerMinDeltas) {
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
	}
	
	
}
