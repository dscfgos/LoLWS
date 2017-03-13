package com.dscfgos.ws.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	@Path("/locales/{region}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPLocales(@PathParam("region") String region)
	{
		return gson.toJson(StaticDataManager.getDataLanguagesByRegion(Region.getRegionById(region)));
	}
}
