package com.dscfgos.api.model.endpoints.methods.tournament;

import java.util.HashMap;
import java.util.Map;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.request.RequestMethod;

public class CreateTournament extends TournamentApiMethod {

	public CreateTournament(ApiConfig config, String tournamentName, int providerId) {
		super(config);
		setMethod(RequestMethod.POST);
		setReturnType(Integer.class);
		setUrlBase("https://global.api.pvp.net/tournament/public/v1/tournament");
		addTournamentApiKeyParameter();
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", (tournamentName == null) ? "" : tournamentName);
		body.put("providerId", providerId);
		buildJsonBody(body);
	}

}
