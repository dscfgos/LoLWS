package com.dscfgos.api.model.dtos.v3.match;

/**
 * @author dscfgos
 * @version 2.0
 * @category MATCH-V3
 */
public class Player
{
	private String 	currentPlatformId ;	
	private String	summonerName;		// - Summoner name
	private String	matchHistoryUri;	// - Match history URI
	private String 	platformId ;	
	private long  	currentAccountId ;
	private int 	profileIcon;		// - Profile icon ID 
	private long   	summonerId;			// - Summoner ID
	private long 	accountId	;
	
	public String getCurrentPlatformId() {
		return currentPlatformId;
	}
	public void setCurrentPlatformId(String currentPlatformId) {
		this.currentPlatformId = currentPlatformId;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public String getMatchHistoryUri() {
		return matchHistoryUri;
	}
	public void setMatchHistoryUri(String matchHistoryUri) {
		this.matchHistoryUri = matchHistoryUri;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public long getCurrentAccountId() {
		return currentAccountId;
	}
	public void setCurrentAccountId(long currentAccountId) {
		this.currentAccountId = currentAccountId;
	}
	public int getProfileIcon() {
		return profileIcon;
	}
	public void setProfileIcon(int profileIcon) {
		this.profileIcon = profileIcon;
	}
	public long getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(long summonerId) {
		this.summonerId = summonerId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}
