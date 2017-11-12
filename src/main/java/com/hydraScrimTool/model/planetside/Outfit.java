package com.hydraScrimTool.model.planetside;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Outfit {
	
	public String outfitName;
	public String outfitTag;
	public String censusId;
	public int outfitScore;
	
	public Outfit(){
		
	}

	/**
	 * Constructor to build an outfit from JSon using jackson
	 * @param jsonString
	 */
	public Outfit(String jsonString) {
		this.outfitScore = 0;
		
		//We will always need to get the first element of the outfit list returned
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonObject = mapper.readTree(jsonString);
			JsonNode jsonOutfit = jsonObject.get("outfit_list").get(0);
			
			//Map manually as the object and Json do match completely.
			this.outfitName = jsonOutfit.get("name").asText();
			this.censusId = jsonOutfit.get("outfit_id").asText();
			this.outfitTag = jsonOutfit.get("alias").asText();
			
			//Should never happen
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

	public String getCensusId() {
		return this.censusId;
	}

	public void setCensusId(String censusId) {
		this.censusId = censusId;
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
