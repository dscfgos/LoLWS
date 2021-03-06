package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.ws.manager.SummonerManager;
import com.google.gson.Gson;

@Path("/summoner")
public class SummonerWS 
{
	Gson gson = new Gson();
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQSummonerByName(@QueryParam("region") int regionId, @QueryParam("summonerName") String summonerName)
	{
		return gson.toJson(SummonerManager.getSummonerByRegionAndName(regionId,summonerName));
	}
	
	@GET
	@Path("/usersids")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQSummonerById(@QueryParam("region") int regionId, @QueryParam("summonerId") String summonerId)
	{
		return gson.toJson(SummonerManager.getSummonerByRegionAndId(regionId,summonerId));
	}
	
	@GET
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateSummonerById(@QueryParam("region") int regionId, @QueryParam("summonerName") String summonerName)
	{
		return gson.toJson(SummonerManager.updateSummonerByRegionAndName(regionId,summonerName));
	}
}
