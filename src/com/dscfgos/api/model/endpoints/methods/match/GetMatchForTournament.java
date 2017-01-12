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

package com.dscfgos.api.model.endpoints.methods.match;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.UrlParameter;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.match.MatchDetail;

public class GetMatchForTournament extends MatchApiMethod {

	public GetMatchForTournament(ApiConfig config, Region region, long matchId, String tournamentCode, boolean includeTimeline) {
		super(config);
		setRegion(region);
		setReturnType(MatchDetail.class);
		setUrlBase(region.getEndpoint() + "/v2.2/match/for-tournament/" + matchId);
		add(new UrlParameter("tournamentCode", tournamentCode));
		if (includeTimeline) {
			add(new UrlParameter("includeTimeline", includeTimeline));
		}
		addTournamentApiKeyParameter();
	}
}