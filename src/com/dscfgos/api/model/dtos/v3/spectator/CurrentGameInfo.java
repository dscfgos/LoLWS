package com.dscfgos.api.model.dtos.v3.spectator;

import java.util.List;


/**
 * @author dscfgos
 * @version 2.0
 * @category SPECTATOR-V3
 */
public class CurrentGameInfo 
{
	private long gameId ; 								// - The ID of the game
	private long gameStartTime ; 						// - The game start time represented in epoch milliseconds
	private String platformId ; 						// - The ID of the platform on which the game is being played
	private String gameMode ; 							// - The game mode (Legal values: CLASSIC, ODIN, ARAM, TUTORIAL, ONEFORALL, ASCENSION, FIRSTBLOOD, KINGPORO)
	private long mapId ; 								// - The ID of the map
	private String gameType ; 							// - The game type (Legal values: CUSTOM_GAME, MATCHED_GAME, TUTORIAL_GAME)
	private List<BannedChampion> bannedChampions ; 		// - Banned champion information
	private Observer observers ; 						// - The observer information
	private List<CurrentGameParticipant> participants ; // - The participant information
	private long gameLength ; 							// - The amount of time in seconds that has passed since the game started
	private long gameQueueConfigId ; 					// - The queue type (queue types are documented on the Game Constants page)
	
	
	public List<BannedChampion> getBannedChampions() {
		return bannedChampions;
	}
	public void setBannedChampions(List<BannedChampion> bannedChampions) {
		this.bannedChampions = bannedChampions;
	}
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public long getGameLength() {
		return gameLength;
	}
	public void setGameLength(long gameLength) {
		this.gameLength = gameLength;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMod(String gameMode) {
		this.gameMode = gameMode;
	}
	public long getGameQueueConfigId() {
		return gameQueueConfigId;
	}
	public void setGameQueueConfigId(long gameQueueConfigId) {
		this.gameQueueConfigId = gameQueueConfigId;
	}
	public long getGameStartTime() {
		return gameStartTime;
	}
	public void setGameStartTime(long gameStartTime) {
		this.gameStartTime = gameStartTime;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public long getMapId() {
		return mapId;
	}
	public void setMapId(long mapId) {
		this.mapId = mapId;
	}
	public Observer getObservers() {
		return observers;
	}
	public void setObservers(Observer observers) {
		this.observers = observers;
	}
	public List<CurrentGameParticipant> getParticipants() {
		return participants;
	}
	public void setParticipants(List<CurrentGameParticipant> participants) {
		this.participants = participants;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
}
