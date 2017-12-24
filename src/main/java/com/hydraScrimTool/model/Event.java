package com.hydraScrimTool.model;

import com.hydraScrimTool.model.planetside.Player;

public class Event {

	// The Player doing the killing
	private Player attacker;

	// The Player getting killed (RIP)
	private Player defender;

	// String for weapon - might need to change this into it's own thing for easier
	// access in tables
	private String weapon;

	// Score associated with this action
	private int score;

	private boolean isHeadShot;

	private String timeStamp;

	public Event() {

	}

	public Player getAttacker() {
		return attacker;
	}

	public void setAttacker(Player attacker) {
		this.attacker = attacker;
	}

	public Player getDefender() {
		return defender;
	}

	public void setDefender(Player defender) {
		this.defender = defender;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isHeadShot() {
		return isHeadShot;
	}

	public void setHeadShot(boolean isHeadShot) {
		this.isHeadShot = isHeadShot;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Convert the current value stored in the timestamp to something useful for
	 * the match log to use
	 * 
	 * @return
	 */
	private String usableDate() {
		//TODO method to convert stuff into a usable date
		return "";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(usableDate() + " ");
		//Check it wasn't suicide
		if(!attacker.equals(defender)){
			sb.append(attacker.getUsableName());
			sb.append(" killed ");
			sb.append(defender.getUsableName());
			sb.append("("+ weapon + ")" );
		}else {
			//Else it was suicide
			//TODO code to handle suicides.
		}
		
		if(isHeadShot) {
			sb.append("HEADSHOT!!");
		}
		
		return sb.toString();
	}

}
