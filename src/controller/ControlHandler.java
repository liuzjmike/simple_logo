package controller;

import java.util.function.Function;

public interface ControlHandler extends Function<String, Double> {
	
	public void setLanguage(String language);
	
	public void newWorkspace();
	
	public void saveWorkspace();
	
	public void loadWorkspace();
}
