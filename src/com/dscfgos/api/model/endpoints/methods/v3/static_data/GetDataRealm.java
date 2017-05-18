/*
 * Copyright 2017 dscfgos
 */

package com.dscfgos.api.model.endpoints.methods.v3.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.static_data.Realm;

public class GetDataRealm extends StaticDataApiMethod {

	public GetDataRealm(ApiConfig config, Region region) {
		super(config);
		setRegion(region);
		setReturnType(Realm.class);
		setUrlBase(region.getV3Endpoint() + "static-data/v3/realms");
		addApiKeyParameter();
	}
}