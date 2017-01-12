package com.dscfgos.ws.manager;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.dtos.summoner.Summoner;
import com.dscfgos.ws.classes.utils.LoLApiUtils;

public class SummonerManager 
{
	public static Summoner getSummonerByName(String name)
	{
		Summoner result = new Summoner();
		
		try 
		{
			result = LoLApiUtils.getRiotApi().getSummonerByName(Region.LAS, name);
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
