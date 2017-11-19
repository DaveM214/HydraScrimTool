package com.hydraScrimTool.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 
 * @author david
 *
 */
public class AliasDictionary {

	private Map<String, String> aliasMap;
	private Map<String, List<String>> reverseMap;
	
	public AliasDictionary(){
		this.aliasMap = new HashMap<>();
		this.reverseMap = new HashMap<>();
	}
	
	public Map<String,String> getAliasMap(){
		return this.aliasMap;
	}
	
	public Map<String, List<String>> getReverseMap(){
		return this.reverseMap;
	}
	
	public String getAliasForPlayer(String playerName){
		return aliasMap.get(playerName);
	}
	
	/**
	 * Remove a character name and it's single alias from the mappings
	 * 
	 * @param player
	 * @return
	 */
	public boolean removePlayer(String player){
		return false;
	}
	
	public List<String> getPlayersFromAlias(String alias){
		return reverseMap.get(alias);
	}
	
	public void setAlias(String playerName, String alias){
		aliasMap.put(playerName, alias);
		
		//If we already have the alias in the reverse map then we add to the list
		if(reverseMap.containsKey(alias)){
			//TODO code to set this
		}
		//Otherwise make a new set
		else{
			reverseMap.put(alias, new ArrayList<String>(Arrays.asList(playerName)));
		}
	}
	
}
