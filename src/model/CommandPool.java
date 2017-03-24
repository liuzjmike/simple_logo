package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import model.executable.command.Command;
import model.executable.command.customize.Declaration;
import util.Constants;
import util.RegexParser;
import util.SLogoException;
import util.SLogoObservable;

/**
 * Instantiates commands according to the names given.
 * Manages all user defined commands and declarations.
 * @author Mike Liu
 * 
 */
public class CommandPool extends SLogoObservable<List<String>>{
    
    public static final String DEFAULT_LANGUAGE_SUBPACKAGE = "languages/";
    public static final String DEFAULT_CLASSPATH_FILE = "Classpath";
    public static final String DEFAULT_LANGUAGE = "English";
    
    private Map<String, Command> userCommands;
    private Map<String, Integer> myDefinitions;
    private RegexParser commandParser;
    private ResourceBundle myResources;
    
    private String myLanguage;
    
    public CommandPool() {
        userCommands = new HashMap<>();
        myDefinitions = new HashMap<>();
        commandParser = new RegexParser();
        myResources = ResourceBundle.getBundle(Constants.DEFAULT_RESOURCE_PACKAGE + DEFAULT_CLASSPATH_FILE);
        setLanguage(DEFAULT_LANGUAGE);
    }
    
    /**
     * Adds user defined commands into the current environment.
     * @param name
     * @param command
     */
    public void add(String name, Command command) {
        userCommands.put(name.toLowerCase(), command);
        myDefinitions.remove(name.toLowerCase());
        notifyObservers();
    }
    
    /**
     * Declares a command for later definition
     * @param name
     * @param numParams
     */
    public void declare(String name, int numParams) {
        myDefinitions.put(name.toLowerCase(), numParams);
        userCommands.remove(name);
    }
    
    /**
     * Creates an instance of a command with name <code>name</code> 
     * if it is a built-in command or it is defined in a library of 
     * commands or definitions. Otherwise throws an error.
     * @param name
     * @return
     */
    public Command getCommand(String name) {
        String command = commandParser.getSymbol(name);
        if(command.equals(RegexParser.NO_MATCH)) {
            return getCustomizedCommand(name);
        } else {
            return getBuiltInCommand(command);
        }
    }
    
    /**
     * Returns the user defined commands.
     * @return
     */
    public Map<String, Command> getUserCommand() {
    	return userCommands;
    }
    
    /**
     * Sets language of the commands.
     * @param language
     */
    void setLanguage(String language) {
        myLanguage = language;
        commandParser.setPattern(DEFAULT_LANGUAGE_SUBPACKAGE + language);
    }
    
    /**
     * Returns the language of the commands.
     * @return
     */
    String getLanguage() {
        return myLanguage;
    }
    
    /**
     * Returns user defined commands.
     * @return
     */
    List<String> getUserCommands() {
        return notification();
    }

    @Override
    protected List<String> notification() {
        List<String> ret = new ArrayList<>(userCommands.keySet());
        Collections.sort(ret);
        return Collections.unmodifiableList(ret);
    }
    
    private Command getCustomizedCommand(String name) {
        String key = name.toLowerCase();
        if(userCommands.containsKey(key)) {
            return userCommands.get(key).newInstance();
        }
        else if(myDefinitions.containsKey(key)) {
            return new Declaration(key, myDefinitions.get(key));
        }
        else {
            throw new SLogoException(SLogoException.INVALID_COMMAND);
        }
    }
    
    private Command getBuiltInCommand(String name) {
        Class<?> clazz;
        try {
            clazz = Class.forName(myResources.getString(name));
            return (Command)clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(name);
            throw new SLogoException(SLogoException.INSTANTIATION_ERROR, name);
        }
    }
}
