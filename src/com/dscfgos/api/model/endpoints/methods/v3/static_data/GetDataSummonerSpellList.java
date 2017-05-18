/*
 * Copyright 2017 dscfgos
 */

package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.v3.static_data.SummonerSpellList;
import com.dscfgos.api.utils.Convert;

public class GetDataSummonerSpellList extends StaticDataApiMethod {

	public GetDataSummonerSpellList(ApiConfig config, Region region, Locale locale, String version, boolean dataById, SpellData... spellData) {
		super(config);
		setRegion(region);
		setReturnType(SummonerSpellList.class);
		setUrlBase(region.getV3Endpoint() + "static-data/v3/summoner-spells");
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		add(new UrlParameter("dataById", dataById));
		if (spellData[0] != null) {
			add(new UrlParameter("spellListData", Convert.joinString(",", (Object[]) spellData)));
		}
		addApiKeyParameter();
	}
}