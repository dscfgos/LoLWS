package com.dscfgos.api.model.dtos.v3.static_data;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains champion passive data. 
 */
public class Passive 
{
	private Image image ;	
	private String sanitizedDescription ;	
	private String name;
	private String description ;
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getSanitizedDescription() {
		return sanitizedDescription;
	}
	public void setSanitizedDescription(String sanitizedDescription) {
		this.sanitizedDescription = sanitizedDescription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
	
}
