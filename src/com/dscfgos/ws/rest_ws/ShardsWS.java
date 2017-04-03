package com.dscfgos.ws.rest_ws;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.dscfgos.admin.LocalesManager;
import com.dscfgos.admin.ShardsLocalesManager;
import com.dscfgos.admin.ShardsManager;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.classes.dtos.ShardDTO;
import com.dscfgos.ws.classes.wrappers.Locales;
import com.dscfgos.ws.classes.wrappers.Shards;
import com.dscfgos.ws.classes.wrappers.Shards_Locales;
import com.dscfgos.ws.manager.StatusManager;
import com.google.gson.Gson;

@Path("/status")
public class ShardsWS 
{
	Gson gson = new Gson();

	@GET
	@Path("/shards")
	@Produces(MediaType.APPLICATION_JSON)
	public String getShards()
	{
		List<Shards> shards = ShardsManager.getAllShards();
		
		List<ShardDTO> result = new ArrayList<>();
		
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
				
				ShardDTO shardDTO = new ShardDTO();
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
	
	@GET
	@Path("/shard")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQShardStatus(@QueryParam("region") String region)
	{
		return gson.toJson(StatusManager.getShardStatus(Region.getRegionById(region)));
	}
	
	@GET
	@Path("/shard/{region}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPShardStatus(@PathParam("region") String region)
	{
		return gson.toJson(StatusManager.getShardStatus(Region.getRegionById(region)));
	}
}
