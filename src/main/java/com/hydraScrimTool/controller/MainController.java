package com.hydraScrimTool.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hydraScrimTool.model.ConfigModel;
import com.hydraScrimTool.model.MainPanelModel;
import com.hydraScrimTool.model.Model;
import com.hydraScrimTool.model.planetside.Outfit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
	private static final String ALIAS_FXML = "/fxml/AliasDialog.fxml";
	
	private MainPanelModel model;
	private Stage parentWindow;
	private List<Button> togglyButtons;
	private List<Button> playerControlButtons;

	public MainController() {

	}

	public void initModel(MainPanelModel model) {
		if (this.model == null) {
			this.model = model;
		}
		setControlAccess();
		setPlayerConfigButtons();
		resetTextFields();
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
	private Label factionLabel2;

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
	private Label outfit1TotalLabel;

	@FXML
	private Label outfit2TotalLabel;

	@FXML
	private Label timerLabel;

	@FXML
	private Button team1AddButton;

	@FXML
	private Button team1RemoveButton;

	@FXML
	private Button team1AddAllButton;

	@FXML
	private Button team1ClearButton;

	@FXML
	private Button team2AddButton;

	@FXML
	private Button team2RemoveButton;

	@FXML
	private Button team2AddAllButton;

	@FXML
	private Button team2ClearButton;


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
		try {
			showAliasManagerDialog();
		} catch (IOException e) {
			showErrorMessage(CONFIG_PANE_OPEN_ERROR);
			e.printStackTrace();
		}
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
		assert factionLabel2 != null : "fx:id=\"factioLabel2\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitLabel1 != null : "fx:id=\"outfitLabel1\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitScore1 != null : "fx:id=\"outfitScore1\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitScore2 != null : "fx:id=\"outfitScore2\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert outfitLabel2 != null : "fx:id=\"outfitLabel2\" was not injected: check your FXML file 'MainPanel.fxml'.";

		this.togglyButtons = new ArrayList<Button>(Arrays.asList(aliasButton, startButton, stopButton,
				manualScoreButton, nextRoundButton, resetRoundButton));

		this.playerControlButtons = new ArrayList<Button>(
				Arrays.asList(team1AddButton, team1AddAllButton, team1ClearButton, team1RemoveButton, team2AddButton,
						team2AddAllButton, team2ClearButton, team2RemoveButton));
	}

	//////////////////////////////////////////////////
	
	private void handleAddPlayer(Outfit outfit, TableView table) {
		
	}
	
	private void handleAddAll(Outfit outfit, TableView table){
		
	}
	
	private void handleRemove(Outfit outfit, TableView table){
		
	}
	
	private void handleClear(Outfit outfit, TableView table){
		
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

	private void showErrorMessage(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private void showConfigureDialog() throws IOException {
		Model configModel = new ConfigModel();
		showDialog(CONFIGURE_FXML,configModel);
		setControlAccess();
		setInformationFields();
		setPlayerConfigButtons();
	}
	
	private void showAliasManagerDialog() throws IOException {
		showDialog(ALIAS_FXML, model.getAliasModel());
		
	}
	
	private void showDialog(String FXML_PATH, Model model)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
		Parent root = loader.load();
		Controller controller = loader.getController();
		controller.initModel(model);
		controller.initMainModel(this.model);
		Stage stage = new Stage();
		stage.setTitle(controller.getTitle());
		stage.getIcons().add(new Image("/icons/hydraLogo.png"));
		stage.setScene(new Scene(root));
		stage.showAndWait();
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

	private void setControlAccess() {
		togglyButtons.stream().forEach(e -> e.setDisable(!model.isCurrentMatchConfigured()));
	}

	private void setPlayerConfigButtons() {
		playerControlButtons.stream().forEach(e -> e.setDisable(!model.isCurrentMatchConfigured()));
	}

	private void setInformationFields() {
		if (model.isCurrentMatchConfigured()) {
			outfitLabel1.setText("[" + model.getCurrentMatch().getOutfit1().getOutfitTag() + "]");
			outfitLabel2.setText("[" + model.getCurrentMatch().getOutfit2().getOutfitTag() + "]");
			outfitScore1.setText(Integer.toString(model.getCurrentMatch().getOutfit1().getScore()));
			outfitScore2.setText(Integer.toString(model.getCurrentMatch().getOutfit2().getScore()));
			team1TableLabel.setText(model.getCurrentMatch().getOutfit1().getOutfitName());
			team2TableLabel.setText(model.getCurrentMatch().getOutfit2().getOutfitName());
		}
	}

	public void resetTextFields() {
		outfitLabel1.setText("");
		outfitLabel2.setText("");
		factionLabel1.setText("");
		factionLabel2.setText("");
		outfitScore1.setText("0");
		outfitScore2.setText("0");
		outfit1TotalLabel.setText("0");
		outfit2TotalLabel.setText("0");
		team1TableLabel.setText("");
		team2TableLabel.setText("");
		timerLabel.setText("0:00");
	}

}
