package controller;

import java.util.function.Consumer;

public interface ControlHandler extends Consumer<String> {
	
	public void setLanguage(String language);
	
	public String getLanguage();
}
