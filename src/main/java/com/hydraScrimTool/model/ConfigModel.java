package com.hydraScrimTool.model;

import java.io.File;
import java.util.Optional;

import com.hydraScrimTool.model.net.RestfulQuestioner;
import com.hydraScrimTool.model.planetside.Base;
import com.hydraScrimTool.model.planetside.Outfit;

public class ConfigModel {

	public static final int DEFAULT_TIME = 300;
	
	private File scoreFile;
	//Represent time as number of seconds
	private int time;
	private Outfit team1;
	private Outfit team2;
	
	//This could change because of how rounds are done
	private Base base;
	private int worldID;
	private RestfulQuestioner restful;
	
	public ConfigModel(){
		this.time = DEFAULT_TIME;
		this.restful = new RestfulQuestioner();
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Optional<Outfit> getTeam1() {
		return Optional.ofNullable(team1);
	}

	public void setTeam1(Outfit team1) {
		this.team1 = team1;
	}

	public Optional<Outfit> getTeam2() {
		return Optional.ofNullable(team2);
	}

	public void setTeam2(Outfit team2) {
		this.team2 = team2;
	}

	public Optional<Base> getBase() {
		return Optional.ofNullable(base);
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public int getWorldID() {
		return worldID;
	}

	public void setWorldID(int worldID) {
		this.worldID = worldID;
	}

	public static int getDefaultTime() {
		return DEFAULT_TIME;
	}
	
	public Optional<File> getScoreFile() {
		return Optional.ofNullable(scoreFile);
	}

	public void setScoreFile(File scoreFile) {
		this.scoreFile = scoreFile;
	}
	
	public void initialiseScoreDocument(){
		
	}

	public boolean validateData() {
		return true;
	}
	
}
