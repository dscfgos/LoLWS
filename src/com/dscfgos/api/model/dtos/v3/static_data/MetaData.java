package com.dscfgos.api.model.dtos.v3.static_data;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains meta data. 
 */
public class MetaData 
{
	private String tier	;
	private String type	;	
	private boolean isRune ;
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isRune() {
		return isRune;
	}
	public void setRune(boolean isRune) {
		this.isRune = isRune;
	}
	
	

}
