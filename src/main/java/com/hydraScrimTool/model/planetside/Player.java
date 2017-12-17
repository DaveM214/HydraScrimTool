package com.hydraScrimTool.model.planetside;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydraScrimTool.model.MatchLog;
import com.hydraScrimTool.model.net.RestfulQuestioner;

public class Player implements Comparable<Player> {

	private Outfit playerOutfit;
	private String name;
	private String alias;
	private String id;
	private int score;
	private boolean online;
	private boolean simpleAliasSet;
	private MatchLog playerLog;

	/**
	 * Constructor for Json string with online stauts
	 * 
	 * @param string
	 * @param b
	 */
	public Player(String jsonString, boolean b) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonObject = mapper.readTree(jsonString);
			this.id = jsonObject.get("character_id").asText();
			this.online = jsonObject.get("online_status").asInt() == 0 ? false : true;
			if(this.online == true) {
				this.name = new RestfulQuestioner().lookupName(this.id);
			}
			else {
				this.name = "";
			}
			this.playerLog = new MatchLog();
			this.alias = "";
			processSimpleAlias();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor to create a player based on a json string received from the API
	 * 
	 * @param jsonString
	 */
	public Player(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {

			JsonNode jsonObject = mapper.readTree(jsonString);

			this.id = jsonObject.get("character_id").asText();
			this.name = jsonObject.get("name").get("first").asText();
			this.playerLog = new MatchLog();
			this.alias = "";
			processSimpleAlias();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player(JsonNode jsonPlayer) {
		this.id = jsonPlayer.get("character_id").asText();
		this.name = jsonPlayer.get("name").get("first").asText();
		this.playerLog = new MatchLog();
		this.alias = "";
		processSimpleAlias();
		setOnlineWithCheck();
	}

	public void processSimpleAlias() {
		String processing = this.name;
		//Ignore all the ones with PSB at the start - These need to be done manually
		if(!processing.startsWith("PSB")){
			int firstXLocation = processing.indexOf("x");
			if(firstXLocation >= 0 && firstXLocation < 5){
				//Take off the prefix
				processing = processing.substring(firstXLocation+1);
				
				//Take off the last two for the faction
				alias = processing.substring(0, processing.length()-2);
				this.simpleAliasSet = true;
				return;
			}
		}
		this.simpleAliasSet =false;
	}

	public Outfit getPlayerOutfit() {
		return playerOutfit;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public void setPlayerOutfit(Outfit playerOutfit) {
		this.playerOutfit = playerOutfit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public MatchLog getPlayerLog() {
		return playerLog;
	}

	public boolean isOnline() {
		return this.online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public void setOnlineWithCheck() {
		this.online = performOnlineCheck();
	}

	public boolean performOnlineCheck() {
		RestfulQuestioner rq = new RestfulQuestioner();
		return rq.isPlayerOnline(this);
	}
	
	public boolean hasSimpleAlias() {
		return this.simpleAliasSet;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Player)) {
			return false;
		}

		Player playerObj = (Player) obj;

		// A player is the same as another IFF their names are the same
		if (this.getName().toLowerCase().equals(playerObj.getName().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Player o) {
		return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
	}

}
