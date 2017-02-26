package model;

import java.util.List;
import java.util.Map.Entry;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtlePool;
import util.RegexParser;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class Environment {
    
    public static final String DEFAULT_LANGUAGE_PACKAGE = "resources/languages/";
    public static final String NO_MATCH = "NO MATCH";
    
    private TurtlePool myPool;
    private SObservableOrderedMap<String, Command> myCommands;
    private SObservableOrderedMap<String, Literal> myVariables;
    private RegexParser commandParser;
    
    public Environment(double width, double height) {
        myPool = new TurtlePool(width, height);
        myCommands = new SObservableOrderedMap<>();
        myVariables = new SObservableOrderedMap<>();
        commandParser = new RegexParser();
    }
    
    public void addCommand(Command toAdd) {
    	//TODO
    }

    public TurtlePool getPool() {
        return myPool;
    }
    
    public Command getCommand(String name) throws Exception {
        String command = commandParser.getSymbol(name);
        return myCommands.get(command.equals(NO_MATCH) ? name : command);
    }
    
    public void addTempVariable(String name, Literal value) {
    	//TODO
    }
    
    public void releaseTempVariables(int num) {
    	//TODO
    }
    
    public Literal getVariable(String name) throws Exception {
        return myVariables.get(name);
    }
    
    void setLanguage(String language) {
        commandParser.setPattern(language);
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
}
