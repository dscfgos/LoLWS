package com.dscfgos.api.model.endpoints.methods.championmastery;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;

public class GetChampionMasteryScore extends ChampionMasteryApiMethod {

	public GetChampionMasteryScore(ApiConfig config, PlatformId platformId, long summonerId) {
		super(config);
		setRegion(Region.getRegionByPlatformId(platformId));
		setReturnType(Integer.class);
		setUrlBase("https://" + platformId.getName() + ".api.pvp.net/championmastery/location/" + platformId.getId() + "/player/" + summonerId + "/score");
		addApiKeyParameter();
	}

}
