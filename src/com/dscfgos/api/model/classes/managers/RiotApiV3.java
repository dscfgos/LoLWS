package com.dscfgos.api.model.classes.managers;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.RuneListData;
import com.dscfgos.api.model.constants.Season;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.champion_mastery.ChampionMastery;
import com.dscfgos.api.model.dtos.current_game.CurrentGameInfo;
import com.dscfgos.api.model.dtos.game.RecentGames;
import com.dscfgos.api.model.dtos.static_data.Realm;
import com.dscfgos.api.model.dtos.static_data.RuneList;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;
import com.dscfgos.api.model.dtos.stats.RankedStats;
import com.dscfgos.api.model.dtos.status.Shard;
import com.dscfgos.api.model.dtos.status.ShardStatus;
import com.dscfgos.api.model.dtos.v3.league.LeaguePosition;
import com.dscfgos.api.model.dtos.v3.static_data.Champion;
import com.dscfgos.api.model.dtos.v3.summoner.Summoner;
import com.dscfgos.api.model.endpoints.methods.championmastery.GetChampionMastery;
import com.dscfgos.api.model.endpoints.methods.current_game.GetCurrentGameInfo;
import com.dscfgos.api.model.endpoints.methods.game.GetRecentGames;
import com.dscfgos.api.model.endpoints.methods.static_data.GetDataRealm;
import com.dscfgos.api.model.endpoints.methods.static_data.GetDataRuneList;
import com.dscfgos.api.model.endpoints.methods.static_data.GetDataSummonerSpell;
import com.dscfgos.api.model.endpoints.methods.stats.GetRankedStats;
import com.dscfgos.api.model.endpoints.methods.status.GetShardStatus;
import com.dscfgos.api.model.endpoints.methods.status.GetShards;
import com.dscfgos.api.model.endpoints.methods.v3.league.GetLeaguePositionBySummonerId;
import com.dscfgos.api.model.endpoints.methods.v3.static_data.GetDataChampion;
import com.dscfgos.api.model.endpoints.methods.v3.static_data.GetDataLanguages;
import com.dscfgos.api.model.endpoints.methods.v3.summoner.GetSummonerByAccountId;
import com.dscfgos.api.model.endpoints.methods.v3.summoner.GetSummonerBySummonerId;
import com.dscfgos.api.model.endpoints.methods.v3.summoner.GetSummonerBySummonerName;

public class RiotApiV3 implements Cloneable
{
	public static final Logger log = Logger.getLogger(RiotApiV3.class.getName());

	private final ApiConfig config;
	private final EndpointManager endpointManager;
	/**
	 * Constructs a RiotApi object with default configuration. Please note that the default configuration does not contain an api key, and
	 * thus cannot be used for most endpoints.
	 */
	public RiotApiV3() {
		this(new ApiConfig());
	}

	/**
	 * Constructs a RiotApi object with the given configuration.
	 * 
	 * @param config
	 *            Configuration to use for this RiotApi object
	 * @see ApiConfig
	 */
	public RiotApiV3(ApiConfig config) {
		this.config = config;
		log.setUseParentHandlers(false);
		log.addHandler(new LogHandler(config.getDebugToFile()));
		log.setLevel(config.getDebugLevel());
		endpointManager = new EndpointManager(config);
	}

	@Override
	public RiotApiV3 clone() {
		return new RiotApiV3(config.clone());
	}


	/**
	 * Get the configuration
	 * 
	 * @return {@link ApiConfig} object
	 * @see ApiConfig
	 */
	protected ApiConfig getConfig() {
		return config;
	}
	
	
	//********************************************//
	// 	SUMMONER-V3									  
	//********************************************//
	public Summoner getSummonerById(Region region, String summonerId) throws RiotApiException 
	{
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetSummonerBySummonerId(getConfig(), region, summonerId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public Summoner getSummonerByAccountId(Region region, String accountId) throws RiotApiException 
	{
		Objects.requireNonNull(region);
		Objects.requireNonNull(accountId);
		ApiMethod method = new GetSummonerByAccountId(getConfig(), region, accountId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public Summoner getSummonerByName(Region region, String summonerName) throws RiotApiException 
	{
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerName);
		ApiMethod method = new GetSummonerBySummonerName(getConfig(), region, summonerName);
		return endpointManager.callMethodAndReturnDto(method);
	}
	
	//********************************************//
	// 	LEAGUE-V3										  
	//********************************************//
	public Set<LeaguePosition> getLeaguePositionBySummonerId(Region region, String summonerId) throws RiotApiException {
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetLeaguePositionBySummonerId(getConfig(), region, summonerId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	
	//********************************************//
	// 	STATIC-DATA-V3										  
	//********************************************//
	public List<String> getDataLanguages(Region region) throws RiotApiException 
	{
		Objects.requireNonNull(region);
		ApiMethod method = new GetDataLanguages(getConfig(), region);
		return endpointManager.callMethodAndReturnDto(method);
	}
	
	public Champion getDataChampion(Region region, int id, Locale locale, String version, ChampData... champData)
			throws RiotApiException {
		Objects.requireNonNull(region);
		ApiMethod method = new GetDataChampion(getConfig(), region, id, locale, version, champData);
		return endpointManager.callMethodAndReturnDto(method);
	}
	
	//TODO 
	
	
	
	public ChampionMastery getChampionMastery(PlatformId platformId, long summonerId, long championId) throws RiotApiException {
		Objects.requireNonNull(platformId);
		ApiMethod method = new GetChampionMastery(getConfig(), platformId, summonerId, championId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public CurrentGameInfo getCurrentGameInfo(PlatformId platformId, long summonerId) throws RiotApiException {
		Objects.requireNonNull(platformId);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetCurrentGameInfo(getConfig(), platformId, summonerId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public Realm getDataRealm(Region region) throws RiotApiException {
		Objects.requireNonNull(region);
		ApiMethod method = new GetDataRealm(getConfig(), region);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public RuneList getDataRuneList(Region region, Locale locale, String version, RuneListData... runeListData) throws RiotApiException {
		Objects.requireNonNull(region);
		ApiMethod method = new GetDataRuneList(getConfig(), region, locale, version, runeListData);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public RuneList getDataRuneList(Region region) throws RiotApiException {
		Objects.requireNonNull(region);
		return getDataRuneList(region, null, null, (RuneListData) null);
	}

	public SummonerSpell getDataSummonerSpell(Region region, int id, Locale locale, String version, SpellData... spellData) throws RiotApiException {
		Objects.requireNonNull(region);
		ApiMethod method = new GetDataSummonerSpell(getConfig(), region, id, locale, version, spellData);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public SummonerSpell getDataSummonerSpell(Region region, int id) throws RiotApiException {
		Objects.requireNonNull(region);
		return getDataSummonerSpell(region, id, null, null, (SpellData) null);
	}



	public RankedStats getRankedStats(Region region, Season season, long summonerId) throws RiotApiException {
		Objects.requireNonNull(region);
		ApiMethod method = new GetRankedStats(getConfig(), region, season, summonerId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public RankedStats getRankedStats(Region region, long summonerId) throws RiotApiException {
		Objects.requireNonNull(region);
		return getRankedStats(region, null, summonerId);
	}

	public RecentGames getRecentGames(Region region, long summonerId) throws RiotApiException {
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetRecentGames(getConfig(), region, summonerId);
		return endpointManager.callMethodAndReturnDto(method);
	}

	public List<Shard> getShards() throws RiotApiException {
		ApiMethod method = new GetShards(getConfig());
		return endpointManager.callMethodAndReturnDto(method);
	}

	public ShardStatus getShardStatus(Region region) throws RiotApiException {
		ApiMethod method = new GetShardStatus(getConfig(), region);
		return endpointManager.callMethodAndReturnDto(method);
	}


}
