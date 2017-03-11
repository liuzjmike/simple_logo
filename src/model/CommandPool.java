package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import model.executable.command.Command;
import model.executable.command.Definition;
import util.Constants;
import util.RegexParser;
import util.SLogoException;
import util.SLogoObservable;

public class CommandPool extends SLogoObservable<List<String>>{
    
    public static final String DEFAULT_LANGUAGE_SUBPACKAGE = "languages/";
    public static final String DEFAULT_CLASSPATH_FILE = "Classpath";
    public static final String DEFAULT_LANGUAGE = "English";
    
    private Map<String, Command> userCommands;
    private Map<String, Integer> myDefinitions;
    private RegexParser commandParser;
    
    private String myLanguage;
    
    public CommandPool() {
        userCommands = new HashMap<>();
        myDefinitions = new HashMap<>();
        commandParser = new RegexParser();
        setLanguage(DEFAULT_LANGUAGE);
    }
    
    public void add(String name, Command command) {
        userCommands.put(name, command);
        myDefinitions.remove(name);
        notifyObservers();
    }
    
    public void define(String name, int numParams) {
        myDefinitions.put(name, numParams);
        userCommands.remove(name);
    }
    
    public Command getCommand(String name) {
        String command = commandParser.getSymbol(name);
        if(command.equals(RegexParser.NO_MATCH)) {
            if(userCommands.containsKey(name)) {
                return userCommands.get(name).newInstance();
            }
            else if(myDefinitions.containsKey(name)) {
                return new Definition(name, myDefinitions.get(name));
            }
            else {
                throw new SLogoException(SLogoException.INVALID_COMMAND);
            }
        }
        ResourceBundle resources = ResourceBundle.getBundle(Constants.DEFAULT_RESOURCE_PACKAGE
                + DEFAULT_CLASSPATH_FILE);
        Class<?> clazz;
        try {
            clazz = Class.forName(resources.getString(command));
            return (Command)clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(command);
            throw new SLogoException(SLogoException.INSTANTIATION_ERROR);
        }
    }
    
    public Map<String, Command> getUserCommand() {
    	return userCommands;
    }
    
    void setLanguage(String language) {
        myLanguage = language;
        commandParser.setPattern(DEFAULT_LANGUAGE_SUBPACKAGE + language);
    }
    
    String getLanguage() {
        return myLanguage;
    }
    
    List<String> getUserCommands() {
        return notification();
    }

    @Override
    protected List<String> notification() {
        List<String> ret = new ArrayList<>(userCommands.keySet());
        Collections.sort(ret);
        return Collections.unmodifiableList(ret);
    }
}
