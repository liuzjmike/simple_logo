package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.List;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.Turtle;
import util.SLogoObserver;

public class SLogoModel {
    
    private Environment myEnv;
    private Interpreter myInterpreter;
    
    public SLogoModel() {
        myEnv = new Environment();
        myInterpreter = new Interpreter();
    }
    
    public double interpret(String commands) throws Exception {
        return myInterpreter.parse(commands, myEnv).execute().getValue();
    }
    
    public void addPoolObserver(SLogoObserver<Collection<Turtle>> so) {
        
    }
    
    public void removePoolObserver(SLogoObserver<Collection<Turtle>> so) {
        
    }
    
    public void addCommandObserver(SLogoObserver<List<SimpleEntry<String, Command>>> so) {
        
    }
    
    public void removeCommandObserver(SLogoObserver<List<SimpleEntry<String, Command>>> so) {
        
    }
    
    public void addVariableObserver(SLogoObserver<List<SimpleEntry<String, Literal>>> so) {
        
    }
    
    public void removeVariableObserver(SLogoObserver<List<SimpleEntry<String, Literal>>> so) {
        
    }
    
    public Collection<Turtle> getTurtles() {
        return null;
    }
    
    public List<SimpleEntry<String, Command>> getCommands() {
        return null;
    }
    
    public List<SimpleEntry<String, Literal>> getVariables() {
        return null;
    }
}
