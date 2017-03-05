package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import model.executable.command.Command;
import model.executable.command.CustomizedCommand;
import model.executable.command.Definition;
import util.Constants;
import util.RegexParser;
import util.SLogoObservable;

public class CommandPool extends SLogoObservable<Map<String, Command>>{
    
    public static final String DEFAULT_LANGUAGE_SUBPACKAGE = "languages/";
    public static final String DEFAULT_CLASSPATH_FILE = "Classpath";
    public static final String DEFAULT_LANGUAGE = "English";
    
    private Map<String, CustomizedCommand> userCommands;
    private Map<String, Integer> myDefinitions;
    private RegexParser commandParser;
    
    public CommandPool() {
        userCommands = new HashMap<>();
        myDefinitions = new HashMap<>();
        commandParser = new RegexParser();
        setLanguage(DEFAULT_LANGUAGE);
    }
    
    public void add(String name, CustomizedCommand command) {
        userCommands.put(name, command);
    }
    
    public void define(String name, int numParams) {
        myDefinitions.put(name, numParams);
    }
    
    public Command getCommand(String name) {
        String command = commandParser.getSymbol(name);
        if(command.equals(RegexParser.NO_MATCH)) {
            if(userCommands.containsKey(name)) {
                return userCommands.get(name).copy();
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
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    
    void setLanguage(String language) {
        commandParser.setPattern(DEFAULT_LANGUAGE_SUBPACKAGE + language);
    }
    
    Map<String, Command> getUserCommands() {
        return notification();
    }

    @Override
    protected Map<String, Command> notification() {
        return Collections.unmodifiableMap(userCommands);
    }
}
