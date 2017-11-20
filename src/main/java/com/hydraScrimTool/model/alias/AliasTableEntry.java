package com.hydraScrimTool.model.alias;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AliasTableEntry {

	private String name;
	private StringProperty nameProperty;
	private StringProperty aliasProperty;

	public AliasTableEntry() {

	}

	public AliasTableEntry(String character, String alias) {
		this.name = character;
		this.nameProperty = new SimpleStringProperty(character);
		this.aliasProperty = new SimpleStringProperty(alias);
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(StringProperty nameProperty) {
		this.nameProperty = nameProperty;
	}

	public StringProperty getAliasProperty() {
		return aliasProperty;
	}

	public void setAliasProperty(StringProperty aliasProperty) {
		this.aliasProperty = aliasProperty;
	}

	public String getAlias() {
		return this.aliasProperty.get();
	}

	public String getName() {
		return this.name;
	}

	public void setAlias(String alias) {
		this.aliasProperty.setValue(alias);
	}

	public void setName(String name) {
		this.name = name;
		this.nameProperty.setValue(name);
	}



}
