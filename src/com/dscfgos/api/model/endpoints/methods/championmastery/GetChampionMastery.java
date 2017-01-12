package com.dscfgos.api.model.endpoints.methods.championmastery;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.champion_mastery.ChampionMastery;

public class GetChampionMastery extends ChampionMasteryApiMethod {

	public GetChampionMastery(ApiConfig config, PlatformId platformId, long summonerId, long championId) {
		super(config);
		setRegion(Region.getRegionByPlatformId(platformId));
		setReturnType(ChampionMastery.class);
		setUrlBase("https://" + platformId.getName() + ".api.pvp.net/championmastery/location/" + platformId.getId() + "/player/" + summonerId + "/champion/"
				+ championId);
		addApiKeyParameter();
	}

}
