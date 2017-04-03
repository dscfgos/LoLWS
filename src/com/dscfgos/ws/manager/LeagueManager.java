package com.dscfgos.ws.manager;

import java.util.List;
import java.util.Map;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.league.League;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.LeagueResultDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;

public class LeagueManager 
{
	public static LeagueResultDTO getLeagueByRegionAndName(int regionId, long summonerId)
	{
		LeagueResultDTO result = new LeagueResultDTO();

		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			Map<String, List<League>> leagues = getLeaguesByRegionAndNameFromRiot(shard.getSlug(), summonerId);
			if(leagues != null && leagues.size() > 0)
			{
				result.setLeagues(leagues);
			}
			else
			{
				result.setResultCode(ErrorsConstants.SM_001);
			}
		}

		return result;
	}

	private static Map<String, List<League>> getLeaguesByRegionAndNameFromRiot(String region, long summonerId)
	{
		Map<String, List<League>> result = null;
		try 
		{
			result  = LoLApiUtils.getRiotApi().getLeagueEntryBySummoners(Region.getRegionByName(region), summonerId);
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
}
