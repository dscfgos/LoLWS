package com.dscfgos.ws.manager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.classes.managers.rate.RateLimitException;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.match.Match;
import com.dscfgos.api.model.dtos.v3.match.MatchReference;
import com.dscfgos.api.model.dtos.v3.match.Matchlist;
import com.dscfgos.api.model.dtos.v3.match.Participant;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.SummonerResultDTO;
import com.dscfgos.ws.classes.dtos.history.GamesHistoryResultDTO;
import com.dscfgos.ws.classes.dtos.history.HistoryGameDataDTO;
import com.dscfgos.ws.classes.dtos.match.MatchReferenceDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Champion;
import com.dscfgos.ws.classes.wrappers.History;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Summoner;
import com.dscfgos.ws.classes.wrappers.SummonerSpell;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;

public class GamesHistoryManager 
{
	public static GamesHistoryResultDTO getGamesHistoryByRegionAndAccount(int regionId, long accountId, Locale locale)
	{
		GamesHistoryResultDTO result = new GamesHistoryResultDTO();
		
		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			Region region = Region.getRegionByName(shard.getSlug());
			
			Matchlist recentGames = getRecentGamesByRegionAndAccountIDFromRiot(region, accountId);
			if(recentGames != null && recentGames.getMatches() != null)
			{
				if(recentGames.getMatches().size() > 0)
				{
					List<HistoryGameDataDTO> listHistoryGameDataDTO = new ArrayList<>();
					
					for (MatchReference game : recentGames.getMatches()) 
					{
						History historyData = getGameFromHistoryByRegionAndGameIdFromDB(region, game.getGameId());
						Champion champion = StaticDataManager.getBasicChampionById(game.getChampion(), region, locale, "");
						if(champion != null)
						{
							HistoryGameDataDTO historyGameDataDTO = new HistoryGameDataDTO();
							
							if(historyData == null)
							{
								SummonerResultDTO summonerResult = SummonerManager.getSummonerByRegionAndAccountId(regionId, accountId);
								
								MatchReferenceDTO matchRefDTO = getMatchByRegionAndGameIdFromRiot(region, game);
								
								historyData = getHistoyDataFromMatchReferenceData(accountId, matchRefDTO, champion, summonerResult.getSummoner(),regionId);
								historyGameDataDTO = getHistoryGameDataFromMatchReferenceData(accountId, matchRefDTO, champion, summonerResult.getSummoner(),regionId);
								
								BaseWrapperFactory<History> baseWrapper = new BaseWrapperFactory<>();
								baseWrapper.addItem(History.class, historyData);

							}
							else
							{
								historyGameDataDTO = getHistoryGameDataFromHistoryDBData(accountId, historyData, champion);
							}
							
							listHistoryGameDataDTO.add(historyGameDataDTO);
						}
					}
					
					result.setHistoryGameDataList(listHistoryGameDataDTO);
				}
		
			}
			else
			{
				result.setResultCode(ErrorsConstants.RG_001);
			}
		}

		return result;
	}

	private static History getHistoyDataFromMatchReferenceData(long accountId, MatchReferenceDTO matchRefDTO,Champion champion, Summoner summoner, int regionId) 
	{
		History history = new History();
		if(matchRefDTO.getGameId()==455418550)
		{
			System.out.println(matchRefDTO.getGameId());
		}
			
		history.setGameId(matchRefDTO.getGameId());
		history.setPlatformId(matchRefDTO.getPlatformId());
		history.setTimestamp(matchRefDTO.getTimestamp());
		history.setQueue(matchRefDTO.getQueue());
		history.setSeason(matchRefDTO.getSeason());
		history.setGameMode(matchRefDTO.getGame().getGameMode());
		history.setMapId(matchRefDTO.getGame().getMapId());
		history.setGameType(matchRefDTO.getGame().getGameType());

		if(matchRefDTO.getGame().getTeams() != null && matchRefDTO.getGame().getTeams().size()==2)
		{
			history.setTeam1Id(matchRefDTO.getGame().getTeams().get(0).getTeamId());
			history.setTeam1Win(matchRefDTO.getGame().getTeams().get(0).getWin());
		
			history.setTeam2Id(matchRefDTO.getGame().getTeams().get(1).getTeamId());
			history.setTeam2Win(matchRefDTO.getGame().getTeams().get(1).getWin());
			
			if(matchRefDTO.getGame().getTeams().get(0).getBans()!= null && matchRefDTO.getGame().getTeams().get(0).getBans().size()==3)
			{
				history.setChampionBan1Id(matchRefDTO.getGame().getTeams().get(0).getBans().get(0).getChampionId());
				history.setChampionBan3Id(matchRefDTO.getGame().getTeams().get(0).getBans().get(1).getChampionId());
				history.setChampionBan5Id(matchRefDTO.getGame().getTeams().get(0).getBans().get(2).getChampionId());
			}
			if(matchRefDTO.getGame().getTeams().get(1).getBans()!= null && matchRefDTO.getGame().getTeams().get(1).getBans().size()==3)
			{
				history.setChampionBan2Id(matchRefDTO.getGame().getTeams().get(1).getBans().get(0).getChampionId());
				history.setChampionBan4Id(matchRefDTO.getGame().getTeams().get(1).getBans().get(1).getChampionId());
				history.setChampionBan6Id(matchRefDTO.getGame().getTeams().get(1).getBans().get(2).getChampionId());
			}
		}
		
		
		history.setParticipant1Id(-1);
		history.setParticipant1AccountId(-1);
		history.setParticipant1ChampionId(matchRefDTO.getGame().getParticipants().get(0).getChampionId());
		history.setParticipant1Spell1Id(matchRefDTO.getGame().getParticipants().get(0).getSpell1Id());
		history.setParticipant1Spell2Id(matchRefDTO.getGame().getParticipants().get(0).getSpell2Id());
		
		SummonerSpell spell11 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(0).getSpell1Id());
		SummonerSpell spell12 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(0).getSpell2Id());
		history.setParticipant1Spell1Image((spell11!=null && spell11.getImage()!=null)?spell11.getImage():"");
		history.setParticipant1Spell2Image((spell12!=null && spell12.getImage()!=null)?spell12.getImage():"");
		history.setParticipant1Item0(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem0());
		history.setParticipant1Item1(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem1());
		history.setParticipant1Item2(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem2());
		history.setParticipant1Item3(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem3());
		history.setParticipant1Item4(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem4());
		history.setParticipant1Item5(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem5());
		history.setParticipant1Item6(matchRefDTO.getGame().getParticipants().get(0).getStats().getItem6());
		history.setParticipant1Deaths(matchRefDTO.getGame().getParticipants().get(0).getStats().getDeaths());
		history.setParticipant1Kills(matchRefDTO.getGame().getParticipants().get(0).getStats().getKills());
		history.setParticipant1Assists(matchRefDTO.getGame().getParticipants().get(0).getStats().getAssists());
		history.setParticipant1TeamId(matchRefDTO.getGame().getTeams().get(0).getTeamId());

		history.setParticipant2Id(-1);
		history.setParticipant2AccountId(-1);
		history.setParticipant2ChampionId(matchRefDTO.getGame().getParticipants().get(1).getChampionId());
		history.setParticipant2Spell1Id(matchRefDTO.getGame().getParticipants().get(1).getSpell1Id());
		history.setParticipant2Spell2Id(matchRefDTO.getGame().getParticipants().get(1).getSpell2Id());
		SummonerSpell spell21 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(1).getSpell1Id());
		SummonerSpell spell22 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(1).getSpell2Id());
		history.setParticipant2Spell1Image((spell21!=null && spell21.getImage()!=null)?spell21.getImage():"");
		history.setParticipant2Spell2Image((spell22!=null && spell22.getImage()!=null)?spell22.getImage():"");
		history.setParticipant2Item0(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem0());
		history.setParticipant2Item1(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem1());
		history.setParticipant2Item2(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem2());
		history.setParticipant2Item3(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem3());
		history.setParticipant2Item4(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem4());
		history.setParticipant2Item5(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem5());
		history.setParticipant2Item6(matchRefDTO.getGame().getParticipants().get(1).getStats().getItem6());
		history.setParticipant2Deaths(matchRefDTO.getGame().getParticipants().get(1).getStats().getDeaths());
		history.setParticipant2Kills(matchRefDTO.getGame().getParticipants().get(1).getStats().getKills());
		history.setParticipant2Assists(matchRefDTO.getGame().getParticipants().get(1).getStats().getAssists());
		history.setParticipant2TeamId(matchRefDTO.getGame().getTeams().get(0).getTeamId());

		history.setParticipant3Id(-1);
		history.setParticipant3AccountId(-1);
		history.setParticipant3ChampionId(matchRefDTO.getGame().getParticipants().get(2).getChampionId());
		history.setParticipant3Spell1Id(matchRefDTO.getGame().getParticipants().get(2).getSpell1Id());
		history.setParticipant3Spell2Id(matchRefDTO.getGame().getParticipants().get(2).getSpell2Id());
		SummonerSpell spell31 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(2).getSpell1Id());
		SummonerSpell spell32 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(2).getSpell2Id());
		history.setParticipant3Spell1Image((spell31!=null && spell31.getImage()!=null)?spell31.getImage():"");
		history.setParticipant3Spell2Image((spell32!=null && spell32.getImage()!=null)?spell32.getImage():"");
		history.setParticipant3Item0(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem0());
		history.setParticipant3Item1(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem1());
		history.setParticipant3Item2(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem2());
		history.setParticipant3Item3(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem3());
		history.setParticipant3Item4(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem4());
		history.setParticipant3Item5(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem5());
		history.setParticipant3Item6(matchRefDTO.getGame().getParticipants().get(2).getStats().getItem6());
		history.setParticipant3Deaths(matchRefDTO.getGame().getParticipants().get(2).getStats().getDeaths());
		history.setParticipant3Kills(matchRefDTO.getGame().getParticipants().get(2).getStats().getKills());
		history.setParticipant3Assists(matchRefDTO.getGame().getParticipants().get(2).getStats().getAssists());
		history.setParticipant3TeamId(matchRefDTO.getGame().getTeams().get(0).getTeamId());

		history.setParticipant4Id(-1);
		history.setParticipant4AccountId(-1);
		history.setParticipant4ChampionId(matchRefDTO.getGame().getParticipants().get(3).getChampionId());
		history.setParticipant4Spell1Id(matchRefDTO.getGame().getParticipants().get(3).getSpell1Id());
		history.setParticipant4Spell2Id(matchRefDTO.getGame().getParticipants().get(3).getSpell2Id());
		SummonerSpell spell41 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(3).getSpell1Id());
		SummonerSpell spell42 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(3).getSpell2Id());
		history.setParticipant4Spell1Image((spell41!=null && spell41.getImage()!=null)?spell41.getImage():"");
		history.setParticipant4Spell2Image((spell42!=null && spell42.getImage()!=null)?spell42.getImage():"");
		history.setParticipant4Item0(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem0());
		history.setParticipant4Item1(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem1());
		history.setParticipant4Item2(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem2());
		history.setParticipant4Item3(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem3());
		history.setParticipant4Item4(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem4());
		history.setParticipant4Item5(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem5());
		history.setParticipant4Item6(matchRefDTO.getGame().getParticipants().get(3).getStats().getItem6());
		history.setParticipant4Deaths(matchRefDTO.getGame().getParticipants().get(3).getStats().getDeaths());
		history.setParticipant4Kills(matchRefDTO.getGame().getParticipants().get(3).getStats().getKills());
		history.setParticipant4Assists(matchRefDTO.getGame().getParticipants().get(3).getStats().getAssists());
		history.setParticipant4TeamId(matchRefDTO.getGame().getTeams().get(0).getTeamId());

		history.setParticipant5Id(-1);
		history.setParticipant5AccountId(-1);
		history.setParticipant5ChampionId(matchRefDTO.getGame().getParticipants().get(4).getChampionId());
		history.setParticipant5Spell1Id(matchRefDTO.getGame().getParticipants().get(4).getSpell1Id());
		history.setParticipant5Spell2Id(matchRefDTO.getGame().getParticipants().get(4).getSpell2Id());
		SummonerSpell spell51 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(4).getSpell1Id());
		SummonerSpell spell52 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(4).getSpell2Id());
		history.setParticipant5Spell1Image((spell51!=null && spell51.getImage()!=null)?spell51.getImage():"");
		history.setParticipant5Spell2Image((spell52!=null && spell52.getImage()!=null)?spell52.getImage():"");
		history.setParticipant5Item0(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem0());
		history.setParticipant5Item1(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem1());
		history.setParticipant5Item2(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem2());
		history.setParticipant5Item3(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem3());
		history.setParticipant5Item4(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem4());
		history.setParticipant5Item5(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem5());
		history.setParticipant5Item6(matchRefDTO.getGame().getParticipants().get(4).getStats().getItem6());
		history.setParticipant5Deaths(matchRefDTO.getGame().getParticipants().get(4).getStats().getDeaths());
		history.setParticipant5Kills(matchRefDTO.getGame().getParticipants().get(4).getStats().getKills());
		history.setParticipant5Assists(matchRefDTO.getGame().getParticipants().get(4).getStats().getAssists());
		history.setParticipant5TeamId(matchRefDTO.getGame().getTeams().get(0).getTeamId());

		history.setParticipant6Id(-1);
		history.setParticipant6AccountId(-1);
		history.setParticipant6ChampionId(matchRefDTO.getGame().getParticipants().get(5).getChampionId());
		history.setParticipant6Spell1Id(matchRefDTO.getGame().getParticipants().get(5).getSpell1Id());
		history.setParticipant6Spell2Id(matchRefDTO.getGame().getParticipants().get(5).getSpell2Id());
		SummonerSpell spell61 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(5).getSpell1Id());
		SummonerSpell spell62 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(5).getSpell2Id());
		history.setParticipant6Spell1Image((spell61!=null && spell61.getImage()!=null)?spell61.getImage():"");
		history.setParticipant6Spell2Image((spell62!=null && spell62.getImage()!=null)?spell62.getImage():"");
		history.setParticipant6Item0(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem0());
		history.setParticipant6Item1(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem1());
		history.setParticipant6Item2(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem2());
		history.setParticipant6Item3(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem3());
		history.setParticipant6Item4(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem4());
		history.setParticipant6Item5(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem5());
		history.setParticipant6Item6(matchRefDTO.getGame().getParticipants().get(5).getStats().getItem6());
		history.setParticipant6Deaths(matchRefDTO.getGame().getParticipants().get(5).getStats().getDeaths());
		history.setParticipant6Kills(matchRefDTO.getGame().getParticipants().get(5).getStats().getKills());
		history.setParticipant6Assists(matchRefDTO.getGame().getParticipants().get(5).getStats().getAssists());
		history.setParticipant6TeamId(matchRefDTO.getGame().getTeams().get(1).getTeamId());

		history.setParticipant7Id(-1);
		history.setParticipant7AccountId(-1);
		history.setParticipant7ChampionId(matchRefDTO.getGame().getParticipants().get(6).getChampionId());
		history.setParticipant7Spell1Id(matchRefDTO.getGame().getParticipants().get(6).getSpell1Id());
		history.setParticipant7Spell2Id(matchRefDTO.getGame().getParticipants().get(6).getSpell2Id());
		SummonerSpell spell71 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(6).getSpell1Id());
		SummonerSpell spell72 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(6).getSpell2Id());
		history.setParticipant7Spell1Image((spell71!=null && spell71.getImage()!=null)?spell71.getImage():"");
		history.setParticipant7Spell2Image((spell72!=null && spell72.getImage()!=null)?spell72.getImage():"");
		history.setParticipant7Item0(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem0());
		history.setParticipant7Item1(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem1());
		history.setParticipant7Item2(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem2());
		history.setParticipant7Item3(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem3());
		history.setParticipant7Item4(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem4());
		history.setParticipant7Item5(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem5());
		history.setParticipant7Item6(matchRefDTO.getGame().getParticipants().get(6).getStats().getItem6());
		history.setParticipant7Deaths(matchRefDTO.getGame().getParticipants().get(6).getStats().getDeaths());
		history.setParticipant7Kills(matchRefDTO.getGame().getParticipants().get(6).getStats().getKills());
		history.setParticipant7Assists(matchRefDTO.getGame().getParticipants().get(6).getStats().getAssists());
		history.setParticipant7TeamId(matchRefDTO.getGame().getTeams().get(1).getTeamId());
		
		history.setParticipant8Id(-1);
		history.setParticipant8AccountId(-1);
		history.setParticipant8ChampionId(matchRefDTO.getGame().getParticipants().get(7).getChampionId());
		history.setParticipant8Spell1Id(matchRefDTO.getGame().getParticipants().get(7).getSpell1Id());
		history.setParticipant8Spell2Id(matchRefDTO.getGame().getParticipants().get(7).getSpell2Id());
		SummonerSpell spell81 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(7).getSpell1Id());
		SummonerSpell spell82 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(7).getSpell2Id());
		history.setParticipant8Spell1Image((spell81!=null && spell81.getImage()!=null)?spell81.getImage():"");
		history.setParticipant8Spell2Image((spell82!=null && spell82.getImage()!=null)?spell82.getImage():"");
		history.setParticipant8Item0(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem0());
		history.setParticipant8Item1(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem1());
		history.setParticipant8Item2(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem2());
		history.setParticipant8Item3(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem3());
		history.setParticipant8Item4(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem4());
		history.setParticipant8Item5(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem5());
		history.setParticipant8Item6(matchRefDTO.getGame().getParticipants().get(7).getStats().getItem6());
		history.setParticipant8Deaths(matchRefDTO.getGame().getParticipants().get(7).getStats().getDeaths());
		history.setParticipant8Kills(matchRefDTO.getGame().getParticipants().get(7).getStats().getKills());
		history.setParticipant8Assists(matchRefDTO.getGame().getParticipants().get(7).getStats().getAssists());
		history.setParticipant8TeamId(matchRefDTO.getGame().getTeams().get(1).getTeamId());
		
		history.setParticipant9Id(-1);
		history.setParticipant9AccountId(-1);
		history.setParticipant9ChampionId(matchRefDTO.getGame().getParticipants().get(8).getChampionId());
		history.setParticipant9Spell1Id(matchRefDTO.getGame().getParticipants().get(8).getSpell1Id());
		history.setParticipant9Spell2Id(matchRefDTO.getGame().getParticipants().get(8).getSpell2Id());
		SummonerSpell spell91 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(8).getSpell1Id());
		SummonerSpell spell92 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(8).getSpell2Id());
		history.setParticipant9Spell1Image((spell91!=null && spell91.getImage()!=null)?spell91.getImage():"");
		history.setParticipant9Spell2Image((spell92!=null && spell92.getImage()!=null)?spell92.getImage():"");
		history.setParticipant9Item0(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem0());
		history.setParticipant9Item1(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem1());
		history.setParticipant9Item2(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem2());
		history.setParticipant9Item3(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem3());
		history.setParticipant9Item4(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem4());
		history.setParticipant9Item5(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem5());
		history.setParticipant9Item6(matchRefDTO.getGame().getParticipants().get(8).getStats().getItem6());
		history.setParticipant9Deaths(matchRefDTO.getGame().getParticipants().get(8).getStats().getDeaths());
		history.setParticipant9Kills(matchRefDTO.getGame().getParticipants().get(8).getStats().getKills());
		history.setParticipant9Assists(matchRefDTO.getGame().getParticipants().get(8).getStats().getAssists());
		history.setParticipant9TeamId(matchRefDTO.getGame().getTeams().get(1).getTeamId());
		
		history.setParticipant10Id(-1);
		history.setParticipant10AccountId(-1);
		history.setParticipant10ChampionId(matchRefDTO.getGame().getParticipants().get(9).getChampionId());
		history.setParticipant10Spell1Id(matchRefDTO.getGame().getParticipants().get(9).getSpell1Id());
		history.setParticipant10Spell2Id(matchRefDTO.getGame().getParticipants().get(9).getSpell2Id());
		SummonerSpell spell101 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(9).getSpell1Id());
		SummonerSpell spell102 = getSummonerSpellByIdFromDB(matchRefDTO.getGame().getParticipants().get(9).getSpell2Id());
		history.setParticipant10Spell1Image((spell101!=null && spell101.getImage()!=null)?spell101.getImage():"");
		history.setParticipant10Spell2Image((spell102!=null && spell102.getImage()!=null)?spell102.getImage():"");
		history.setParticipant10Item0(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem0());
		history.setParticipant10Item1(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem1());
		history.setParticipant10Item2(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem2());
		history.setParticipant10Item3(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem3());
		history.setParticipant10Item4(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem4());
		history.setParticipant10Item5(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem5());
		history.setParticipant10Item6(matchRefDTO.getGame().getParticipants().get(9).getStats().getItem6());
		history.setParticipant10Deaths(matchRefDTO.getGame().getParticipants().get(9).getStats().getDeaths());
		history.setParticipant10Kills(matchRefDTO.getGame().getParticipants().get(9).getStats().getKills());
		history.setParticipant10Assists(matchRefDTO.getGame().getParticipants().get(9).getStats().getAssists());
		history.setParticipant10TeamId(matchRefDTO.getGame().getTeams().get(1).getTeamId());
		 
		return history;
	}

	private static HistoryGameDataDTO getHistoryGameDataFromMatchReferenceData(long accountId, MatchReferenceDTO matchRefDTO, Champion champion, Summoner summoner, int regionId)
	{
		HistoryGameDataDTO result = new HistoryGameDataDTO();
		
		result.setGameId(matchRefDTO.getGameId());
		result.setPlatformId(matchRefDTO.getPlatformId());
		result.setTimestamp(matchRefDTO.getTimestamp());
		result.setQueue(matchRefDTO.getQueue());
		result.setSeason(matchRefDTO.getSeason());
		result.setGameMode(matchRefDTO.getGame().getGameMode());
		result.setMapId(matchRefDTO.getGame().getMapId());
		result.setGameType(matchRefDTO.getGame().getGameType());
		result.setChampionId(champion.getId());
		result.setChampion(champion);
		
		for (Participant participant : matchRefDTO.getGame().getParticipants()) 
		{
			if(participant.getChampionId() == champion.getId())
			{
				result.setTeamId(participant.getTeamId());
				result.setWin(participant.getStats().isWin());
				
				result.setSummonerId(summoner.getId());
				result.setAccountId(summoner.getAccountId());
				
				result.setSpell1Id(participant.getSpell1Id());
				result.setSpell2Id(participant.getSpell2Id());
				SummonerSpell spell1 = getSummonerSpellByIdFromDB(participant.getSpell1Id());
				SummonerSpell spell2 = getSummonerSpellByIdFromDB(participant.getSpell1Id());
				result.setSpell1Image((spell1!=null && spell1.getImage()!=null)?spell1.getImage():"");
				result.setSpell2Image((spell2!=null && spell2.getImage()!=null)?spell2.getImage():"");
				result.setItem0(participant.getStats().getItem0());
				result.setItem1(participant.getStats().getItem1());
				result.setItem2(participant.getStats().getItem2());
				result.setItem3(participant.getStats().getItem3());
				result.setItem4(participant.getStats().getItem4());
				result.setItem5(participant.getStats().getItem5());
				result.setItem6(participant.getStats().getItem6());
				result.setDeaths(participant.getStats().getDeaths());
				result.setKills(participant.getStats().getKills());
				result.setAssists(participant.getStats().getAssists());
				
				break;
			}
		}
		
		return result;
	}
	
	
	private static HistoryGameDataDTO getHistoryGameDataFromHistoryDBData(long accountId, History historyData, Champion champion ) 
	{
		HistoryGameDataDTO result = new HistoryGameDataDTO();
		
		result.setGameId(historyData.getGameId());
		result.setPlatformId(historyData.getPlatformId());
		result.setTimestamp(historyData.getTimestamp());
		result.setQueue(historyData.getQueue());
		result.setSeason(historyData.getSeason());
		result.setGameMode(historyData.getGameMode());
		result.setMapId(historyData.getMapId());
		result.setGameType(historyData.getGameType());
		result.setChampionId(champion.getId());
		result.setChampion(champion);
		
		if(historyData.getParticipant1ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant1TeamId());
			result.setWin(historyData.getTeam1Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant1Id());
			result.setAccountId(historyData.getParticipant1AccountId());
			result.setSpell1Id(historyData.getParticipant1Spell1Id());
			result.setSpell2Id(historyData.getParticipant1Spell2Id());
			result.setSpell1Image(historyData.getParticipant1Spell1Image());
			result.setSpell2Image(historyData.getParticipant1Spell2Image());
			result.setItem0(historyData.getParticipant1Item0());
			result.setItem1(historyData.getParticipant1Item1());
			result.setItem2(historyData.getParticipant1Item2());
			result.setItem3(historyData.getParticipant1Item3());
			result.setItem4(historyData.getParticipant1Item4());
			result.setItem5(historyData.getParticipant1Item5());
			result.setItem6(historyData.getParticipant1Item6());
			result.setDeaths(historyData.getParticipant1Deaths());
			result.setKills(historyData.getParticipant1Kills());
			result.setAssists(historyData.getParticipant1Assists());
		}
		else if(historyData.getParticipant2ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant2TeamId());
			result.setWin(historyData.getTeam1Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant2Id());
			result.setAccountId(historyData.getParticipant2AccountId());
			result.setSpell1Id(historyData.getParticipant2Spell1Id());
			result.setSpell2Id(historyData.getParticipant2Spell2Id());
			result.setSpell1Image(historyData.getParticipant2Spell1Image());
			result.setSpell2Image(historyData.getParticipant2Spell2Image());
			result.setItem0(historyData.getParticipant2Item0());
			result.setItem1(historyData.getParticipant2Item1());
			result.setItem2(historyData.getParticipant2Item2());
			result.setItem3(historyData.getParticipant2Item3());
			result.setItem4(historyData.getParticipant2Item4());
			result.setItem5(historyData.getParticipant2Item5());
			result.setItem6(historyData.getParticipant2Item6());
			result.setDeaths(historyData.getParticipant2Deaths());
			result.setKills(historyData.getParticipant2Kills());
			result.setAssists(historyData.getParticipant2Assists());
		}
		else if(historyData.getParticipant3ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant3TeamId());
			result.setWin(historyData.getTeam1Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant3Id());
			result.setAccountId(historyData.getParticipant3AccountId());
			result.setSpell1Id(historyData.getParticipant3Spell1Id());
			result.setSpell2Id(historyData.getParticipant3Spell2Id());
			result.setSpell1Image(historyData.getParticipant3Spell1Image());
			result.setSpell2Image(historyData.getParticipant3Spell2Image());
			result.setItem0(historyData.getParticipant3Item0());
			result.setItem1(historyData.getParticipant3Item1());
			result.setItem2(historyData.getParticipant3Item2());
			result.setItem3(historyData.getParticipant3Item3());
			result.setItem4(historyData.getParticipant3Item4());
			result.setItem5(historyData.getParticipant3Item5());
			result.setItem6(historyData.getParticipant3Item6());
			result.setDeaths(historyData.getParticipant3Deaths());
			result.setKills(historyData.getParticipant3Kills());
			result.setAssists(historyData.getParticipant3Assists());
		}
		else if(historyData.getParticipant4ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant4TeamId());
			result.setWin(historyData.getTeam1Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant4Id());
			result.setAccountId(historyData.getParticipant4AccountId());
			result.setSpell1Id(historyData.getParticipant4Spell1Id());
			result.setSpell2Id(historyData.getParticipant4Spell2Id());
			result.setSpell1Image(historyData.getParticipant4Spell1Image());
			result.setSpell2Image(historyData.getParticipant4Spell2Image());
			result.setItem0(historyData.getParticipant4Item0());
			result.setItem1(historyData.getParticipant4Item1());
			result.setItem2(historyData.getParticipant4Item2());
			result.setItem3(historyData.getParticipant4Item3());
			result.setItem4(historyData.getParticipant4Item4());
			result.setItem5(historyData.getParticipant4Item5());
			result.setItem6(historyData.getParticipant4Item6());
			result.setDeaths(historyData.getParticipant4Deaths());
			result.setKills(historyData.getParticipant4Kills());
			result.setAssists(historyData.getParticipant4Assists());
		}
		else if(historyData.getParticipant5ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant5TeamId());
			result.setWin(historyData.getTeam1Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant5Id());
			result.setAccountId(historyData.getParticipant5AccountId());
			result.setSpell1Id(historyData.getParticipant5Spell1Id());
			result.setSpell2Id(historyData.getParticipant5Spell2Id());
			result.setSpell1Image(historyData.getParticipant5Spell1Image());
			result.setSpell2Image(historyData.getParticipant5Spell2Image());
			result.setItem0(historyData.getParticipant5Item0());
			result.setItem1(historyData.getParticipant5Item1());
			result.setItem2(historyData.getParticipant5Item2());
			result.setItem3(historyData.getParticipant5Item3());
			result.setItem4(historyData.getParticipant5Item4());
			result.setItem5(historyData.getParticipant5Item5());
			result.setItem6(historyData.getParticipant5Item6());
			result.setDeaths(historyData.getParticipant5Deaths());
			result.setKills(historyData.getParticipant5Kills());
			result.setAssists(historyData.getParticipant5Assists());
		}
		//TEAM 2
		else if(historyData.getParticipant6ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant6TeamId());
			result.setWin(historyData.getTeam2Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant6Id());
			result.setAccountId(historyData.getParticipant6AccountId());
			result.setSpell1Id(historyData.getParticipant6Spell1Id());
			result.setSpell2Id(historyData.getParticipant6Spell2Id());
			result.setSpell1Image(historyData.getParticipant6Spell1Image());
			result.setSpell2Image(historyData.getParticipant6Spell2Image());
			result.setItem0(historyData.getParticipant6Item0());
			result.setItem1(historyData.getParticipant6Item1());
			result.setItem2(historyData.getParticipant6Item2());
			result.setItem3(historyData.getParticipant6Item3());
			result.setItem4(historyData.getParticipant6Item4());
			result.setItem5(historyData.getParticipant6Item5());
			result.setItem6(historyData.getParticipant6Item6());
			result.setDeaths(historyData.getParticipant6Deaths());
			result.setKills(historyData.getParticipant6Kills());
			result.setAssists(historyData.getParticipant6Assists());
		}
		else if(historyData.getParticipant7ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant7TeamId());
			result.setWin(historyData.getTeam2Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant7Id());
			result.setAccountId(historyData.getParticipant7AccountId());
			result.setSpell1Id(historyData.getParticipant7Spell1Id());
			result.setSpell2Id(historyData.getParticipant7Spell2Id());
			result.setSpell1Image(historyData.getParticipant7Spell1Image());
			result.setSpell2Image(historyData.getParticipant7Spell2Image());
			result.setItem0(historyData.getParticipant7Item0());
			result.setItem1(historyData.getParticipant7Item1());
			result.setItem2(historyData.getParticipant7Item2());
			result.setItem3(historyData.getParticipant7Item3());
			result.setItem4(historyData.getParticipant7Item4());
			result.setItem5(historyData.getParticipant7Item5());
			result.setItem6(historyData.getParticipant7Item6());
			result.setDeaths(historyData.getParticipant7Deaths());
			result.setKills(historyData.getParticipant7Kills());
			result.setAssists(historyData.getParticipant7Assists());
		}
		else if(historyData.getParticipant8ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant8TeamId());
			result.setWin(historyData.getTeam2Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant8Id());
			result.setAccountId(historyData.getParticipant8AccountId());
			result.setSpell1Id(historyData.getParticipant8Spell1Id());
			result.setSpell2Id(historyData.getParticipant8Spell2Id());
			result.setSpell1Image(historyData.getParticipant8Spell1Image());
			result.setSpell2Image(historyData.getParticipant8Spell2Image());
			result.setItem0(historyData.getParticipant8Item0());
			result.setItem1(historyData.getParticipant8Item1());
			result.setItem2(historyData.getParticipant8Item2());
			result.setItem3(historyData.getParticipant8Item3());
			result.setItem4(historyData.getParticipant8Item4());
			result.setItem5(historyData.getParticipant8Item5());
			result.setItem6(historyData.getParticipant8Item6());
			result.setDeaths(historyData.getParticipant8Deaths());
			result.setKills(historyData.getParticipant8Kills());
			result.setAssists(historyData.getParticipant8Assists());
		}
		else if(historyData.getParticipant9ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant9TeamId());
			result.setWin(historyData.getTeam2Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant9Id());
			result.setAccountId(historyData.getParticipant9AccountId());
			result.setSpell1Id(historyData.getParticipant9Spell1Id());
			result.setSpell2Id(historyData.getParticipant9Spell2Id());
			result.setSpell1Image(historyData.getParticipant9Spell1Image());
			result.setSpell2Image(historyData.getParticipant9Spell2Image());
			result.setItem0(historyData.getParticipant9Item0());
			result.setItem1(historyData.getParticipant9Item1());
			result.setItem2(historyData.getParticipant9Item2());
			result.setItem3(historyData.getParticipant9Item3());
			result.setItem4(historyData.getParticipant9Item4());
			result.setItem5(historyData.getParticipant9Item5());
			result.setItem6(historyData.getParticipant9Item6());
			result.setDeaths(historyData.getParticipant9Deaths());
			result.setKills(historyData.getParticipant9Kills());
			result.setAssists(historyData.getParticipant9Assists());
		}
		else if(historyData.getParticipant10ChampionId()==champion.getId())
		{
			result.setTeamId(historyData.getParticipant10TeamId());
			result.setWin(historyData.getTeam2Win().equalsIgnoreCase("win"));
			result.setSummonerId(historyData.getParticipant10Id());
			result.setAccountId(historyData.getParticipant10AccountId());
			result.setSpell1Id(historyData.getParticipant10Spell1Id());
			result.setSpell2Id(historyData.getParticipant10Spell2Id());
			result.setSpell1Image(historyData.getParticipant10Spell1Image());
			result.setSpell2Image(historyData.getParticipant10Spell2Image());
			result.setItem0(historyData.getParticipant10Item0());
			result.setItem1(historyData.getParticipant10Item1());
			result.setItem2(historyData.getParticipant10Item2());
			result.setItem3(historyData.getParticipant10Item3());
			result.setItem4(historyData.getParticipant10Item4());
			result.setItem5(historyData.getParticipant10Item5());
			result.setItem6(historyData.getParticipant10Item6());
			result.setDeaths(historyData.getParticipant10Deaths());
			result.setKills(historyData.getParticipant10Kills());
			result.setAssists(historyData.getParticipant10Assists());
		}
		
		return result;
	}

	private static Matchlist getRecentGamesByRegionAndAccountIDFromRiot(Region region, long summonerAccountId)
	{
		Matchlist result = null;
		try 
		{
			result  = LoLApiUtils.getRiotApi().getRecentGamesByAccountId(region, summonerAccountId);
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
	
	private static MatchReferenceDTO getMatchByRegionAndGameIdFromRiot(Region region, MatchReference game)
	{
		MatchReferenceDTO result = null;
		try 
		{
			//TimeUnit.MILLISECONDS.sleep(700);

			Match match = LoLApiUtils.getRiotApi().getMatchByGameId(region, game.getGameId());
			
			result = new MatchReferenceDTO();
			BeanUtils.copyProperties(result, game);
			
			result.setGame(match);
		}
		catch (RateLimitException eRate)
		{
			try 
			{
				TimeUnit.SECONDS.sleep(eRate.getRetryAfter());
				result = getMatchByRegionAndGameIdFromRiot(region, game);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
		}
		catch (RiotApiException | IllegalAccessException | InvocationTargetException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
	
	
	//FROM DB
	private static History getGameFromHistoryByRegionAndGameIdFromDB(Region region, long gameId)
	{
		String patformId = PlatformId.getPlatformByName(region.toString()).getId().toUpperCase();
		
		FieldValue field1 = new FieldValue("gameId", gameId, Types.BIGINT);
		FieldValue field2 = new FieldValue("platformId", patformId, Types.VARCHAR);
	
		BaseWrapperFactory<History> baseWrapperFactory = new BaseWrapperFactory<>();
		History result = baseWrapperFactory.getItemByFields(History.class, new FieldValue[]{field1,field2}, WhereOperation.AND);

		return result;
	}
	
	
	private static HashMap<Integer, SummonerSpell> mapSummonerSpell = new HashMap<>();
	
	private static SummonerSpell getSummonerSpellByIdFromDB(int spellId)
	{
		SummonerSpell result = mapSummonerSpell.get(spellId);
		if(result == null)
		{
			FieldValue field1 = new FieldValue("id", spellId, Types.INTEGER);
			
			BaseWrapperFactory<SummonerSpell> baseWrapperFactory = new BaseWrapperFactory<>();
			result = baseWrapperFactory.getItemByFields(SummonerSpell.class, new FieldValue[]{field1}, WhereOperation.AND);
			
			mapSummonerSpell.put(spellId, result);
		}

		return result;
	}
}
