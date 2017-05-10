package com.dscfgos.api.model.dtos.v3.static_data;

import java.util.List;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains spell vars data.
 */
public class SpellVars 
{
	private String       ranksWith;
	private String       dyn;
	private String       link;
	private List<Double> coeff;
	private String       key;

	public List<Double> getCoeff() {
		return coeff;
	}
	public void setCoeff(List<Double> coeff) {
		this.coeff = coeff;
	}
	public String getDyn() {
		return dyn;
	}
	public void setDyn(String dyn) {
		this.dyn = dyn;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRanksWith() {
		return ranksWith;
	}
	public void setRanksWith(String ranksWith) {
		this.ranksWith = ranksWith;
	}

}
