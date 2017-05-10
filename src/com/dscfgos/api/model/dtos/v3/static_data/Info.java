package com.dscfgos.api.model.dtos.v3.static_data;

/**
 * @author dscfgos
 * @version 2.0
 * @category STATIC-DATA-V3
 * <br />
 * This object contains champion information. 
 */
public class Info 
{
	private int difficulty ;	
	private int attack ;	
	private int defense ;	
	private int magic ;
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getMagic() {
		return magic;
	}
	public void setMagic(int magic) {
		this.magic = magic;
	}
	
	
	
}
