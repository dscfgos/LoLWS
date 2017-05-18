/*
 * Copyright 2017 dscfgos
 */

package com.dscfgos.api.model.endpoints.methods.v3.match;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.match.Match;

public class GetMatch extends MatchApiMethod {

	public GetMatch(ApiConfig config, Region region, long gameId) 
	{
		super(config);
		setRegion(region);
		setReturnType(Match.class);
		setUrlBase(region.getV3Endpoint() + "match/v3/matches/"+ gameId);
		addApiKeyParameter();
	}
}