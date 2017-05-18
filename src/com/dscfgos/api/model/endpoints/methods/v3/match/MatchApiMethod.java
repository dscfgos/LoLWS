/*
 * Copyright 2017 dscfgos
 */
package com.dscfgos.api.model.endpoints.methods.v3.match;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.ApiMethod;

abstract public class MatchApiMethod extends ApiMethod {

	protected MatchApiMethod(ApiConfig config) {
		super(config, "match");
	}
}