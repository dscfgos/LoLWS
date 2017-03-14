package com.dscfgos.ws.rest_ws;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.LocalesManager;
import com.dscfgos.admin.ShardsLocalesManager;
import com.dscfgos.admin.ShardsManager;
import com.dscfgos.ws.classes.dtos.Shard;
import com.dscfgos.ws.classes.wrappers.Locales;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Shards_Locales;
import com.google.gson.Gson;

@Path("/test")
public class TestWS 
{
	Gson gson = new Gson();

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public String getShards()
	{
		List<Shards> shards = ShardsManager.getAllShards();
		
		List<Shard> result = new ArrayList<>();
		
		for (Shards shard : shards) 
		{
			
			List<Shards_Locales> shard_locale_list = ShardsLocalesManager.getShardLocalesByShardId(shard.getId());
			int[] ids = new int[shard_locale_list.size()];
			for (int i = 0; i < ids.length; i++) 
			{
				ids[i] = shard_locale_list.get(i).getLocale_id();
			}
			
			try 
			{
				List<Locales> locales = LocalesManager.getLocaleByIds(ids);
				
				Shard shardDTO = new Shard();
				BeanUtils.copyProperties(shardDTO, shard);
				
				shardDTO.setLocales(locales);
				
				result.add(shardDTO);
			} 
			catch (IllegalAccessException | InvocationTargetException e) 
			{
				e.printStackTrace();
			} 
		}
		
		return gson.toJson(result);
		
	}

}
