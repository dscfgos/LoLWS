/*
 * Copyright 2017 dscfgos
 */
package com.dscfgos.api.model.endpoints.methods.v3.spectator;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.spectator.CurrentGameInfo;

public class GetActiveGameInfo extends SpectatorApiMethod {

	public GetActiveGameInfo(ApiConfig config, Region region, long summonerId) {
		super(config);
		setRegion(region);
		setReturnType(CurrentGameInfo.class);
		setUrlBase(region.getV3Endpoint() + "spectator/v3/active-games/by-summoner/" + summonerId);
		addApiKeyParameter();
	}

}
