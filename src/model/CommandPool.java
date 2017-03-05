package model;

import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import model.executable.command.Command;
import util.Constants;
import util.RegexParser;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class CommandPool {
    
    public static final String DEFAULT_LANGUAGE_SUBPACKAGE = "languages/";
    public static final String DEFAULT_CLASSPATH_FILE = "Classpath";
    public static final String DEFAULT_LANGUAGE = "English";
    
    private SObservableOrderedMap<String, Command> userCommands;
    private RegexParser commandParser;
    
    public CommandPool() {
        userCommands = new SObservableOrderedMap<>();
        commandParser = new RegexParser();
        setLanguage(DEFAULT_LANGUAGE);
    }
    
    public void add(String name, Command command) {
        userCommands.put(name, command);
    }
    
    public Command getCommand(String name) {
        String command = commandParser.getSymbol(name);
        if(command.equals(RegexParser.NO_MATCH)) {
            return userCommands.get(name);
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
    
    List<Entry<String, Command>> getUserCommands() {
        return userCommands.getAll();
    }
    
    void addUserObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        userCommands.addObserver(so);
    }
    
    void removeObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        userCommands.removeObserver(so);
    }
}
