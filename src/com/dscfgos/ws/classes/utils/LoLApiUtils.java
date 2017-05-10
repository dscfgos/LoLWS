package com.dscfgos.ws.classes.utils;
import java.util.logging.Level;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.RiotApiV3;



public class LoLApiUtils 
{
	private static final String apiKey = "RGAPI-1fea13ea-65f0-4839-b073-6ca782e43597";
	private static final String tournamentApiKey = "RGAPI-1fea13ea-65f0-4839-b073-6ca782e43597";

	public static RiotApiV3 getRiotApi() {
		ApiConfig config = new ApiConfig().setDebugLevel(Level.FINEST).setKey(apiKey).setTournamentKey(tournamentApiKey);
		return new RiotApiV3(config);
	}
}
