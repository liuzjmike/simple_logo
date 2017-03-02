package controller;

import javafx.scene.paint.Color;
import model.SLogoModel;

public class GUIControlHandler {
	
	SLogoModel mySLogoModel;
	
	public GUIControlHandler(SLogoModel mySLogoModel) {
		this.mySLogoModel = mySLogoModel;
	}
	
	public void changeLanguage(String language) {
		mySLogoModel.setLanguage(language);
	}
}
