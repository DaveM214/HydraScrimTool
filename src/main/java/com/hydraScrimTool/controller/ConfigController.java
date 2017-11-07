package com.hydraScrimTool.controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hydraScrimTool.model.ConfigModel;
import com.hydraScrimTool.model.MainPanelModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConfigController {

	public static final String TITLE = "Configuration";
	private ConfigModel model;
	private Stage parentWindow;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField team1Field;

	@FXML
	private TextField team2Field;

	@FXML
	private ComboBox<?> worldComboBox;

	@FXML
	private TextField baseField;

	@FXML
	private TextField timeLimitField;

	@FXML
	private TextField scoreFileField;
	
	@FXML
	private Button cancelButton;

	@FXML
	void handleCancel(ActionEvent event) {
		   Stage stage = (Stage) cancelButton.getScene().getWindow();
		   stage.close();
	}

	@FXML
	void handleClear(ActionEvent event) {
		if(showConfirmMessage("Are you sure you wish to clear all fields?")){
		this.team1Field.clear();
		this.team2Field.clear();
		this.baseField.clear();
		setDefaultTime();
		this.scoreFileField.clear();
		}
	}

	@FXML
	void handleOpenFileChooser(ActionEvent event) {
		//The File Chooser can easily return null, use optional
		Optional<File> file = getFile();
		file.ifPresent(f-> scoreFileField.setText(f.getAbsolutePath()));
		file.ifPresent(f -> model.setScoreFile(f));
	}

	@FXML
	void handleSave(ActionEvent event) {
		if(model.validateData()){
			
		}
	}

	@FXML
	void initialize() {
		assert team1Field != null : "fx:id=\"team1Field\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert team2Field != null : "fx:id=\"team2Field\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert worldComboBox != null : "fx:id=\"worldComboBox\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert baseField != null : "fx:id=\"baseField\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert timeLimitField != null : "fx:id=\"timeLimitField\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert scoreFileField != null : "fx:id=\"scoreFileField\" was not injected: check your FXML file 'ConfigDialog.fxml'.";

	}

	public void initModel(ConfigModel model) {
		if (this.model == null) {
			this.model = model;
		}
	}

	private Optional<File> getFile() {
		return Optional.ofNullable(new FileChooser().showOpenDialog(parentWindow));
	}
	
	private boolean showConfirmMessage(String msg) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	private void setDefaultTime() {
		// TODO Auto-generated method stub
		
	}

}
