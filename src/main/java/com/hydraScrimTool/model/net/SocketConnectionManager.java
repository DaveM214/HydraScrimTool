package com.hydraScrimTool.model.net;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SocketConnectionManager {

	private static final String SERVICE_KEY = "service";
	private static final String ACTION_KEY = "action";
	private static final String SUBSCRIBE_VALUE = "subsribe";
	private static final String EVENT_VALUE = "event";
	private static final String CHARACTER_KEY = null;
	private static final String EVENT_KEY = null;
	private static final String DEATH_VALUE = "death";
	List<String> ids;
	boolean idsSet;



	public SocketConnectionManager() {
		idsSet = false;
	}

	public SocketMonitoringTask createMatchSocketConnection() {
		if (idsSet) {
			SocketMonitoringTask task = new SocketMonitoringTask();
			task.setCommandString(createCommandString());
			return task;
		}else {
			return null;
		}
	}

	private String createCommandString() {
		
		JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
		ObjectNode root = jsonFactory.objectNode();
		root.put(SERVICE_KEY,EVENT_VALUE);
		root.put(ACTION_KEY,SUBSCRIBE_VALUE);
		ArrayNode charArray = root.putArray(CHARACTER_KEY);
		for (String id : ids) {
			charArray.add(id);
		}
		
		ArrayNode eventArray = root.putArray(EVENT_KEY);
		eventArray.add(DEATH_VALUE);
		
		return root.toString();
	}

	public void setPlayerIDList(List<String> ids) {
		this.ids = ids;
		idsSet = true;
	}

}
