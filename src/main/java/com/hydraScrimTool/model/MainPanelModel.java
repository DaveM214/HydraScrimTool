package com.hydraScrimTool.model;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

import com.hydraScrimTool.model.alias.AliasModel;
import com.hydraScrimTool.model.net.RestfulQuestioner;
import com.hydraScrimTool.model.net.SocketConnectionManager;
import com.hydraScrimTool.model.planetside.Player;

public class MainPanelModel {
	
	private ScoredMatch currentMatch;
	private RestfulQuestioner rQuestioner;
	private SocketConnectionManager socketConnectionManager;
	private AliasModel aliasModel;
	
	public MainPanelModel(){
		this.currentMatch = new ScoredMatch();
		this.rQuestioner = new RestfulQuestioner();
		this.socketConnectionManager = new SocketConnectionManager();
		this.aliasModel = new AliasModel();
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
	
	public void setAliasModel(AliasModel aliasModel){
		this.aliasModel = aliasModel;
	}

	public Model getAliasModel() {
		return (Model)this.aliasModel;
	}

	public void matchPlayersToAlias() {
		 Set<Player> team1Players = currentMatch.getOutfit1().getPlayers();
		 Set<Player> team2Players = currentMatch.getOutfit2().getPlayers();	 
		 assignPlayersAlias(team1Players);
		 assignPlayersAlias(team2Players);
	}

	private void assignPlayersAlias(Set<Player> team1Players) {
		for (Player player : team1Players) {
			Optional<String> alias = aliasModel.getAliasForPlayer(player.getName());
			//If there is an alias in the model then use that one
			if(alias.isPresent()) {
				player.setAlias(alias.get());
			}else if(!player.hasSimpleAlias()) {
				player.setAlias("");
			}else {
				player.processSimpleAlias();
			}
		}
	}
	
	
}
