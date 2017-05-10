package com.dscfgos.api.model.dtos.v3.static_data;

import java.util.List;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains champion recommended data.
 */
public class Recommended 
{
    private String      map;
	private List<Block> blocks;
	private String      champion;
    private String      title;
    private boolean     priority;
	private String      mode;
    private String      type;

    public List<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
	public String getChampion() {
		return champion;
	}
	public void setChampion(String champion) {
		this.champion = champion;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public boolean isPriority() {
		return priority;
	}
	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
