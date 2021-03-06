package com.hydraScrimTool.model;

import java.io.File;
import java.util.Optional;

import com.hydraScrimTool.model.net.RestfulQuestioner;
import com.hydraScrimTool.model.planetside.Base;
import com.hydraScrimTool.model.planetside.Outfit;

public class ConfigModel implements Model{

	public static final int DEFAULT_TIME = 300;

	private File scoreFile;
	private ScoreSystem scoreSystem;
	// Represent time as number of seconds
	private int time;
	private Outfit team1;
	private Outfit team2;

	// This could change because of how rounds are done
	private Base base;
	private int worldID;
	private RestfulQuestioner restful;

	public ConfigModel() {
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

	public boolean initialiseScoreDocument() {
		ScoreParser parser = new ScoreParser();
		this.scoreSystem = parser.parse(scoreFile);
		return true;
	}
	
	public ScoreSystem getScoreSystem(){
		return this.scoreSystem;
	}

	public boolean lookupOutfit(int i, String tag) {
		tag = tag.toUpperCase();
		Optional<Outfit> outfit = restful.findOutfit(tag);
		if (outfit.isPresent()) {
			if (i == 1) {
				team1 = outfit.get();
			} else {
				team2 = outfit.get();
			}
			return true;
		}
		return false;
	}

	
	/**
	 * Method that converts the text entered in the time field from mm:ss to seconds. Will also allow a raw value in seconds
	 * @param text Raw text
	 * @return Whether the time entered by the user was valid.
	 */
	public boolean validateMatchTime(String text) {
		boolean validTimeString = text.matches("(\\d{1,2}:\\d{2}|\\d+)");
		if(validTimeString) {
			//If we have a minute:seconds string
			if(text.contains(":")) {
				int minutes = Integer.parseInt(text.substring(0, text.lastIndexOf(":")));
				int seconds = Integer.parseInt(text.substring(text.lastIndexOf(":")+1));
				time = 60*minutes + seconds;
				return true;		
			}else {
				time = Integer.parseInt(text);
				return true;
			}
		}else {
			return false;
		}
	}

}
