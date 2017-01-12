package com.dscfgos.api.model.endpoints.methods.tournament;

import java.util.HashMap;
import java.util.Map;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.classes.managers.request.RequestMethod;
import com.dscfgos.api.model.constants.PickType;
import com.dscfgos.api.model.constants.SpectatorType;
import com.dscfgos.api.model.constants.TournamentMap;

public class UpdateTournamentCode extends TournamentApiMethod 
{
	public UpdateTournamentCode(ApiConfig config, String tournamentCode, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			long... allowedSummonerIds) {
		super(config);
		setMethod(RequestMethod.PUT);
		setUrlBase("https://global.api.pvp.net/tournament/public/v1/code/" + tournamentCode);
		addTournamentApiKeyParameter();
		Map<String, Object> body = new HashMap<String, Object>();
		if (mapType != null) {
			body.put("mapType", mapType);
		}
		if (pickType != null) {
			body.put("pickType", pickType);
		}
		if (spectatorType != null) {
			body.put("spectatorType", spectatorType);
		}
		if (allowedSummonerIds != null && allowedSummonerIds.length > 0) {
			body.put("allowedParticipants", allowedSummonerIds);
		}
		buildJsonBody(body);
	}

}
