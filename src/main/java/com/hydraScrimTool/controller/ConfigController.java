package com.hydraScrimTool.controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import com.hydraScrimTool.model.ConfigModel;
import com.hydraScrimTool.model.MainPanelModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConfigController {

	public static final String TITLE = "Configuration";
	private static final String TEXT_FIELD_FILL = "-fx-background-color: ";
	private static final String TEXT_FIELD_RED = "#e25353;";
	private static final String TEXT_FIELD_GREEN = "#65e53d;";
	private static final int OUTFIT_TAG_LENGTH = 4;
	private boolean valid;
	private ConfigModel configModel;
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
	private TextField timeLimitField;

	@FXML
	private TextField scoreFileField;

	@FXML
	private Button cancelButton;

	@FXML
	private Button saveButton;
	private MainPanelModel mainModel;

	@FXML
	void handleCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleClear(ActionEvent event) {
		if (showConfirmMessage("Are you sure you wish to clear all fields?")) {
			this.team1Field.clear();
			this.team1Field.setStyle(null);
			this.team2Field.clear();
			this.team2Field.setStyle(null);
			setDefaultTime();
			this.scoreFileField.clear();
		}
	}

	@FXML
	void handleUpdate(ActionEvent event) {
		boolean team1Valid = configModel.lookupOutfit(1, team1Field.getText());
		boolean team2Valid = configModel.lookupOutfit(2, team2Field.getText());
		boolean scoringFileParsed = configModel.initialiseScoreDocument();
		boolean validTime = configModel.validateMatchTime(timeLimitField.getText());

		String redFill = TEXT_FIELD_FILL + TEXT_FIELD_RED;
		String greenFill = TEXT_FIELD_FILL + TEXT_FIELD_GREEN;

		if (team1Valid) {
			team1Field.setStyle(greenFill);
		} else {
			team1Field.setStyle(redFill);
		}

		if (team2Valid) {
			team2Field.setStyle(greenFill);
		} else {
			team2Field.setStyle(redFill);
		}

		if (validTime) {
			timeLimitField.setStyle(greenFill);
		} else {
			timeLimitField.setStyle(redFill);
		}

		if (scoringFileParsed) {
			scoreFileField.setStyle(greenFill);
		} else {
			scoreFileField.setStyle(redFill);
		}

		if (team1Valid && team2Valid && scoringFileParsed && validTime) {
			this.saveButton.setDisable(false);
		}

	}

	@FXML
	void handleOpenFileChooser(ActionEvent event) {
		// The File Chooser can easily return null, use optional
		Optional<File> file = getFile();
		file.ifPresent(f -> scoreFileField.setText(f.getAbsolutePath()));
		file.ifPresent(f -> configModel.setScoreFile(f));
	}

	@FXML
	void handleSave(ActionEvent event) {
		this.mainModel.getCurrentMatch().setConfiguration(configModel.getTeam1().get(), configModel.getTeam2().get(),
				configModel.getTime(), configModel.getScoreSystem());
		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {
		assert team1Field != null : "fx:id=\"team1Field\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert team2Field != null : "fx:id=\"team2Field\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert timeLimitField != null : "fx:id=\"timeLimitField\" was not injected: check your FXML file 'ConfigDialog.fxml'.";
		assert scoreFileField != null : "fx:id=\"scoreFileField\" was not injected: check your FXML file 'ConfigDialog.fxml'.";

		addLengthLimiter(team1Field, OUTFIT_TAG_LENGTH);
		addLengthLimiter(team2Field, OUTFIT_TAG_LENGTH);
		this.valid = false;
		this.saveButton.setDisable(true);
	}

	private void addLengthLimiter(TextField textField, int length) {
		textField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (textField.getText().length() > length) {
					String s = textField.getText().substring(0, length);
					textField.setText(s);
				}
			}
		});
	}

	public void initConfigModel(ConfigModel model) {
		if (this.configModel == null) {
			this.configModel = model;
		}
	}

	public void initMainModel(MainPanelModel mainModel) {
		if (this.mainModel == null) {
			this.mainModel = mainModel;
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
		this.timeLimitField.setText(Integer.toString(ConfigModel.DEFAULT_TIME));
	}

}
