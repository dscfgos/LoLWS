package com.dscfgos.api.model.constants;

public enum Region 
{
	BR("BR1", "br1.api.riotgames.com", "br"),
	EUNE("EUNE1", "eun1.api.riotgames.com", "eune"),
	EUW("EUW1", "euw1.api.riotgames.com", "euw"),
	JP("JP1", "jp1.api.riotgames.com", "jp"),
	KR("KR", "kr.api.riotgames.com", "kr"),
	LAN("LA1", "la1.api.riotgames.com", "lan"),
	LAS("LA2", "la2.api.riotgames.com", "las"),
	NA("NA1", "na1.api.riotgames.com", "na"),
	OCE("OC1", "oc1.api.riotgames.com", "oce"),
	TR("TR1", "tr1.api.riotgames.com", "tr"),
	RU("RU", "ru.api.riotgames.com", "ru"),
	PBE("PBE1", "pbe1.api.riotgames.com", "pbe"),
	GLOBAL("GLOBAL", "global.api.riotgames.com", "global");

	private String id ;
	private String endpoint;
	private String region;
	
	Region(String id, String endpoint, String region) 
	{
        this.id 			= id;
        this.region 		= region;
        this.endpoint 		= endpoint;
    }
	
	public String getId() {
		return id;
	}
	public String getRegion() {
		return region;
	}
	public String getEndpoint() {
		return getEndpoint(true);
	}
	public String getV3Endpoint() {
		return getV3Endpoint(false);
	}
	
	
	public static Region getRegionByName(String name) 
	{
		Region result = null;
		for (Region region : Region.values()) 
		{
			if (region.getRegion().equals(name.toLowerCase())) 
			{
				result = region;
				break;
			}
		}
		
		return result;
	}
	
	public static Region getRegionById(String id) 
	{
		Region result = null;
		for (Region region : Region.values()) 
		{
			if (region.getId().equalsIgnoreCase(id)) 
			{
				result = region;
				break;
			}
		}
		
		return result;
	}

	public static Region getRegionByPlatformId(PlatformId platformId) 
	{
		return getRegionByName(platformId.getName());
	}
	
	@Deprecated
	public String getEndpoint(boolean withRegion) 
	{
		String url = "https://" + endpoint + "/api/lol/";
		if (withRegion) 
		{
			url += getRegion();
		}
		return url;
	}
	
	public String getV3Endpoint(boolean withRegion) 
	{
		String url = "https://" + endpoint + "/lol/";
		if (withRegion) 
		{
			url += getRegion();
		}
		return url;
	}

	@Override
	public String toString() {
		return getRegion();
	}
	
}
