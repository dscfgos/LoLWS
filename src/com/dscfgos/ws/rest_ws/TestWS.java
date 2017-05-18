package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dscfgos.admin.SummonerSpellManager;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.manager.GamesHistoryManager;
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
			 //Locale.getById(locale)
			//acount : 200185948
			//result = LoLApiUtils.getRiotApi().getDataChampion(Region.LAS, 412, Locale.ES_ES, null, ChampData.IMAGE);
			result = GamesHistoryManager.getGamesHistoryByRegionAndAccount(5, 200185948L, Locale.ES_ES);
			//SummonerSpellManager.insertAllSpells(Region.LAS);
			//result = LoLApiUtils.getRiotApi().getRecentGamesByAccountId(Region.LAS, 200185948);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return gson.toJson(result);
		
	}

}
