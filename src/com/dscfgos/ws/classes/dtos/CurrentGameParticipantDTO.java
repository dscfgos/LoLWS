package com.dscfgos.ws.classes.dtos;

import java.util.List;

import com.dscfgos.api.model.dtos.current_game.Mastery;
import com.dscfgos.api.model.dtos.current_game.Rune;
import com.dscfgos.api.model.dtos.static_data.Champion;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;

/**
 * @author dscfgos
 * @version 1.0
 * @category current-game-v1.0
 */
public class CurrentGameParticipantDTO 
{
	private boolean bot ; 				// - Flag indicating whether or not this participant is a bot
	private long championId ; 			// - The ID of the champion played by this participant
	private Champion champion ; 		// - The champion played by this participant
	private List<Mastery> masteries ; 	// - The masteries used by this participant
	private long profileIconId ; 		// - The ID of the profile icon used by this participant
	private List<Rune> runes ;		 	// - The runes used by this participant
	private SummonerSpell spell1 ; 		// - The first summoner spell used by this participant
	private SummonerSpell spell2 ; 		// - The second summoner spell used by this participant
	private long summonerId ; 			// - The summoner ID of this participant
	private String summonerName ; 		// - The summoner name of this participant
	private long teamId ; 				// - The team ID of this participant, indicating the participant's team
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public long getChampionId() {
		return championId;
	}
	public void setChampionId(long championId) {
		this.championId = championId;
	}
	public List<Mastery> getMasteries() {
		return masteries;
	}
	public void setMasteries(List<Mastery> masteries) {
		this.masteries = masteries;
	}
	public long getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(long profileIconId) {
		this.profileIconId = profileIconId;
	}
	public List<Rune> getRunes() {
		return runes;
	}
	public void setRunes(List<Rune> runes) {
		this.runes = runes;
	}
	public SummonerSpell getSpell1() {
		return spell1;
	}
	public void setSpell1(SummonerSpell spell1) {
		this.spell1 = spell1;
	}
	public SummonerSpell getSpell2() {
		return spell2;
	}
	public void setSpell2(SummonerSpell spell2) {
		this.spell2 = spell2;
	}
	public long getSummonerId() {
		return summonerId;
	}
	public void setSummonerId(long summonerId) {
		this.summonerId = summonerId;
	}
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public Champion getChampion() {
		return champion;
	}
	public void setChampion(Champion champion) {
		this.champion = champion;
	}
	
	
}
