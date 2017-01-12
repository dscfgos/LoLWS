package com.dscfgos.api.model.endpoints.methods.tournament;

import com.dscfgos.api.model.classes.managers.ApiConfig;
import com.dscfgos.api.model.dtos.tournament.TournamentCode;

public class GetTournamentCode extends TournamentApiMethod 
{
	public GetTournamentCode(ApiConfig config, String tournamentCode) {
		super(config);
		setReturnType(TournamentCode.class);
		setUrlBase("https://global.api.pvp.net/tournament/public/v1/code/" + tournamentCode);
		addTournamentApiKeyParameter();
	}

}
