package com.hydraScrimTool.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

import com.hydraScrimTool.model.alias.AliasModel;
import com.hydraScrimTool.model.net.RestfulQuestioner;
import com.hydraScrimTool.model.net.SocketConnectionManager;
import com.hydraScrimTool.model.net.SocketMonitoringTask;
import com.hydraScrimTool.model.planetside.Player;

public class MainPanelModel {
	
	private ScoredMatch currentMatch;
	private RestfulQuestioner rQuestioner;
	private SocketConnectionManager socketConnectionManager;
	private SocketMonitoringTask socketTask;
	private AliasModel aliasModel;
	private TimerTask timer;
	private EventStore eventStore;
	
	public MainPanelModel(){
		this.currentMatch = new ScoredMatch();
		this.rQuestioner = new RestfulQuestioner();
		this.socketConnectionManager = new SocketConnectionManager();
		this.aliasModel = new AliasModel();
		this.eventStore = new EventStore();
		this.timer = new TimerTask();
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
		this.currentMatch.setMatchStarted(true);
		initialiseSocketConnections();
		new Thread(timer).start();
	}
	
	/**
	 * Open the socket connections to subscribe to the kill events for the characters concerned.
	 */
	private void initialiseSocketConnections() {
		List<String> playerIDs = new ArrayList<String>();
		Set<Player> players1 = currentMatch.getOutfit1().getPlayers();
		Set<Player> players2 = currentMatch.getOutfit2().getPlayers();
		getPlayerIDs(playerIDs, players1);	
		getPlayerIDs(playerIDs, players2);
		
		//Now we have all of the player ID's we can create a socket that will listen for these events.
		socketConnectionManager.setPlayerIDList(playerIDs);
		socketTask = socketConnectionManager.createMatchSocketConnection();
		socketTask.giveEventStore(eventStore);
	}

	private void getPlayerIDs(List<String> playerIDs, Set<Player> players1) {
		for (Player player : players1) {
			playerIDs.add(player.getId());
		}
	}

	public void pauseMatch() {
		timer.pauseTimer();
	}
	
	public TimerTask getTimer() {
		return this.timer;
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

	public void setTimer() {
		this.timer.setInitialTime(getCurrentMatch().getTimeLimit());
		
	}
	
	
}
