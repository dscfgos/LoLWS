package com.dscfgos.manager;

import com.dscfgos.classes.utils.LoLApiUtils;
import com.dscfgos.lol.model.classes.api.RiotApiException;
import com.dscfgos.lol.model.constants.Region;
import com.dscfgos.lol.model.dtos.summoner.Summoner;

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
