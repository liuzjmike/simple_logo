package controller;

import java.util.function.Function;

/**
 * A class can implement the ControlHandler class when it needs to 
 * delegate the control of the workspace.
 * The <code>apply</code> method inherited from Function is used to
 * execute user input
 * @author Mike Liu
 * 
 */
public interface ControlHandler extends Function<String, Double> {
	
	/**
	 * This method is called whenever the user chooses the preferred 
	 * language of the workspace.
	 * @param language
	 */
	public void setLanguage(String language);
	
	/**
	 * This method is called to create a new workspace that is separate
	 * from the current workspace.
	 */
	public void newWorkspace();
	
	/**
	 * This method is called to save the current settings of the workspace.
	 */
	public void saveWorkspace();
	
	/**
	 * This method is called when a user wants to load a previously saved 
	 * workspace setting. 
	 */
	public void loadWorkspace();
	
	/**
	 * This method is called whenever the user needs to save the user-defined
	 * functions and global variables into a library.
	 */
	public void saveLibrary();
	
	/**
	 * This method is called to load a previously saved library to the workspace.
	 */
	public void loadLibrary();
}
