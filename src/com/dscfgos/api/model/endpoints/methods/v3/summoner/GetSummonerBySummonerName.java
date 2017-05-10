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

package com.dscfgos.api.model.endpoints.methods.v3.summoner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.RiotApiV3;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.v3.summoner.Summoner;
import com.dscfgos.api.utils.Convert;
import com.google.gson.reflect.TypeToken;

public class GetSummonerBySummonerName extends SummonerApiMethod {

	public GetSummonerBySummonerName(ApiConfig config, Region region, String summonerName) 
	{
		super(config);
		setRegion(region);
		summonerName = Convert.normalizeSummonerName(summonerName);
		setReturnType(new TypeToken<Summoner>(){}.getType());
		try 
		{
			setUrlBase(region.getV3Endpoint() + "summoner/v3/summoners/by-name/" + URLEncoder.encode(summonerName, "UTF-8"));
		} 
		catch (UnsupportedEncodingException e) 
		{
			RiotApiV3.log.log(Level.SEVERE, "URL Encoding Failed", e);
		}
		addApiKeyParameter();
	}
}