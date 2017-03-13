package com.dscfgos.ws.classes.dtos;

import java.util.List;

import com.dscfgos.ws.classes.wrappers.Locales;

/**
 * @author dscfgos
 */
public class Shard 
{
	private int				id;
	private String       	hostname;
    private List<Locales> 	locales;
    private String       	name;
    private String       	region_tag;
    private String      	slug;
	
    public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public List<Locales> getLocales() {
		return locales;
	}
	public void setLocales(List<Locales> locales) {
		this.locales = locales;
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
