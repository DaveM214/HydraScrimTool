package com.hydraScrimTool.model;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;

public class AliasModel implements Model{

	AliasDictionary dictionary;
	
	public AliasModel(){
		this.dictionary = new AliasDictionary();
	}

	public AliasDictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(AliasDictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public void addAlias(String characterName, String alias){
		dictionary.setAlias(characterName, alias);
	}
	
	public boolean removeAlias(String characterName){
		return dictionary.removePlayer(characterName);
	}
	
	public void parseAliasFile(File file) throws ConfigurationException{
		HierarchicalINIConfiguration iniConfObj = new HierarchicalINIConfiguration(file);	
		SubnodeConfiguration subConf = iniConfObj.getSection("");
		System.out.println(iniConfObj.getSections().toString());
		Iterator<String> iterator = subConf.getKeys();
		while(iterator.hasNext()){
			String key = iterator.next();
			addAlias(key, subConf.getString(key));
		}
	}
	
	public Optional<String> getAliasForPlayer(String playerName){
		return Optional.ofNullable(dictionary.getAliasForPlayer(playerName));
	}
	
	public Optional<List<String>> getPlayersFromAlias(String alias){
		return Optional.ofNullable(dictionary.getPlayersFromAlias(alias));
	}
	
	
	
}
