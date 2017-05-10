package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.manager.StaticDataManager;
import com.google.gson.Gson;

@Path("/static-data")
public class StaticDataWS 
{
	Gson gson = new Gson();

	@GET
	@Path("/locales")
	@Produces(MediaType.APPLICATION_JSON)
	public String getQLocales(@QueryParam("region") String region)
	{
		return gson.toJson(StaticDataManager.getDataLanguagesByRegion(Region.getRegionById(region)));
	}
	
	@GET
	@Path("/champ")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChampionById(@QueryParam("id") int id, @QueryParam("locale") String locale, @QueryParam("region") int regionId)
	{
		return gson.toJson(StaticDataManager.getChampionById(id, regionId, Locale.getById(locale), "", true, ChampData.IMAGE));
	}
}
