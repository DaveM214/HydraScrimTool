package com.hydraScrimTool.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FilenameUtils;

import com.hydraScrimTool.model.MainPanelModel;
import com.hydraScrimTool.model.Model;
import com.hydraScrimTool.model.alias.AliasModel;
import com.hydraScrimTool.model.alias.AliasTableEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AliasController implements Controller {

	private static final String NO_SAVE_ERROR = "The file could not be saved, something went wrong. I personally blame Halospud";
	private static final String CONFIRM_MESSAGE = "Loading an alias file will overwrite any conflicting names. Continue?";
	private static final String TITLE = "Aliases";
	private static final String ALIAS_PARSE_ERROR = "There was an error loading the alias file, the file was probably not valid";
	private static final String SAVE_COMPLETE_MSG = "The file was saved successfully";

	private AliasModel aliasModel;
	private MainPanelModel mainModel;
	private Stage parentWindow;
	private ObservableList<AliasTableEntry> aliasTableEntries;

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
	private TableColumn<AliasTableEntry, String> characterColumn;

	@FXML
	private TableColumn<AliasTableEntry, String> aliasColumn;

	@FXML
	private TextField aliasFileField;

	@FXML
	private TextField characterTextField;

	@FXML
	private TextField aliasTextField;

	@FXML
	void initialize() {
		characterColumn.setCellValueFactory(cell -> cell.getValue().getNameProperty());
		aliasColumn.setCellValueFactory(cell -> cell.getValue().getAliasProperty());
		aliasTable.setItems(this.aliasTableEntries);
		aliasTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public AliasController() {
		this.aliasTableEntries = FXCollections.observableArrayList();
	}

	@FXML
	void handleSave(ActionEvent event) {
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleSelect(ActionEvent event) {
		Optional<File> file = getFile();
		if (file.isPresent() && showConfirmMessage(CONFIRM_MESSAGE)) {
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
		if (!(aliasTextField.getText().equals("") || characterTextField.getText().equals(""))) {
			aliasModel.addAlias(characterTextField.getText(), aliasTextField.getText());
			AliasTableEntry entry = new AliasTableEntry(characterTextField.getText(), aliasTextField.getText());
			aliasTableEntries.add(entry);
			aliasTextField.setText("");
			characterTextField.setText("");
		}
	}

	@FXML
	void handleRemove(ActionEvent event) {
		List<AliasTableEntry> selectedItems = aliasTable.getSelectionModel().getSelectedItems();

		for (AliasTableEntry selectedItem : selectedItems) {
			// Remove from dictionary
			aliasModel.getDictionary().removePlayer(selectedItem.getName());

			// Remove from table
			aliasTable.getItems().remove(selectedItem);
		}
	}

	/**
	 * Write the aliases to a file
	 * 
	 * @param event
	 */
	@FXML
	void handleFileSave(ActionEvent event) {
		Optional<File> file = getSaveFile();
		if (file.isPresent()) {
			boolean success = writeAliasFile(file.get());
			if (success) {
				showInfoMessage(SAVE_COMPLETE_MSG);
			}
		}
	}

	/**
	 * Write an alias dictionary to a file
	 * 
	 * @return
	 */
	private boolean writeAliasFile(File file) {
		// If the extension is not .alias then change it
		String path = file.getAbsolutePath();
		if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(".alias")) {
			// Slightly hacky way of doing this
			int index = path.lastIndexOf('.');
			index = index < 0 ? path.length() : index;
			path = path.substring(0, index) + ".alias";
		}

		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
			// Use the table rather than file because we can preserve case
			for (AliasTableEntry aliasTableEntry : aliasTableEntries) {
				writer.write(aliasTableEntry.getName() + "=" + aliasTableEntry.getAlias() + "\n");
			}
			return true;
		} catch (IOException e) {
			showErrorMessage(NO_SAVE_ERROR);
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
	}

	private Optional<File> getFile() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter extFilter = new ExtensionFilter("Alias Files (*.alias)", "*.alias");
		chooser.getExtensionFilters().add(extFilter);
		return Optional.ofNullable(chooser.showOpenDialog(parentWindow));
	}

	private Optional<File> getSaveFile() {
		FileChooser chooser = new FileChooser();
		return Optional.ofNullable(chooser.showSaveDialog(parentWindow));
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

	private void showInfoMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Information");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	public void initModel(Model model) {
		if (this.aliasModel == null) {
			this.aliasModel = (AliasModel) model;
		}
		updateTable();
	}

	/**
	 * Redraw the table
	 */
	private void updateTable() {
		aliasTableEntries.clear();
		Iterator<Entry<String, String>> it = aliasModel.getDictionary().getAliasMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> pair = it.next();
			aliasTableEntries.add(new AliasTableEntry(pair.getKey(), pair.getValue()));
		}
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
