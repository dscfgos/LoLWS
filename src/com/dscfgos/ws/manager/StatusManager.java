package com.dscfgos.ws.manager;

import java.util.ArrayList;
import java.util.List;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.status.Shard;
import com.dscfgos.api.model.dtos.status.ShardStatus;
import com.dscfgos.ws.classes.utils.LoLApiUtils;

public class StatusManager 
{
	public static List<Shard> getShards()
	{
		List<Shard> result = new ArrayList<>();
		
		try 
		{
			result = LoLApiUtils.getRiotApi().getShards();
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ShardStatus getShardStatus(Region region)
	{
		ShardStatus result = new ShardStatus();
		
		try 
		{
			result = LoLApiUtils.getRiotApi().getShardStatus(region);
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
