package com.hydraScrimTool.common.enums;

public enum FactionEnum {
	
	VS(1,"Vanu Sovereignty","#a8016b"),
	NC(2,"New Conglomerate","#5a98fc"),
	TR(3,"Terran Republic","#c40b0b");
	
	
	private final String fullName;
	private final int ApiID;
	private final String colourCode;
	
	private FactionEnum(int ApiID, String fullName, String colourCode){
		this.ApiID = ApiID;
		this.fullName = fullName;
		this.colourCode = colourCode;
	}
	
	public String getFullName(){
		return this.fullName;
	}
	
	public int getApiID(){
		return this.ApiID;
	}
	
	public String getcolorCode(){
		return this.colourCode;
	}
}
