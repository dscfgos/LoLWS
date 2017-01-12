package com.dscfgos.api.model.endpoints.methods.champion;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.champion.ChampionList;

public class GetChampions extends ChampionApiMethod {

	public GetChampions(ApiConfig config, Region region, boolean freeToPlay) {
		super(config);
		setRegion(region);
		setReturnType(ChampionList.class);
		setUrlBase(region.getEndpoint(true) + "/v1.2/champion");
		if (freeToPlay) {
			add(new UrlParameter("freeToPlay", freeToPlay));
		}
		addApiKeyParameter();
	}

}
