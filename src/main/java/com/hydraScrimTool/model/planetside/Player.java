package com.hydraScrimTool.model.planetside;

import com.hydraScrimTool.model.MatchLog;

public class Player {

	private Outfit playerOutfit;
	private String name;
	private String alias;
	private String id;
	private int score;
	private MatchLog playerLog;
	
	public Player(Outfit outfit, String name){
		this.playerOutfit = outfit;
		this.name = name;
		this.playerLog = new MatchLog();
		
		//TODO Code in here to automatically analyse an "Easy" alias
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
	
	
	
	
	
}
