package com.dscfgos.api.model.constants;

public enum PlatformId 
{
	BR("BR1", "br"),
	EUNE("EUN1", "eune"),
	EUW("EUW1", "euw"),
	JP("JP1", "jp"),
	KR("KR", "kr"),
	LAN("LA1", "lan"),
	LAS("LA2", "las"),
	NA("NA1", "na"),
	OCE("OC1", "oce"),
	PBE("PBE1", "pbe"),
	RU("RU", "ru"),
	TR("TR1", "tr");

	private String id;
	private String name;

	PlatformId(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	public static PlatformId getPlatformByName(String name) 
	{
		PlatformId result = null;
		for (PlatformId region : PlatformId.values()) 
		{
			if (region.getName().equals(name.toLowerCase())) 
			{
				result = region;
				break;
			}
		}
		
		return result;
	}
}
