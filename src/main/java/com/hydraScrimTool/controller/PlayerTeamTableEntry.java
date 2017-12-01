package com.hydraScrimTool.controller;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import com.hydraScrimTool.model.planetside.Player;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerTeamTableEntry {

	private Player player;
	private StringProperty nameProperty;
	private StringProperty onlineProperty;
	private StringProperty aliasProperty;
	
	private PlayerTeamTableEntry(){
		this.nameProperty = new SimpleStringProperty();
		this.onlineProperty = new SimpleStringProperty();
		this.aliasProperty = new SimpleStringProperty();
	}
	
	public PlayerTeamTableEntry(String name, String online, String alias) {
		this.nameProperty = new SimpleStringProperty(name);
		this.onlineProperty = new SimpleStringProperty(online);
		this.aliasProperty = new SimpleStringProperty(alias);
		this.player = null;
	}
	
	public PlayerTeamTableEntry(Player player){
		this.nameProperty = new SimpleStringProperty();
		this.onlineProperty = new SimpleStringProperty();
		this.aliasProperty = new SimpleStringProperty();
		
		this.player = player;
		nameProperty.set(player.getName());
		aliasProperty.set(player.getAlias());
		if(player.isOnline()){
			onlineProperty.set("Yes");
		}else{
			onlineProperty.set("No");
		}
	}
	
	public Optional<Player> getPlayer(){
		return Optional.ofNullable(this.player);
	}
	
	public StringProperty getNameProperty(){
		return this.nameProperty;
	}
	
	public StringProperty getOnlineProperty(){
		return this.onlineProperty;
	}
	
	public StringProperty getAliasProperty(){
		return this.aliasProperty;
	}
	
	public void setName(String name){
		this.nameProperty.set(name);
	}
	
	public void setAlias(String alias){
		this.nameProperty.set(alias);
	}
	
	public void setOnline(String online){
		this.onlineProperty.set(online);
	}
	
	public String getName(){
		return this.nameProperty.get();
	}
	
	public String getAlias(){
		return this.aliasProperty.get();
	}
	
	public String getOnline(){
		return this.onlineProperty.get();
	}
	
	
}
