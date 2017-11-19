package com.hydraScrimTool.controller;

import com.hydraScrimTool.model.MainPanelModel;
import com.hydraScrimTool.model.Model;

public interface Controller {

	public String getName();
	public String getTitle();
	public void initModel(Model model);
	public void initMainModel(MainPanelModel model);
	
}
