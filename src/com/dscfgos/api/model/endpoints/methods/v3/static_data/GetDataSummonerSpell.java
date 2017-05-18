/*
 * Copyright 2017 dscfgos
 */


package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.v3.static_data.SummonerSpell;
import com.dscfgos.api.model.endpoints.methods.v3.static_data.StaticDataApiMethod;
import com.dscfgos.api.utils.Convert;

public class GetDataSummonerSpell extends StaticDataApiMethod {

	public GetDataSummonerSpell(ApiConfig config, Region region, int id, Locale locale, String version, SpellData... spellData) 
	{
		super(config);
		setRegion(region);
		setReturnType(SummonerSpell.class);
		setUrlBase(region.getV3Endpoint() + "static-data/v3/summoner-spells/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (spellData[0] != null) {
			add(new UrlParameter("spellData", Convert.joinString(",", (Object[]) spellData)));
		}
		addApiKeyParameter();
	}
}