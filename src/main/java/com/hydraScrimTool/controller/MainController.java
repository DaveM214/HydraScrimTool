package com.hydraScrimTool.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hydraScrimTool.model.MainPanelModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MainController {

	private static final String EXIT_CONFIRMATION = "Are you sure you want to exit?";
	private static final String NEW_MATCH_CONFIRMATION = "Are you sure you want to start a new match? This will delete the current one.";
	private static final String STOP_MATCH_CONFIRMATION = "Are you sure you want to stop the current match?";
	private static final String NOT_CONFIGURED_ERROR = "The match must be configured before it is started";

	private MainPanelModel model;

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
	void handleAddManualScore(ActionEvent event) {

	}

	@FXML
	void handleConfigure(ActionEvent event) {
		showConfigureDialog();
	}

	@FXML
	void handleManageAliases(ActionEvent event) {
		showAliasManagerDialog();
	}

	@FXML
	void handleMenuExit(ActionEvent event) {
		if(showConfirmMessage(EXIT_CONFIRMATION) == true ){
			System.exit(0);
		}
	}

	@FXML
	void handleNewMatch(ActionEvent event) {
		if(showConfirmMessage(NEW_MATCH_CONFIRMATION) == true ){
			createNewMatch();
		}
		
	}

	@FXML
	void handleStartMatch(ActionEvent event) {
		startMatch();
	}


	@FXML
	void handleStopMatch(ActionEvent event) {
		if(showConfirmMessage(STOP_MATCH_CONFIRMATION) == true ){
			stopMatch();
		}
	}
	
	@FXML 
	void handleNewRound(ActionEvent event){
		
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

	private void showConfigureDialog() {
		// TODO Auto-generated method stub
	}

	private void showAliasManagerDialog() {
		// TODO Auto-generated method stub

	}
	
	private void createNewMatch() {
		// TODO Auto-generated method stub
		
	}
	
	private void startMatch() {
		if(model.isCurrentMatchConfigured()){
			model.startMatch();
		}else{
			showErrorMessage(NOT_CONFIGURED_ERROR);
		}
	}
	
	private void stopMatch() {
		// TODO Auto-generated method stub
		
	}

}
