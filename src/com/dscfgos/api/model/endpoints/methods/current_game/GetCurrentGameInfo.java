package com.dscfgos.api.model.endpoints.methods.current_game;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.current_game.CurrentGameInfo;

public class GetCurrentGameInfo extends CurrentGameApiMethod {

	public GetCurrentGameInfo(ApiConfig config, PlatformId platformId, long summonerId) {
		super(config);
		setRegion(Region.getRegionByPlatformId(platformId));
		setReturnType(CurrentGameInfo.class);
		setUrlBase(
				"https://" + platformId.getName() + ".api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/" + platformId.getId() + '/' + summonerId);
		addApiKeyParameter();
	}

}
