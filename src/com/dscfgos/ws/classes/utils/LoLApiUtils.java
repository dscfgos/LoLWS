package com.dscfgos.ws.classes.utils;
import java.util.logging.Level;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.RiotApi;
import com.dscfgos.api.model.classes.managers.RiotApiAsync;



public class LoLApiUtils 
{
	private static final String apiKey = "RGAPI-1fea13ea-65f0-4839-b073-6ca782e43597";
	private static final String tournamentApiKey = "RGAPI-1fea13ea-65f0-4839-b073-6ca782e43597";

	public static RiotApi getRiotApi() {
		ApiConfig config = new ApiConfig().setDebugLevel(Level.FINEST).setKey(apiKey).setTournamentKey(tournamentApiKey);
		return new RiotApi(config);
	}

	public static RiotApiAsync getRiotApiAsync() {
		return getRiotApi().getAsyncApi();
	}
}
