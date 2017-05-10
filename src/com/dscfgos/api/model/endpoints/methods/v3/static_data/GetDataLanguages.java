/*
 * Copyright 2017 dscfgos
 */
package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import java.util.List;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.google.gson.reflect.TypeToken;

public class GetDataLanguages extends StaticDataApiMethod {

	public GetDataLanguages(ApiConfig config, Region region) {
		super(config);
		setRegion(region);
		setReturnType(new TypeToken<List<String>>() {
		}.getType());
		setUrlBase(region.getV3Endpoint() + "static-data/v3/languages");

		addApiKeyParameter();
	}
}