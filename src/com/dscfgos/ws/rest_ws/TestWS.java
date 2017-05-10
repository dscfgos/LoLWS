package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
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
		//LeaguePositionResultDTO shards = LeagueManager.getLeaguesPositionsByRegionAndSummonerId(5, "2291487");
		Object result = null;
		try 
		{
			result = LoLApiUtils.getRiotApi().getDataChampion(Region.LAS, 412, Locale.ES_ES, null, ChampData.IMAGE);
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return gson.toJson(result);
		
	}

}
