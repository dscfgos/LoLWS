package com.dscfgos.ws.manager;

import java.util.HashMap;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.game.RecentGames;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;
import com.dscfgos.api.model.dtos.v3.static_data.Champion;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.GameDTO;
import com.dscfgos.ws.classes.dtos.RecentGamesResultDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;

public class RecentGamesManager 
{
	public static RecentGamesResultDTO getRecentGamesByRegionAndName(int regionId, long summonerId, String locale)
	{
		RecentGamesResultDTO result = new RecentGamesResultDTO();

		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			RecentGames recentGames = getRecentGamesByRegionAndNameFromRiot(shard.getSlug(), summonerId);
			if(recentGames != null && recentGames.getGames() != null)
			{
				if(recentGames.getGames().size() > 0)
				{
					HashMap<Integer, Champion> mapChamps = new HashMap<>();
					for (GameDTO game : recentGames.getGames()) 
					{
						//Champion
						Champion champion = mapChamps.get(game.getChampionId());
						if(champion==null)
						{
							champion = StaticDataManager.getChampionById(game.getChampionId(), regionId, Locale.getById(locale), "", true, ChampData.IMAGE);
							mapChamps.put(champion.getId(), champion);
							
						}
						
						SummonerSpell spell1 = StaticDataManager.getSummonerSpellById(game.getSpell1(), regionId);
						SummonerSpell spell2 = StaticDataManager.getSummonerSpellById(game.getSpell2(), regionId);
						
						game.setSpell1Key(spell1.getKey());
						game.setSpell2Key(spell2.getKey());
						
						game.setChampion(champion);
					}
				}
		
				result.setRecentGames(recentGames);
			}
			else
			{
				result.setResultCode(ErrorsConstants.RG_001);
			}
		}

		return result;
	}

	private static RecentGames getRecentGamesByRegionAndNameFromRiot(String region, long summonerId)
	{
		RecentGames result = null;
		try 
		{
			result  = LoLApiUtils.getRiotApi().getRecentGames(Region.getRegionByName(region), summonerId);
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
}
