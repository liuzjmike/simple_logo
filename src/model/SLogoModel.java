package model;

import java.util.Collection;		
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.Turtle;
import util.SLogoObserver;

public class SLogoModel extends Observable {
    
    public String interpret(String String) throws Exception {
		return null;
	}
    
    public void addPoolObserver(Observer o) {
	}
    
    public void removePoolObserver(Observer o) {
	}
    
    public void addCommandObserver(Observer o) {
	}
    
    public void removeCommandObserver(Observer o) {
	}
    
    public void addVariableObserver(Observer o) {
	}
    
    public void removeVariableObserver(Observer o) {
	}
    
    public Collection<Turtle> getTurtles() {
		return null;
	}
    
    public List<String> getCommands() {
		return null;
	}
    
    public List<String> getVariables() {
		return null;
	}
public class SLogoModel {
    
    private Environment myEnv;
    private Interpreter myInterpreter;
    
    public SLogoModel(double width, double height) {
        myEnv = new Environment(width, height);
        myInterpreter = new Interpreter();
    }
    
    public double interpret(String commands) throws Exception {
        return myInterpreter.parse(commands, myEnv).execute(myEnv).getValue();
    }
    
    public void setLanguage(String language) {
        myEnv.setLanguage(language);
    }
    
    public void setSize(double width, double height) {
        myEnv.getPool().setSize(width, height);
    }
    
    public void addPoolObserver(SLogoObserver<Collection<Turtle>> so) {
        myEnv.getPool().addObserver(so);
    }
    
    public void removePoolObserver(SLogoObserver<Collection<Turtle>> so) {
        myEnv.getPool().removeObserver(so);
    }
    
    public void addCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myEnv.addCommandObserver(so);
    }
    
    public void removeCommandObserver(SLogoObserver<List<Entry<String, Command>>> so) {
        myEnv.removeCommandObserver(so);
    }
    
    public void addVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myEnv.addVariableObserver(so);
    }
    
    public void removeVariableObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myEnv.removeVariableObserver(so);
    }
    
    public Collection<Turtle> getTurtles() {
        return myEnv.getPool().getTurtles();
    }
    
    public List<Entry<String, Command>> getCommands() {
        return myEnv.getCommands();
    }
    
    public List<Entry<String, Literal>> getVariables() {
        return myEnv.getVariables();
    }
}
