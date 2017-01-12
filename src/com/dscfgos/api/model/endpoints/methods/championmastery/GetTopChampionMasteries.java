package com.dscfgos.api.model.endpoints.methods.championmastery;

import java.util.List;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.PlatformId;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.champion_mastery.ChampionMastery;
import com.google.gson.reflect.TypeToken;

public class GetTopChampionMasteries extends ChampionMasteryApiMethod {

	public GetTopChampionMasteries(ApiConfig config, PlatformId platformId, long summonerId, int count) {
		super(config);
		setRegion(Region.getRegionByPlatformId(platformId));
		setReturnType(new TypeToken<List<ChampionMastery>>() {
		}.getType());
		setUrlBase(
				"https://" + platformId.getName() + ".api.pvp.net/championmastery/location/" + platformId.getId() + "/player/" + summonerId + "/topchampions");
		if (count != -1) {
			add(new UrlParameter("count", count));
		}
		addApiKeyParameter();
	}

}
