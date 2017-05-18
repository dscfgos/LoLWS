package com.dscfgos.api.model.dtos.v3.static_data;

import java.util.Map;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains rune list data.
 */
public class RuneList 
{
    private Map<String, Rune> data;
	private String            version;
	private String            type;
	private BasicData         basic;
	
	public BasicData getBasic() {
		return basic;
	}
	public void setBasic(BasicData basic) {
		this.basic = basic;
	}
	public Map<String, Rune> getData() {
		return data;
	}
	public void setData(Map<String, Rune> data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
    
}
