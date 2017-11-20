package com.hydraScrimTool.model.alias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.List;

/**
 * 
 * @author david
 *
 */
public class AliasDictionary {

	private Map<String, String> aliasMap;
	private Map<String, List<String>> reverseMap;

	/**
	 * Constructor - we'll use a treeMap and sacrifice a tiny bit of performance
	 * in order to get case insensitivty
	 */
	public AliasDictionary() {
		this.aliasMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		this.reverseMap = new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER);
	}

	public Map<String, String> getAliasMap() {
		return this.aliasMap;
	}

	public Map<String, List<String>> getReverseMap() {
		return this.reverseMap;
	}

	public String getAliasForPlayer(String playerName) {
		return aliasMap.get(playerName);
	}

	/**
	 * Remove a character name and its single alias from the mappings
	 * 
	 * @param player
	 * @return
	 */
	public void removePlayer(String player) {
		Optional<String> alias = Optional.ofNullable(aliasMap.remove(player));
		if (alias.isPresent()) {
			String aliasString = alias.get();
			reverseMap.get(aliasString).remove(player);
			if (reverseMap.get(aliasString).isEmpty()) {
				reverseMap.remove(aliasString);
			}
		}
	}

	public List<String> getPlayersFromAlias(String alias) {
		return reverseMap.get(alias);
	}

	public void setAlias(String playerName, String alias) {
		aliasMap.put(playerName, alias);

		// If we already have the alias in the reverse map then we add to the
		// list
		if (reverseMap.containsKey(alias)) {
			List<String> list = reverseMap.get(alias);
			list.add(playerName);
		}
		// Otherwise make a new set
		else {
			reverseMap.put(alias, new ArrayList<String>(Arrays.asList(playerName)));
		}
	}

}
