package com.dscfgos.api.model.endpoints.methods.v3.champion_mastery;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.champion_mastery.ChampionMastery;
/*
 * Copyright 2017 dscfgos
 */
public class GetChampionMastery extends ChampionMasteryApiMethod {

	public GetChampionMastery(ApiConfig config, Region region, long summonerId, long championId) {
		super(config);
		setRegion(region);
		setReturnType(ChampionMastery.class);
		setUrlBase(region.getV3Endpoint() + "champion-mastery/v3/champion-masteries/by-summoner/" + summonerId + "/by-champion/" + championId);
		addApiKeyParameter();
	}

}
