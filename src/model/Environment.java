package model;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtlePool;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class Environment {
    
    public static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";
    public static final String NO_MATCH = "NO MATCH";
    
    private TurtlePool myPool;
    private SObservableOrderedMap<String, Command> myCommands;
    private SObservableOrderedMap<String, Literal> myVariables;
    private List<Entry<String, Pattern>> commandTable;
    
    public Environment() {
        myPool = new TurtlePool();
        myCommands = new SObservableOrderedMap<>();
        myVariables = new SObservableOrderedMap<>();
    }

    public TurtlePool getPool() {
        return myPool;
    }
    
    public Command getCommand(String name) throws Exception {
        String command = getCommandName(name);
        return myCommands.get(command.equals(NO_MATCH) ? name : command);
    }
    
    public Literal getVariable(String name) throws Exception {
        return myVariables.get(name);
    }
    
    void setLanguage(String language) {
        ResourceBundle resources = ResourceBundle.getBundle(language);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            commandTable.add(new SimpleEntry<>(key, 
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
    
    List<Entry<String, Command>> getCommands() {
        return myCommands.getAll();
    }
    
    List<Entry<String, Literal>> getVariables() {
        return myVariables.getAll();
    }
    
    void addCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myCommands.addObserver(so);
    }
    
    void removeCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myCommands.removeObserver(so);
    }
    
    void addVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myVariables.addObserver(so);
    }
    
    void removeVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myVariables.removeObserver(so);
    }
    
    private String getCommandName(String name) {
        for (Entry<String, Pattern> e : commandTable) {
            if (e.getValue().matcher(name).matches()) {
                return e.getKey();
            }
        }
        return NO_MATCH;
    }
}
