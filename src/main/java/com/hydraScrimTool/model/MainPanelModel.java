package com.hydraScrimTool.model;

import java.util.Optional;
import java.util.OptionalLong;

import com.hydraScrimTool.model.net.RestfulQuestioner;
import com.hydraScrimTool.model.net.SocketConnectionManager;

public class MainPanelModel {
	
	private ScoredMatch currentMatch;
	private RestfulQuestioner rQuestioner;
	private SocketConnectionManager socketConnectionManager;
	
	public MainPanelModel(){
		this.currentMatch = new ScoredMatch();
		this.rQuestioner = new RestfulQuestioner();
		this.socketConnectionManager = new SocketConnectionManager();
	}
	
	public void createNewMatch(){
		this.currentMatch = new ScoredMatch();
	}

	public ScoredMatch getCurrentMatch() {
		return currentMatch;
	}

	public RestfulQuestioner getrQuestioner() {
		return rQuestioner;
	}

	public SocketConnectionManager getSocketConnectionManager() {
		return socketConnectionManager;
	}
	
	public boolean isCurrentMatchConfigured(){
		return currentMatch.isConfigured();
	}

	public void startMatch() {
		
	}
	
	
}
