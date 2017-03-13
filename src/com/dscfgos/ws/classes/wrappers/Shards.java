package com.dscfgos.ws.classes.wrappers;

/**
 * @author dscfgos
 */
public class Shards 
{
	private int 		id;
	private String      hostname;
    private String      name;
    private String      region_tag;
    private String      slug;
	
    public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegion_tag() {
		return region_tag;
	}
	public void setRegion_tag(String region_tag) {
		this.region_tag = region_tag;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
}
