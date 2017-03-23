package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.executable.Literal;
import util.CollectionTransformer;
import util.SLogoException;
import util.SLogoObservable;

/**
 * Stores variables in dynamic scope.
 * @author Mike Liu
 *
 */
public class VariablePool extends SLogoObservable<List<Entry<String, Double>>> {
	
	public static final int STACK_LIMIT = 1024;
    
    private Deque<Map<String, Double>> myStack;
    
    public VariablePool() {
        myStack = new ArrayDeque<>();
        alloc();
    }
    
    public void add(String name, double value) {
        myStack.peek().put(name.toLowerCase(), value);
        notifyObservers();
    }
    
    public void add(String name, Literal value) {
        add(name, value.getValue());
    }
    
    public Literal get(String name) {
        String key = name.toLowerCase();
        for(Map<String, Double> scope: myStack) {
            if(scope.containsKey(key)) {
                return new Literal(scope.get(key));
            }
        }
        return new Literal(0);
    }
    
    public void alloc() {
        myStack.push(new HashMap<>());
        if (myStack.size() > STACK_LIMIT) {
        	throw new SLogoException(SLogoException.STACK_OVERFLOW);
        }
    }
    
    public void release() {
        if(myStack.size() <= 1) {
            throw new SLogoException(SLogoException.STACK_UNDERFLOW);
        }
        myStack.pop();
        notifyObservers();
    }
    
    List<Entry<String, Double>> getVariables() {
        return notification();
    }
    
    public Map<String, Double> getGlobal() {
        return myStack.getLast();
    }

    @Override
    protected List<Entry<String, Double>> notification() {
        List<Entry<String, Double>> ret = new ArrayList<>();
        CollectionTransformer transformer = new CollectionTransformer();
        for(Map<String, Double> stack: myStack) {
            ret.addAll(transformer.mapToList(stack));
        }
        return ret;
    }

}
