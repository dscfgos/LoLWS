package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.ws.manager.CurrentGameManager;
import com.google.gson.Gson;

@Path("/current")
public class CurrentGameWS 
{
	Gson gson = new Gson();
	
	@GET
	@Path("/game")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrentGameInfoData(@QueryParam("region") int regionId, @QueryParam("id") long summonerId, @QueryParam("locale") String locale)
	{
		return gson.toJson(CurrentGameManager.getCurrentGameInfoByRegionAndSummonerId(regionId, summonerId,locale));
	}
}
