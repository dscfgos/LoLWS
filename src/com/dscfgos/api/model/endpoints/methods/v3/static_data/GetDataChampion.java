/*
 * Copyright 2017 dscfgos
 */

package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.ChampData;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.static_data.Champion;
import com.dscfgos.api.utils.Convert;

public class GetDataChampion extends StaticDataApiMethod {

	public GetDataChampion(ApiConfig config, Region region, int id, Locale locale, String version, ChampData... champData) {
		super(config);
		setRegion(region);
		setReturnType(Champion.class);
		setUrlBase(region.getV3Endpoint() + "static-data/v3/champions/" + id);

		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (champData[0] != null) {
			add(new UrlParameter("champData", Convert.joinString(",", (Object[]) champData)));
		}
		addApiKeyParameter();
	}
}