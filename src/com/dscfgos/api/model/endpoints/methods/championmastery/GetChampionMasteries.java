package com.dscfgos.api.model.endpoints.methods.championmastery;

import java.util.List;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.champion_mastery.ChampionMastery;
import com.google.gson.reflect.TypeToken;

public class GetChampionMasteries extends ChampionMasteryApiMethod {

	public GetChampionMasteries(ApiConfig config, PlatformId platformId, long summonerId) {
		super(config);
		setRegion(Region.getRegionByPlatformId(platformId));
		setReturnType(new TypeToken<List<ChampionMastery>>() {
		}.getType());
		setUrlBase("https://" + platformId.getName() + ".api.pvp.net/championmastery/location/" + platformId.getId() + "/player/" + summonerId + "/champions");
		addApiKeyParameter();
	}

}
