/*
 * Copyright 2016 Taylor Caldwell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dscfgos.api.model.endpoints.methods.static_data;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Locale;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.RuneData;
import com.dscfgos.api.model.dtos.static_data.Rune;
import com.dscfgos.api.model.endpoints.methods.v3.static_data.StaticDataApiMethod;
import com.dscfgos.api.utils.Convert;

public class GetDataRune extends StaticDataApiMethod {

	public GetDataRune(ApiConfig config, Region region, int id, Locale locale, String version, RuneData... runeData) {
		super(config);
		setRegion(region);
		setReturnType(Rune.class);
		setUrlBase("https://global.api.pvp.net/api/lol/static-data/" + region + "/v1.2/rune/" + id);
		if (locale != null) {
			add(new UrlParameter("locale", locale));
		}
		if (version != null) {
			add(new UrlParameter("version", version));
		}
		if (runeData[0] != null) {
			add(new UrlParameter("runeData", Convert.joinString(",", (Object[]) runeData)));
		}
		addApiKeyParameter();
	}
}