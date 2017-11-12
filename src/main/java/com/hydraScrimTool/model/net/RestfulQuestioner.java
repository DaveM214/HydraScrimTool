package com.hydraScrimTool.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class RestfulQuestioner {

	private String serviceID = AppConstants.EXAMPLE_SERVICE;

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
		//Handling for empty string as this will return an outfit with the empty tag
		if(StringUtils.equals("", tag)){
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
