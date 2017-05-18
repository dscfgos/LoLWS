package com.dscfgos.ws.manager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.classes.managers.rate.RateLimitException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.v3.static_data.SummonerSpell;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.classes.wrappers.Champion;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.factory.BaseWrapperFactory;
import com.dscfgos.ws.factory.FieldValue;
import com.dscfgos.ws.factory.WhereOperation;

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
	
	public static Champion getBasicChampionById(long id,Region region, Locale locale, String version)
	{
		Champion result = getChampionByRegionIdAndIdFromDB((int) id);
		if(result == null)
		{
			try 
			{
				com.dscfgos.api.model.dtos.v3.static_data.Champion champion = LoLApiUtils.getRiotApi().getDataChampion(region, new Long(id).intValue(), locale, version, ChampData.IMAGE);
				if(champion != null)
				{
					result = new Champion();
					result.setId(champion.getId());
					result.setName(champion.getName());
					result.setTitle(champion.getTitle());
					result.setImage(champion.getImage().getFull());
					
					BaseWrapperFactory<Champion> baseWrapper = new BaseWrapperFactory<>();
					baseWrapper.addItem(Champion.class, result);
				}
			} 
			catch (RiotApiException e) {
				e.printStackTrace();
			}
			
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
					result = LoLApiUtils.getRiotApi().getDataSummonerSpell(Region.getRegionByName(shard.getSlug()), new Long(id).intValue(), null, null, SpellData.IMAGE);
					if(result != null)
					{
						mapSimpleSummonerSpell.put(id, result);
					}
				}
			} 
			catch (RateLimitException eRate)
			{
				try 
				{
					TimeUnit.SECONDS.sleep(eRate.getRetryAfter());
					result = getSummonerSpellById(id, regionId);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
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
	
	
	//FROM DB
	private static com.dscfgos.ws.classes.wrappers.Champion getChampionByRegionIdAndIdFromDB(int id)
	{
		FieldValue field1 = new FieldValue("id", id, Types.INTEGER);
	
		BaseWrapperFactory<com.dscfgos.ws.classes.wrappers.Champion> baseWrapperFactory = new BaseWrapperFactory<>();
		com.dscfgos.ws.classes.wrappers.Champion result = baseWrapperFactory.getItemByFields(com.dscfgos.ws.classes.wrappers.Champion.class, new FieldValue[]{field1}, WhereOperation.AND);

		return result;
	}
}
