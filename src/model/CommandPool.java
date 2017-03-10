package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import model.executable.command.Command;
import model.executable.command.Definition;
import util.Constants;
import util.RegexParser;
import util.SLogoException;
import util.SLogoObservable;

public class CommandPool extends SLogoObservable<Map<String, Command>>{
    
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
                throw new RuntimeException();
            }
        }
        ResourceBundle resources = ResourceBundle.getBundle(Constants.DEFAULT_RESOURCE_PACKAGE
                + DEFAULT_CLASSPATH_FILE);
        Class<?> clazz;
        try {
            clazz = Class.forName(resources.getString(command));
            return (Command)clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new SLogoException(SLogoException.INSTANTIATION_ERROR);
        }
    }
    
    void setLanguage(String language) {
        myLanguage = language;
        commandParser.setPattern(DEFAULT_LANGUAGE_SUBPACKAGE + language);
    }
    
    String getLanguage() {
        return myLanguage;
    }
    
    Map<String, Command> getUserCommands() {
        return notification();
    }

    @Override
    protected Map<String, Command> notification() {
        return Collections.unmodifiableMap(userCommands);
    }
}
