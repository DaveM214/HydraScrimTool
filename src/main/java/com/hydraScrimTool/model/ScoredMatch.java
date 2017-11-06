package com.hydraScrimTool.model;

import java.io.File;
import java.util.List;

import com.hydraScrimTool.model.planetside.Base;
import com.hydraScrimTool.model.planetside.Outfit;

public class ScoredMatch extends Match{

	private boolean configured;
	private ScoreSystem scoring;
	private Outfit outfit1;
	private Outfit outfit2;
	private MatchLog matchLog;
	private List<Round> rounds;
	
	public ScoredMatch(){
		this.configured = false;
	}

	public Outfit getOutfit1() {
		return outfit1;
	}

	public void setOutfit1(Outfit outfit1) {
		this.outfit1 = outfit1;
	}

	public Outfit getOutfit2() {
		return outfit2;
	}

	public void setOutfit2(Outfit outfit2) {
		this.outfit2 = outfit2;
	}
	
	public boolean isConfigured(){
		return configured;
	}
	
	public void setConfigured(boolean configured){
		this.configured =  configured;
	}
	
	public void setScoreSystem(File scoreFile){
		//Parse the scoreFile and make the object
	}
	
	public ScoreSystem getScoreSystem(){
		return this.scoring;
	}
	
	public MatchLog getMatchLog(){
		return this.matchLog;
	}
	
	public List<Round> getRounds(){
		return this.rounds;
	}
	
	
	
}
