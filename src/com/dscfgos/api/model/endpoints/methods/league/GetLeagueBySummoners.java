package com.dscfgos.api.model.endpoints.methods.league;

import java.util.List;
import java.util.Map;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.league.League;
import com.google.gson.reflect.TypeToken;

public class GetLeagueBySummoners extends LeagueApiMethod {

	public GetLeagueBySummoners(ApiConfig config, Region region, String summonerIds) {
		super(config);
		setRegion(region);
		setReturnType(new TypeToken<Map<String, List<League>>>() {
		}.getType());
		setUrlBase(region.getEndpoint() + "/v2.5/league/by-summoner/" + summonerIds);
		addApiKeyParameter();
	}
}
