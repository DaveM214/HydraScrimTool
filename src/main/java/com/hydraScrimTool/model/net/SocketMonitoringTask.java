package com.hydraScrimTool.model.net;

import com.hydraScrimTool.model.EventStore;

import javafx.concurrent.Task;

public class SocketMonitoringTask extends Task<Boolean>{

	private boolean connected;
	private String commandString;
	private EventStore eventStore;
	
	public SocketMonitoringTask() {
		this.connected = false;
		this.commandString = "";
	}
	
	public synchronized void setCommandString(String command){
		this.commandString = command;
	}
	
	public synchronized boolean isConnected() {
		return connected;
	}
	
	@Override
	protected Boolean call() throws Exception {
		return true;
	}

	
	public void giveEventStore(EventStore eventStore) {
		this.eventStore = eventStore;
	}
	
	

	
}
