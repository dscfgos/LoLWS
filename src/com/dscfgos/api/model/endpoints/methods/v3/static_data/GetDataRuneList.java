/*
 * Copyright 2017 dscfgos
 */


package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.RuneListData;
import com.dscfgos.api.model.dtos.v3.static_data.RuneList;
import com.dscfgos.api.utils.Convert;

public class GetDataRuneList extends StaticDataApiMethod {

	public GetDataRuneList(ApiConfig config, Region region, Locale locale, String version, RuneListData... runeListData) {
		super(config);
		setRegion(region);
		setReturnType(RuneList.class);
		setUrlBase(region.getV3Endpoint() + "static-data/v3/runes");

		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (runeListData[0] != null) {
			add(new UrlParameter("runeListData", Convert.joinString(",", (Object[]) runeListData)));
		}
		addApiKeyParameter();
		
	}
}