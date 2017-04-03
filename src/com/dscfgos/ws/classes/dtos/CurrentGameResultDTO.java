package com.dscfgos.ws.classes.dtos;

public class CurrentGameResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private CurrentGameInfoDTO currentGame = null;
	public CurrentGameInfoDTO getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(CurrentGameInfoDTO currentGame) {
		this.currentGame = currentGame;
	}
	
}
