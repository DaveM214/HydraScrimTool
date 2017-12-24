package com.hydraScrimTool.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

public class TimerTask extends Task<Integer>{

	private int startValue = 1;
	private int currentTime;
	private boolean timerStarted = false;
	private boolean paused = false;
	
	public TimerTask() {
	
	}
	
	@Override
	protected Integer call() throws Exception {
		currentTime = startValue;
		timerStarted = true;
		
		while(currentTime > 0) {
			if(!paused) {
			Thread.sleep(1000);
			currentTime -= 1;
			setMessageProperty();
			System.out.println(currentTime);
			}else {
				System.out.println("PAUSED ");
				Thread.sleep(3000);
			}
		}
		
		return new Integer(currentTime);
	}
	
	private void setMessageProperty() {
		int time = getRemainingTime();
		int minutes = time / 60;
		int seconds = time % 60;
		String insert = seconds < 10 ? "0" : "";
		String friendlyString = minutes +":"+insert+seconds;
		this.updateMessage(friendlyString);
	}

	public synchronized void setInitialTime(int startValue){
		if(!timerStarted) {
			this.startValue = startValue;
		}
	}
	
	public synchronized boolean timerStarted() {
		return this.timerStarted;
	}
	
	public synchronized int getRemainingTime(){
		return currentTime;
	}
	
	public synchronized void pauseTimer() {
		this.paused = true;
	}
	
	public synchronized void unpauseTimer() {
		this.paused = false;
	}
	
}
