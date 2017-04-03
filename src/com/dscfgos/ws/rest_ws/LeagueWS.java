package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.ws.manager.LeagueManager;
import com.google.gson.Gson;

@Path("/league")
public class LeagueWS 
{
	Gson gson = new Gson();
	
	@GET
	@Path("/summoner")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSummonerLeagueData(@QueryParam("region") int regionId, @QueryParam("id") long id)
	{
		return gson.toJson(LeagueManager.getLeagueByRegionAndName(regionId,id));
	}
}
