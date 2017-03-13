package com.dscfgos.ws.manager;

import java.util.ArrayList;
import java.util.List;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.ws.classes.utils.LoLApiUtils;

public class StaticDataManager 
{
	public static List<String> getDataLanguagesByRegion(Region region)
	{
		List<String> result = new ArrayList<>();
		
		try 
		{
			result = LoLApiUtils.getRiotApi().getDataLanguages(region);
		} 
		catch (RiotApiException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
