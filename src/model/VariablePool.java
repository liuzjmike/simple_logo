package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.executable.Literal;
import util.SLogoObserver;
import util.SObservableOrderedMap;

public class VariablePool {
    
    private SObservableOrderedMap<String, Literal> myVariables;
    private Deque<Map<String, Literal>> myStack;
    
    public VariablePool() {
        myVariables = new SObservableOrderedMap<>();
        myStack = new ArrayDeque<>();
    }
    
    public void add(String name, double value) {
    	myVariables.put(name, new Literal(value));
    }
    
    public Literal get(String name) throws Exception {
        if(myStack.getFirst().containsKey(name)) {
            return myStack.getFirst().get(name);
        } else {
            return myVariables.get(name);
        }
    }
    
    public void allocTemp() {
        myStack.push(new HashMap<String, Literal>());
    }
    
    public void addTemp(String name, Literal value) {
        myStack.getFirst().put(name, value);
    }
    
    public void releaseTemp() {
        myStack.pop();
    }
    
    List<Entry<String, Literal>> getVariables() {
        return myVariables.getAll();
    }
    
    void addObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myVariables.addObserver(so);
    }
    
    void removeObserver(SLogoObserver<List<Entry<String, Literal>>> so) {
        myVariables.removeObserver(so);
    }

}
