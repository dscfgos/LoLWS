package com.dscfgos.ws.manager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Types;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Summoner;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;

public class SummonerManager 
{
	public static Summoner getSummonerByRegionAndName(int regionId, String name)
	{
		Summoner result = null ;

		result = getSummonerByRegionIdAndNameFromDB(regionId, name);
		if(result == null)
		{
			Shards shard = ShardsManager.getShardsById(regionId);
			if(shard != null)
			{
				result = getSummonerByRegionAndNameFromRiot(shard.getSlug(), name);
				if(result != null)
				{
					result.setShardid(shard.getId());
					BaseWrapperFactory<Summoner> baseWrapper = new BaseWrapperFactory<>();
					result = baseWrapper.addItem(Summoner.class, result);
				}	
			}
		}

		return result;
	}

	private static Summoner getSummonerByRegionAndNameFromRiot(String region, String name)
	{
		Summoner result = null;
		try 
		{
			com.dscfgos.api.model.dtos.summoner.Summoner summoner  = LoLApiUtils.getRiotApi().getSummonerByName(Region.getRegionByName(region), name);
			if(summoner != null)
			{
				result = new Summoner();
			}
			try 
			{
				BeanUtils.copyProperties(result, summoner);
			} 
			catch (IllegalAccessException | InvocationTargetException e) 
			{
				e.printStackTrace();
			}

		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static Summoner getSummonerByRegionIdAndNameFromDB(int regionId, String name)
	{
		FieldValue field1 = new FieldValue("name", name, Types.VARCHAR);
		FieldValue field2 = new FieldValue("shardid", regionId, Types.INTEGER);
		
		BaseWrapperFactory<Summoner> baseWrapperFactory = new BaseWrapperFactory<>();
		Summoner result = baseWrapperFactory.getItemByFields(Summoner.class, new FieldValue[]{field1,field2}, WhereOperation.AND);
	
		return result;
	}

}
