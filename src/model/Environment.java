package model;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtlePool;
import util.SLogoObserver;
import util.SObservableList;

public class Environment {
    
    private TurtlePool myPool;
    private SObservableList<SimpleEntry<String, Command>> myCommands;
    private SObservableList<SimpleEntry<String, Literal>> myVariables;
    
    public Environment() {
        myPool = new TurtlePool();
        myCommands = new SObservableList<>();
        myVariables = new SObservableList<>();
    }

    public TurtlePool getPool() {
        return myPool;
    }
    
    public Command getCommand(String name) {
        return null;
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
