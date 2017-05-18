package com.dscfgos.ws.classes.dtos.history;

import java.util.List;

import com.dscfgos.ws.classes.dtos.BaseResultDTO;

public class GamesHistoryResultDTO extends BaseResultDTO 
{
	private static final long serialVersionUID = -7641331425268869841L;
	
	private List<HistoryGameDataDTO> historyGameDataList = null;
	public List<HistoryGameDataDTO> getHistoryGameDataList() {
		return historyGameDataList;
	}

	public void setHistoryGameDataList(List<HistoryGameDataDTO> historyGameDataList) {
		this.historyGameDataList = historyGameDataList;
	}


}
