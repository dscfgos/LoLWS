package com.dscfgos.rest_ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.dscfgos.manager.SummonerManager;
import com.google.gson.Gson;

@Path("/summoner")
public class SummonerWS 
{
	Gson gson = new Gson();

	@GET
	@Path("/users/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSummonerByName(@PathParam("name") String name)
	{
		return gson.toJson(SummonerManager.getSummonerByName(name));
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSummonerByName2(@QueryParam("name") String name)
	{
		return gson.toJson(SummonerManager.getSummonerByName(name));
	}
}
