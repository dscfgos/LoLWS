package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.api.model.constants.Region;
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
		return gson.toJson(StatusManager.getShards());
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
