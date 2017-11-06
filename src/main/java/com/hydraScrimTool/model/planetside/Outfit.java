package com.hydraScrimTool.model.planetside;

public class Outfit {
	
	public String outfitName;
	public String outfitTag;
	public String censusAPIString;
	public int outfitScore;
	
	public Outfit(){
		
	}

	public String getOutfitName() {
		return outfitName;
	}

	public void setOutfitName(String outfitName) {
		this.outfitName = outfitName;
	}

	public String getOutfitTag() {
		return outfitTag;
	}

	public void setOutfitTag(String outfitTag) {
		this.outfitTag = outfitTag;
	}

	public String getCensusAPIString() {
		return censusAPIString;
	}

	public void setCensusAPIString(String censusAPIString) {
		this.censusAPIString = censusAPIString;
	}
	
	public int getScore(){
		return this.outfitScore;
	}
	
	public void setScore(int score){
		this.outfitScore = score;
	}
	
	public void incrementScore(int inc){
		this.outfitScore += inc;
	}
	
}
