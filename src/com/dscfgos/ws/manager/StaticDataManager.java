package com.dscfgos.ws.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.static_data.Champion;
import com.dscfgos.api.model.dtos.static_data.SummonerSpell;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Shards;

public class StaticDataManager 
{
	private static HashMap<String, SummonerSpell> mapSummonerSpell = new HashMap<>();

	private static HashMap<Long, SummonerSpell> mapSimpleSummonerSpell = new HashMap<>();

	public static List<String> getDataLanguagesByRegion(Region region)
	{
		List<String> result = new ArrayList<>();
		
		try 
		{
			result = LoLApiUtils.getRiotApi().getDataLanguages(region);
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Champion getChampionById(long id,int regionId, Locale locale, String version, boolean dataById, ChampData... champdata)
	{
		
		Champion result = null;
		
		try 
		{
			Shards shard = ShardsManager.getShardsById(regionId);
			if(shard != null)
			{
				result = LoLApiUtils.getRiotApi().getDataChampion(Region.getRegionByName(shard.getSlug()), new Long(id).intValue(), locale, version, champdata);
			}
			
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static SummonerSpell getSummonerSpellById(long id,int regionId)
	{
		SummonerSpell result = mapSimpleSummonerSpell.get(id);
		if(result == null)
		{
			try 
			{
				Shards shard = ShardsManager.getShardsById(regionId);
				if(shard != null)
				{
					result = LoLApiUtils.getRiotApi().getDataSummonerSpell(Region.getRegionByName(shard.getSlug()), new Long(id).intValue());
					if(result != null)
					{
						mapSimpleSummonerSpell.put(id, result);
					}
				}
			} 
			catch (RiotApiException e) {
				e.printStackTrace();
			}	
		}
			
		return result;
	}
	
	public static SummonerSpell getSummonerSpellById(long id,int regionId, Locale locale, String version, SpellData... spellData)
	{
		String strKey = id + "_"+regionId+"_"+locale ;
		
		SummonerSpell result = mapSummonerSpell.get(strKey);
		if(result == null)
		{
			try 
			{
				Shards shard = ShardsManager.getShardsById(regionId);
				if(shard != null)
				{
					result = LoLApiUtils.getRiotApi().getDataSummonerSpell(Region.getRegionByName(shard.getSlug()), new Long(id).intValue(), locale, version, spellData);
					if(result != null)
					{
						mapSummonerSpell.put(strKey, result);
					}
				}
			} 
			catch (RiotApiException e) {
				e.printStackTrace();
			}	
		}
			
		return result;
	}
}
