package com.dscfgos.api.model.dtos.v3.spectator;

/**
 * @author dscfgos
 * @version 2.0
 * @category SPECTATOR-V3
 */
public class Observer 
{
	private String encryptionKey ; // - Key used to decrypt the spectator grid game data for playback

	public String getEncryptionKey() {
		return encryptionKey;
	}
	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}
}
