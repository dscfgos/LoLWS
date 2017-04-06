package com.dscfgos.ws.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.champion_mastery.ChampionMastery;
import com.dscfgos.api.model.dtos.current_game.CurrentGameInfo;
import com.dscfgos.api.model.dtos.current_game.CurrentGameParticipant;
import com.dscfgos.api.model.dtos.static_data.Champion;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;
import com.dscfgos.api.model.dtos.stats.ChampionStats;
import com.dscfgos.api.model.dtos.stats.RankedStats;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.CurrentGameInfoDTO;
import com.dscfgos.ws.classes.dtos.CurrentGameParticipantDTO;
import com.dscfgos.ws.classes.dtos.CurrentGameResultDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;

public class CurrentGameManager 
{
	public static CurrentGameResultDTO getCurrentGameInfoByRegionAndSummonerId(int regionId, long summonerId, String locale)
	{
		CurrentGameResultDTO result = new CurrentGameResultDTO();

		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			CurrentGameInfo currentGame = getCurrentGameInfoFromRiot(PlatformId.getPlatformByName(shard.getSlug()), summonerId);
			if(currentGame != null)
			{
				
				try 
				{
					CurrentGameInfoDTO currentGameDTO = new CurrentGameInfoDTO();
					List<CurrentGameParticipantDTO> participantsListDTO = new ArrayList<>();
					
					BeanUtils.copyProperties(currentGameDTO, currentGame);
					
					for (CurrentGameParticipant participant : currentGame.getParticipants()) 
					{
						//Champion,SummonerSpell
						CurrentGameParticipantDTO participantDTO = new CurrentGameParticipantDTO();
						BeanUtils.copyProperties(participantDTO, participant);
						
						//Champion
						Champion champion = StaticDataManager.getChampionById(participant.getChampionId(), regionId, Locale.getById(locale), "", true, ChampData.IMAGE);
						//Spell1
						SummonerSpell spell1 = StaticDataManager.getSummonerSpellById(participant.getSpell1Id(), regionId, Locale.getById(locale), "", SpellData.IMAGE);
						//Spell2
						SummonerSpell spell2 = StaticDataManager.getSummonerSpellById(participant.getSpell2Id(), regionId, Locale.getById(locale), "", SpellData.IMAGE);
						//Champion Mastery
						ChampionMastery champMastery = getChampionMasteryFromRiot(PlatformId.getPlatformByName(shard.getSlug()), participant.getSummonerId(), participant.getChampionId());
						
						ChampionStats champStats = getChampionStatsFromRiot(Region.getRegionByName(shard.getSlug()), summonerId, participant.getChampionId());
						
						participantDTO.setChampion(champion);
						participantDTO.setSpell1(spell1);
						participantDTO.setSpell2(spell2);
						participantDTO.setChampionMastery(champMastery);
						if(champStats != null && champStats.getStats()!= null)
						{
							participantDTO.setTotalSessionsLost(champStats.getStats().getTotalSessionsLost());
							participantDTO.setTotalSessionsPlayed(champStats.getStats().getTotalSessionsPlayed());
							participantDTO.setTotalSessionsWon(champStats.getStats().getTotalSessionsWon());	
						}
						
						
						participantsListDTO.add(participantDTO);
					}
					currentGameDTO.setParticipants(participantsListDTO);
					result.setCurrentGame(currentGameDTO);
				} 
				catch (IllegalAccessException | InvocationTargetException e) 
				{
					e.printStackTrace();
					result.setResultCode(ErrorsConstants.CG_001);
				}
			}
			else
			{
				result.setResultCode(ErrorsConstants.CG_001);
			}
		}

		return result;
	}

	private static CurrentGameInfo getCurrentGameInfoFromRiot(PlatformId platformId, long summonerId)
	{
		CurrentGameInfo result = null;
		try 
		{
			result  = LoLApiUtils.getRiotApi().getCurrentGameInfo(platformId, summonerId);
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
	
	private static ChampionMastery getChampionMasteryFromRiot(PlatformId platformId, long summonerId, long championId)
	{
		ChampionMastery result = null;
		try 
		{
			TimeUnit.MILLISECONDS.sleep(500);

			result  = LoLApiUtils.getRiotApi().getChampionMastery(platformId, summonerId, championId);
		} 
		catch (RiotApiException | InterruptedException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
	
	private static ChampionStats getChampionStatsFromRiot(Region region, long summonerId, long championId)
	{
		ChampionStats result = null;
		try 
		{
			TimeUnit.MILLISECONDS.sleep(500);

			RankedStats stats = LoLApiUtils.getRiotApi().getRankedStats(region, summonerId);
			if(stats != null && stats.getChampions() != null && stats.getChampions().size() > 0)
			{
				for (ChampionStats item : stats.getChampions()) 
				{
					if(item.getId()==championId)
					{
						result = item;
						break;
					}
				}	
			}
		} 
		catch (RiotApiException | InterruptedException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
}
