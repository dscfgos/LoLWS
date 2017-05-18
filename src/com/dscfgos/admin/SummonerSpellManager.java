package com.dscfgos.admin;

import com.dscfgos.api.model.classes.managers.RiotApiException;
import com.dscfgos.api.model.constants.Region;
import com.dscfgos.api.model.constants.SpellData;
import com.dscfgos.api.model.dtos.v3.static_data.SummonerSpell;
import com.dscfgos.api.model.dtos.v3.static_data.SummonerSpellList;
import com.dscfgos.ws.classes.utils.LoLApiUtils;
import com.dscfgos.ws.factory.BaseWrapperFactory;

public class SummonerSpellManager 
{
	public static void insertAllSpells(Region region)
	{
		try 
		{
			SummonerSpellList result = LoLApiUtils.getRiotApi().getAllDataSummonerSpell(region,null, null, SpellData.IMAGE);
			if(result != null)
			{
				System.out.println("Count : " + result.getData().values().size());
				for (SummonerSpell spell : result.getData().values()) 
				{
					com.dscfgos.ws.classes.wrappers.SummonerSpell spellW = new com.dscfgos.ws.classes.wrappers.SummonerSpell();
					spellW.setId(spell.getId());
					spellW.setName(spell.getKey());
					spellW.setImage(spell.getImage().getFull());
					
					System.out.println("Inserting : " + spell.getId());
					
					BaseWrapperFactory<com.dscfgos.ws.classes.wrappers.SummonerSpell> baseWrapper = new BaseWrapperFactory<>();
					baseWrapper.addItem(com.dscfgos.ws.classes.wrappers.SummonerSpell.class, spellW);
				}
			}
		} 
		catch (RiotApiException e) 
		{
		
			e.printStackTrace();
		}

	}
}	
