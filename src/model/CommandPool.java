package model;

import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import model.executable.command.Command;
import util.RegexParser;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class CommandPool {
    
    public static final String DEFAULT_LANGUAGE_SUBPACKAGE = "languages/";
    public static final String DEFAULT_CLASSPATH_FILE = "Classpath";
    
    private SObservableOrderedMap<String, Command> userCommands;
    private RegexParser commandParser;
    
    public CommandPool() {
        userCommands = new SObservableOrderedMap<>();
        commandParser = new RegexParser();
    }
    
    public void add(String name, Command command) {
        userCommands.put(name, command);
    }
    
    void setLanguage(String language) {
        commandParser.setPattern(DEFAULT_LANGUAGE_SUBPACKAGE + language);
    }
    
    Command getCommand(String name) throws Exception {
        String command = commandParser.getSymbol(name);
        if(command.equals(RegexParser.NO_MATCH)) {
            return userCommands.get(name);
        }
        ResourceBundle resources = ResourceBundle.getBundle(DEFAULT_CLASSPATH_FILE);
		Class<?> clazz = Class.forName(resources.getString(command));
		Command ret = (Command)clazz.newInstance();
        return ret;
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
