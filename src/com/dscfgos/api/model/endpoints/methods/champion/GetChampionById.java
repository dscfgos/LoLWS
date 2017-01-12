package com.dscfgos.api.model.endpoints.methods.champion;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.champion.Champion;

public class GetChampionById extends ChampionApiMethod {

	public GetChampionById(ApiConfig config, Region region, int id) {
		super(config);
		setRegion(region);
		setReturnType(Champion.class);
		setUrlBase(region.getEndpoint() + "/v1.2/champion/" + id);
		addApiKeyParameter();
	}

}
