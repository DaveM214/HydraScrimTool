package com.hydraScrimTool.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydraScrimTool.common.AppConstants;
import com.hydraScrimTool.model.planetside.Outfit;
import com.hydraScrimTool.model.planetside.Player;

public class RestfulQuestioner {

	private String serviceID = AppConstants.HYDRA_SERVICE;

	public RestfulQuestioner() {

	}

	/**
	 * Optional of an outfit from its tag. Optional wraps an outfit object if it
	 * is found otherwise it wraps null.
	 * 
	 * @param tag
	 *            Tag of the outfit
	 * @return Optional of outfit.
	 */
	public Optional<Outfit> findOutfit(String tag) {
		// Handling for empty string as this will return an outfit with the
		// empty tag
		if (StringUtils.equals("", tag)) {
			tag = "invalid";
		}

		String queryString = "outfit/?alias=";
		try {
			String result = sendGetRequest(queryString + tag);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode object = mapper.readTree(result);
			JsonNode numReturned = object.get("returned");
			if (numReturned.asInt() == 0) {
				return Optional.ofNullable(null);
			} else {
				Outfit outfit = new Outfit(result);
				return Optional.ofNullable(outfit);
			}
		} catch (IOException e) {
			return Optional.ofNullable(null);
		}
	}

	public Optional<Player> getPlayerByName(String name) {
		String queryString = "character/?name.first_lower=";
		try {
			String result = sendGetRequest(queryString + name.toLowerCase());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode object = mapper.readTree(result);
			JsonNode numReturned = object.get("returned");
			if (numReturned.asInt() == 0) {
				// Just return an empty list if none found
				return Optional.ofNullable(null);
			} else {
				JsonNode jsonPlayer = object.get("character_list").get(0);
				Player player = new Player(jsonPlayer);
				return Optional.of(player);
			}
		} catch (IOException e) {
			return Optional.ofNullable(null);
		}
	}

	public List<Player> getOnlinePlayers(Outfit outfit) {
		String queryString = "";
		try {
			String result = sendGetRequest(queryString + outfit.getOutfitTag());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode object = mapper.readTree(result);
			int numReturned = object.get("returned").asInt();
			if (numReturned == 0) {
				return new ArrayList<Player>();
			} else {
				List<Player> players = new ArrayList<Player>();
				for(int i = 0; i< numReturned ; i++){
					players.add(new Player(object.get("THE_NAME_OF_THE_LIST").get(i).asText()));
				}
				return players;
			}
		} catch (IOException e) {
			return null;
		}
	}
	
	public boolean isPlayerOnline(Player player) {
		String queryString = "characters_online_status/?character_id=";
		try {
			String result = sendGetRequest(queryString + player.getId());
			ObjectMapper mapper = new ObjectMapper();
			JsonNode object = mapper.readTree(result);
			JsonNode numReturned = object.get("returned");
			if (numReturned.asInt() == 0) {
				// Return false if none found
				return false;
			} else {
				int online = object.get("characters_online_status_list").get(0).get("online_status").asInt();
				if(online > 0 ){
					return true;
				}else{
					return false;
				}
			}
		} catch (IOException e) {
			return false;
		}
	}

	private String sendGetRequest(String queryString) throws ClientProtocolException, IOException {
		String url = AppConstants.CENSUS_URL + "/" + serviceID + "/get/" + AppConstants.PS2_API + "/" + queryString;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(url);
		req.addHeader("User-Agent", AppConstants.APPLICATION_NAME);
		HttpResponse response = client.execute(req);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_NOT_FOUND) {
			return "";
		} else if (statusCode == HttpStatus.SC_OK) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} else {
			return "";
		}
	}

	

}
