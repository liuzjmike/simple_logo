package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtlePool;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class Environment {
    
    private TurtlePool myPool;
    private SObservableOrderedMap<String, Command> myCommands;
    private SObservableOrderedMap<String, Literal> myVariables;
    
    public Environment() {
        myPool = new TurtlePool();
        myCommands = new SObservableOrderedMap<>();
        myVariables = new SObservableOrderedMap<>();
    }

    public TurtlePool getPool() {
        return myPool;
    }
    
    public Command getCommand(String name) throws Exception {
        return myCommands.get(name);
    }
    
    public Literal getVariable(String name) throws Exception {
        return myVariables.get(name);
    }
    
    public List<SimpleEntry<String, Command>> getCommands() {
        return myCommands.getAll();
    }
    
    public List<SimpleEntry<String, Literal>> getVariables() {
        return myVariables.getAll();
    }
    
    public void addCommandObserver(SLogoObserver<List<SimpleEntry<String, Command>>> so) {
        myCommands.addObserver(so);
    }
    
    public void removeCommandObserver(SLogoObserver<List<SimpleEntry<String, Command>>> so) {
        myCommands.removeObserver(so);
    }
    
    public void addVariableObserver(SLogoObserver<List<SimpleEntry<String, Literal>>> so) {
        myVariables.addObserver(so);
    }
    
    public void removeVariableObserver(SLogoObserver<List<SimpleEntry<String, Literal>>> so) {
        myVariables.removeObserver(so);
    }
}
