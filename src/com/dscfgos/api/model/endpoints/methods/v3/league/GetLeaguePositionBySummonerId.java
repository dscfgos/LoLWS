package com.dscfgos.api.model.endpoints.methods.v3.league;

import java.util.Set;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.league.LeaguePosition;
import com.google.gson.reflect.TypeToken;

public class GetLeaguePositionBySummonerId extends LeagueApiMethod {

	public GetLeaguePositionBySummonerId(ApiConfig config, Region region, String accountId) 
	{
		super(config);
		setRegion(region);
		setReturnType(new TypeToken<Set<LeaguePosition>>() {
		}.getType());
		setUrlBase(region.getV3Endpoint() + "league/v3/positions/by-summoner/" + accountId);
		addApiKeyParameter();
	}
}