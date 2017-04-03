package com.dscfgos.ws.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.current_game.CurrentGameInfo;
import com.dscfgos.api.model.dtos.current_game.CurrentGameParticipant;
import com.dscfgos.api.model.dtos.static_data.Champion;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;
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
			CurrentGameInfo currentGame = getCurrentGameInfoFromRiot(PlatformId.LAS, summonerId);
			//CurrentGameInfo currentGame = getCurrentGameInfoFromRiot(PlatformId.getPlatformByName(shard.getSlug()), summonerId);
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
					
						participantDTO.setChampion(champion);
						participantDTO.setSpell1(spell1);
						participantDTO.setSpell2(spell2);
						
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
}
