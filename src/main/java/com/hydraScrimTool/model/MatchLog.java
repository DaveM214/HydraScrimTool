package com.hydraScrimTool.model;

import java.util.ArrayList;
import java.util.List;

public class MatchLog {

	List<String> logList;
	
	public  MatchLog(){
		this.logList = new ArrayList<>();
	}
	
	public void addEntryToList(String entry){
		//We'll probably want to do the formatting here
		logList.add(entry);
	}
	
}
