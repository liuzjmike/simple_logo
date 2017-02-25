package model;

import java.util.Collection;	
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.turtle.Turtle;

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
}
