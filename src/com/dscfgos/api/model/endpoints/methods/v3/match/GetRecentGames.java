/*
 * Copyright 2017 dscfgos
 */

package com.dscfgos.api.model.endpoints.methods.v3.match;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.match.Matchlist;

public class GetRecentGames extends MatchApiMethod {

	public GetRecentGames(ApiConfig config, Region region, long accountId) 
	{
		super(config);
		setRegion(region);
		setReturnType(Matchlist.class);
		setUrlBase(region.getV3Endpoint() + "match/v3/matchlists/by-account/"+ accountId +"/recent");
		addApiKeyParameter();
	}
}