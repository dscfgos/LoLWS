package com.dscfgos.api.model.endpoints.methods.league;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.QueueType;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.league.League;

public class GetChallengerLeague extends LeagueApiMethod {

	public GetChallengerLeague(ApiConfig config, Region region, QueueType queueType) {
		super(config);
		setRegion(region);
		setReturnType(League.class);
		setUrlBase(region.getEndpoint() + "/v2.5/league/challenger");
		add(new UrlParameter("type", queueType.name()));
		addApiKeyParameter();
	}

}
