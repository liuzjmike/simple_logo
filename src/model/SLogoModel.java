package model;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import model.executable.Executable;
import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtleInfo;
import util.SLogoObserver;

public class SLogoModel {
    
    private Environment myEnv;
    private Interpreter myInterpreter;
    
    public SLogoModel(double width, double height) {
        myEnv = new Environment(width, height);
        myInterpreter = new Interpreter();
    }
    
    public double interpret(String commands) {
    	Executable root = myInterpreter.parse(commands, myEnv);
    	double ret = 0;
    	for(int i = 0; i < myEnv.getTurtlePool().size(); i++) {
    		root.execute(myEnv);
    		myEnv.getTurtlePool().switchTurtle();
    		//TODO: solve this
    	}
        return ret;
    }
    
    public void setLanguage(String language) {
        myEnv.getCommandPool().setLanguage(language);
    }
    
    public void setSize(double width, double height) {
        myEnv.setWidth(width);
        myEnv.setHeight(height);
    }
    
    public void addPoolObserver(SLogoObserver<Collection<Entry<Integer, TurtleInfo>>> so) {
        myEnv.getTurtlePool().addObserver(so);
    }
    
    public void removePoolObserver(SLogoObserver<Collection<Entry<Integer, TurtleInfo>>> so) {
        myEnv.getTurtlePool().removeObserver(so);
    }
    
    public void addCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myEnv.getCommandPool().addUserObserver(so);
    }
    
    public void removeCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myEnv.getCommandPool().removeObserver(so);
    }
    
    public void addVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myEnv.getVariablePool().addObserver(so);
    }
    
    public void removeVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myEnv.getVariablePool().removeObserver(so);
    }
    
    public Collection<TurtleInfo> getTurtles() {
        return myEnv.getTurtlePool().getTurtles();
    }
    
    public List<Entry<String, Command>> getCommands() {
        return myEnv.getCommandPool().getUserCommands();
    }
    
    public List<Entry<String, Literal>> getVariables() {
        return myEnv.getVariablePool().getVariables();
    }
}
