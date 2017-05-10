package com.dscfgos.ws.manager;

import java.util.ArrayList;
import java.util.Set;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.league.LeaguePosition;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.LeaguePositionResultDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;

public class LeagueManager 
{
	public static LeaguePositionResultDTO getLeaguesPositionsByRegionAndSummonerId(int regionId, String summonerId)
	{
		LeaguePositionResultDTO result = new LeaguePositionResultDTO();

		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			Set<LeaguePosition> leaguePosition = getLeaguePostionByRegionAndSummonerIdFromRiot(shard.getSlug(), summonerId);
			if(leaguePosition != null && leaguePosition.size() > 0)
			{
				result.setLeaguePosition(new ArrayList<>(leaguePosition));
			}
			else
			{
				result.setResultCode(ErrorsConstants.SM_001);
			}
		}

		return result;
	}

	private static Set<LeaguePosition> getLeaguePostionByRegionAndSummonerIdFromRiot(String region, String summonerId)
	{
		Set<LeaguePosition> result = null;
		try 
		{
			result  = LoLApiUtils.getRiotApi().getLeaguePositionBySummonerId(Region.getRegionByName(region), summonerId);
		} 
		catch (RiotApiException e) 
		{
			e.printStackTrace();
		}

		return result;
	}
}
