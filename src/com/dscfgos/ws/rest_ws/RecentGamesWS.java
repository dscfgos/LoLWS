package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.ws.manager.GamesHistoryManager;
import com.google.gson.Gson;

@Path("/recent")
public class RecentGamesWS 
{
	Gson gson = new Gson();
	
	@GET
	@Path("/games")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecentGamesData(@QueryParam("region") int regionId, @QueryParam("id") long accountId, @QueryParam("locale") String locale)
	{
		return gson.toJson(GamesHistoryManager.getGamesHistoryByRegionAndAccount(regionId, accountId, Locale.getById(locale)));
	}
}
