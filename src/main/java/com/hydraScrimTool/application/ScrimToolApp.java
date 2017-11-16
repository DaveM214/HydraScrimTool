package com.hydraScrimTool.application;

import java.util.Observable;

import com.hydraScrimTool.controller.MainController;
import com.hydraScrimTool.model.MainPanelModel;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScrimToolApp extends Application{

	public static final String APPLICATION_NAME = "Hydra Scrim Tool";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPanel.fxml"));
		 Parent root = loader.load();
         Scene scene = new Scene(root);
         
         MainController mainController = loader.getController();
         MainPanelModel model = new MainPanelModel();
         mainController.initModel(model);
         mainController.giveStage(primaryStage);
         primaryStage.getIcons().add(new Image("/icons/hydraLogo.png"));
         primaryStage.setTitle(APPLICATION_NAME);
         primaryStage.setScene(scene);
         primaryStage.show();
	}
	
}
