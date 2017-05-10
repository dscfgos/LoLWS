package com.dscfgos.ws.manager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Types;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.classes.constants.ErrorsConstants;
import com.dscfgos.ws.classes.dtos.SummonerResultDTO;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Summoner;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;

public class SummonerManager 
{
	public static SummonerResultDTO getSummonerByRegionAndName(int regionId, String name)
	{
		SummonerResultDTO result = new SummonerResultDTO();
		
		Summoner summoner = null ;

		summoner = getSummonerByRegionIdAndNameFromDB(regionId, name);
		if(summoner == null)
		{
			Shards shard = ShardsManager.getShardsById(regionId);
			if(shard != null)
			{
				summoner = getSummonerByRegionAndNameFromRiot(shard.getSlug(), name);
				
				if(summoner != null)
				{
					summoner.setShardid(shard.getId());
					BaseWrapperFactory<Summoner> baseWrapper = new BaseWrapperFactory<>();
					baseWrapper.addItem(Summoner.class, summoner);
					
					result.setSummoner(summoner);
				}
				else
				{
					result.setResultCode(ErrorsConstants.SM_001);
				}
			}
		}
		else
		{
			result.setSummoner(summoner);
		}

		return result;
	}
	
	public static SummonerResultDTO getSummonerByRegionAndId(int regionId, String name)
	{
		SummonerResultDTO result = new SummonerResultDTO();
		
		Summoner summoner = null ;

		summoner = getSummonerByRegionIdAndIdFromDB(regionId, name);
		if(summoner == null)
		{
			Shards shard = ShardsManager.getShardsById(regionId);
			if(shard != null)
			{
				summoner = getSummonerByRegionAndIdFromRiot(shard.getSlug(), name);
				
				if(summoner != null)
				{
					summoner.setShardid(shard.getId());
					BaseWrapperFactory<Summoner> baseWrapper = new BaseWrapperFactory<>();
					baseWrapper.addItem(Summoner.class, summoner);
					
					result.setSummoner(summoner);
				}
				else
				{
					result.setResultCode(ErrorsConstants.SM_001);
				}
			}
		}
		else
		{
			result.setSummoner(summoner);
		}

		return result;
	}

	public static SummonerResultDTO updateSummonerByRegionAndName(int regionId, String name)
	{
		SummonerResultDTO result = new SummonerResultDTO();

		Summoner summoner = null ;

		Shards shard = ShardsManager.getShardsById(regionId);
		if(shard != null)
		{
			summoner = getSummonerByRegionAndNameFromRiot(shard.getSlug(), name);
			if(summoner != null)
			{
				FieldValue field1 = new FieldValue("name", name, Types.VARCHAR);
				FieldValue field2 = new FieldValue("shardid", regionId, Types.INTEGER);
				
				FieldValue[] whereFields = new FieldValue[]{field1,field2};
				
				FieldValue fieldName1 = new FieldValue("profileIconId",summoner.getProfileIconId(),Types.INTEGER);
				FieldValue fieldName2 = new FieldValue("revisionDate",summoner.getRevisionDate(),Types.BIGINT);
				FieldValue fieldName3 = new FieldValue("summonerLevel",summoner.getSummonerLevel(),Types.BIGINT);
				
				FieldValue[] toUpdateFields = new FieldValue[]{fieldName1,fieldName2, fieldName3};
				
				summoner.setShardid(shard.getId());
				
				BaseWrapperFactory<Summoner> baseWrapper = new BaseWrapperFactory<>();
				baseWrapper.updateItem(Summoner.class, summoner, toUpdateFields, whereFields);
				
				result.setSummoner(summoner);
			}
			else
			{
				result.setResultCode(ErrorsConstants.SM_001);
			}
		}
		else
		{
			result.setResultCode(ErrorsConstants.SD_001);
		}


		return result;
	}

	private static Summoner getSummonerByRegionAndNameFromRiot(String region, String name)
	{
		Summoner result = null;
		try 
		{
			com.dscfgos.api.model.dtos.v3.summoner.Summoner summoner  = LoLApiUtils.getRiotApi().getSummonerByName(Region.getRegionByName(region), name);
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
	
	private static Summoner getSummonerByRegionAndIdFromRiot(String region, String summonerId)
	{
		Summoner result = null;
		try 
		{
			com.dscfgos.api.model.dtos.v3.summoner.Summoner summoner  = LoLApiUtils.getRiotApi().getSummonerById(Region.LAS, summonerId);
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
	
	private static Summoner getSummonerByRegionIdAndIdFromDB(int regionId, String id)
	{
		FieldValue field1 = new FieldValue("id", id, Types.BIGINT);
		FieldValue field2 = new FieldValue("shardid", regionId, Types.INTEGER);

		BaseWrapperFactory<Summoner> baseWrapperFactory = new BaseWrapperFactory<>();
		Summoner result = baseWrapperFactory.getItemByFields(Summoner.class, new FieldValue[]{field1,field2}, WhereOperation.AND);

		return result;
	}

}
