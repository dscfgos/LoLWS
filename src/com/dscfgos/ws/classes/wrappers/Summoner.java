package com.dscfgos.ws.classes.wrappers;

/**
 * @author dscfgos
 */
public class Summoner 
{
	
	private long   id;					// - Summoner ID.
	private String name;				// - Summoner name.
	private int    profileIconId;		// - ID of the summoner icon associated with the summoner.
	private long   revisionDate;		// - Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
	private long   summonerLevel;		// - Summoner level associated with the summoner.
	private long   shardid;				// - Shard ID.
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(int profileIconId) {
		this.profileIconId = profileIconId;
	}
	public long getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}
	public long getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getShardid() {
		return shardid;
	}
	public void setShardid(long shardid) {
		this.shardid = shardid;
	}
	
	
	

	
}
