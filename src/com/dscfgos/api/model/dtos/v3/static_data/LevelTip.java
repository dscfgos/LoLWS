package com.dscfgos.api.model.dtos.v3.static_data;

import java.util.List;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains champion level tip data.
 */
public class LevelTip 
{
	private List<String> effect;
	private List<String> label;
	public List<String> getEffect() {
		return effect;
	}
	public void setEffect(List<String> effect) {
		this.effect = effect;
	}
	public List<String> getLabel() {
		return label;
	}
	public void setLabel(List<String> label) {
		this.label = label;
	}

}
