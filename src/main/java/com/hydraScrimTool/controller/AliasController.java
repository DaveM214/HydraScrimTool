package com.hydraScrimTool.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.configuration.ConfigurationException;

import com.hydraScrimTool.model.AliasModel;
import com.hydraScrimTool.model.ConfigModel;
import com.hydraScrimTool.model.MainPanelModel;
import com.hydraScrimTool.model.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;



public class AliasController implements Controller{

	private static final String CONFIRM_MESSAGE = "Loading an alias file will overwrite any conflicting names. Continue?";
	private static final String TITLE = "Aliases";
	private static final String ALIAS_PARSE_ERROR = "There was an error loading the alias file, the file was probably not valid";
	
	private AliasModel aliasModel;
	private MainPanelModel mainModel;
	private Stage parentWindow;
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button removeButton;
	
	@FXML
	private Button fileSaveButton;
	
    @FXML
    private TableView<AliasTableEntry> aliasTable;

    @FXML
    private TextField aliasFileField;

    @FXML
	void initialize() {
    	
	}
    
    @FXML
    void handleSave(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void handleSelect(ActionEvent event) {
    	Optional<File> file = getFile();
		if(file.isPresent() && showConfirmMessage(CONFIRM_MESSAGE)){
			aliasFileField.setText(file.get().getAbsolutePath());
			try {
				aliasModel.parseAliasFile(file.get());
				updateTable();
			} catch (ConfigurationException e) {
				showErrorMessage(ALIAS_PARSE_ERROR);
			}
		}
    }
    

    @FXML
    void handleAdd(ActionEvent event) {
    	
    }

    @FXML
    void handleRemove(ActionEvent event) {
    	
    }

    @FXML
    void handleFileSave(ActionEvent event) {
    	
    }
	
	private Optional<File> getFile() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter extFilter = new ExtensionFilter("Alias Files (*.alias)", "*.alias");
		chooser.getExtensionFilters().add(extFilter);
		return Optional.ofNullable(chooser.showOpenDialog(parentWindow));
	}
	
	private void showErrorMessage(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
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
	
	public void initModel(Model model){
		if(this.aliasModel == null){
			this.aliasModel = (AliasModel) model;
		}
		updateTable();	
	}

	private void updateTable() {
		//TODO code for displaying the table
	}

	@Override
	public String getName() {
		return this.getClass().toString();
	}

	@Override
	public String getTitle() {
		return this.TITLE;
	}

	@Override
	public void initMainModel(MainPanelModel model) {
		this.mainModel = model;
	}
	
    
}
