package com.hydraScrimTool.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hydraScrimTool.model.ConfigModel;
import com.hydraScrimTool.model.MainPanelModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class MainController {

	private static final String CONFIG_PANE_OPEN_ERROR = "An error occured opening the configuration pane";
	private static final String EXIT_CONFIRMATION = "Are you sure you want to exit?";
	private static final String NEW_MATCH_CONFIRMATION = "Are you sure you want to start a new match? This will delete the current one.";
	private static final String STOP_MATCH_CONFIRMATION = "Are you sure you want to stop the current match?";
	private static final String NOT_CONFIGURED_ERROR = "The match must be configured before it is started";
	private static final String CONFIGURE_FXML = "/fxml/ConfigDialog.fxml";

	private MainPanelModel model;
	private Stage parentWindow;
	private List<Button> togglyButtons;

	public MainController() {
		
	}

	public void initModel(MainPanelModel model) {
		if (this.model == null) {
			this.model = model;
		}
	}

	//////////////////////////////////////////////////
	// FXML METHOD HANDLERS
	//////////////////////////////////////////////////

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label factionLabel1;

	@FXML
	private Label factioLabel2;

	@FXML
	private Label outfitLabel1;

	@FXML
	private Label outfitScore1;

	@FXML
	private Label outfitScore2;

	@FXML
	private Label outfitLabel2;

	@FXML
	private Label team1TableLabel;

	@FXML
	private Label team2TableLabel;

	@FXML
	private Button aliasButton;

	@FXML
	private Button startButton;

	@FXML
	private Button stopButton;

	@FXML
	private Button manualScoreButton;

	@FXML
	private Button nextRoundButton;

	@FXML
	private Button resetRoundButton;

	@FXML
	void handleAddManualScore(ActionEvent event) {

	}

	@FXML
	void handleConfigure(ActionEvent event) {
		try {
			showConfigureDialog();
		} catch (IOException e) {
			showErrorMessage(CONFIG_PANE_OPEN_ERROR);
			e.printStackTrace();
		}
	}

	@FXML
	void handleManageAliases(ActionEvent event) {
		showAliasManagerDialog();
	}

	@FXML
	void handleMenuExit(ActionEvent event) {
		if (showConfirmMessage(EXIT_CONFIRMATION) == true) {
			System.exit(0);
		}
	}

	@FXML
	void handleNewMatch(ActionEvent event) {
		if (showConfirmMessage(NEW_MATCH_CONFIRMATION) == true) {
			createNewMatch();
		}

	}

	@FXML
	void handleStartMatch(ActionEvent event) {
		startMatch();
	}

	@FXML
	void handleStopMatch(ActionEvent event) {
		if (showConfirmMessage(STOP_MATCH_CONFIRMATION) == true) {
			stopMatch();
		}
	}

	@FXML
	void handleNewRound(ActionEvent event) {

	}

	@FXML
	void handleTeam1Add(ActionEvent event) {

	}

	@FXML
	void handleTeam1AddAll(ActionEvent event) {

	}

	@FXML
	void handleTeam1Clear(ActionEvent event) {

	}

	@FXML
	void handleTeam1Remove(ActionEvent event) {

	}

	@FXML
	void handleTeam2Add(ActionEvent event) {

	}

	@FXML
	void handleTeam2AddAll(ActionEvent event) {

	}

	@FXML
	void handleTeam2Clear(ActionEvent event) {

	}

	@FXML
	void handleTeam2Remove(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert factionLabel1 != null : "fx:id=\"factionLabel1\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert factioLabel2 != null : "fx:id=\"factioLabel2\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitLabel1 != null : "fx:id=\"outfitLabel1\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitScore1 != null : "fx:id=\"outfitScore1\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitScore2 != null : "fx:id=\"outfitScore2\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitLabel2 != null : "fx:id=\"outfitLabel2\" was not injected: check your FXML file 'MainPanel.fxml'.";
		
		this.togglyButtons = new ArrayList<Button>(Arrays.asList(aliasButton, startButton, stopButton,
				manualScoreButton, nextRoundButton, resetRoundButton));

	}

	//////////////////////////////////////////////////

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

	private void showErrorMessage(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private void showConfigureDialog() throws IOException {
		FXMLLoader configLoader = new FXMLLoader(getClass().getResource(CONFIGURE_FXML));
		Parent root = configLoader.load();

		ConfigController configController = configLoader.getController();
		ConfigModel configModel = new ConfigModel();
		configController.initModel(configModel);
		Stage stage = new Stage();
		stage.setTitle(ConfigController.TITLE);
	    stage.getIcons().add(new Image("/icons/hydraLogo.png"));
		stage.setScene(new Scene(root));
		stage.showAndWait();
		setControlAccess();
	}

	private void showAliasManagerDialog() {
		// TODO Auto-generated method stub

	}

	private void createNewMatch() {
		// TODO Auto-generated method stub

	}

	private void startMatch() {
		if (model.isCurrentMatchConfigured()) {
			model.startMatch();
		} else {
			showErrorMessage(NOT_CONFIGURED_ERROR);
		}
	}

	private void stopMatch() {
		// TODO Auto-generated method stub

	}

	public void giveStage(Stage stage) {
		this.parentWindow = stage;
	}

	public void setControlAccess() {
		togglyButtons.stream().forEach(e -> e.setDisable(!model.isCurrentMatchConfigured()));
	}

}
