package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.executable.Literal;
import util.SLogoException;
import util.SLogoObservable;

public class VariablePool extends SLogoObservable<List<Entry<String, Literal>>> {
	
	public static final int STACK_LIMIT = 1024;
    
    private Deque<Map<String, Literal>> myStack;
    private int callStack;
    
    public VariablePool() {
        myStack = new ArrayDeque<>();
        callStack = 0;
    }
    
    public void add(String name, double value) {
        add(name, new Literal(value));
    }
    
    public void add(String name, Literal value) {
        System.out.println(name + " " + value.getValue());
        myStack.getFirst().put(name, value);
        notifyObservers();
    }
    
    public Literal get(String name) {
        for(Map<String, Literal> scope: myStack) {
            if(scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        throw new SLogoException(SLogoException.NO_VARIABLE_IN_SCOPE);
    }
    
    public void alloc() {
        if(myStack.isEmpty() || callStack > 0) {
            myStack.push(new HashMap<>());
        }
        callStack++;
        if (callStack > STACK_LIMIT) {
        	throw new SLogoException(SLogoException.STACK_OVERFLOW);
        }
    }
    
    public void release() {
        if(callStack == 0) {
            throw new SLogoException(SLogoException.STACK_UNDERFLOW);
        }
        if(callStack > 1) {
            myStack.pop();
        }
        callStack--;
        notifyObservers();
    }
    
    List<Entry<String, Literal>> getVariables() {
        return notification();
    }

    @Override
    protected List<Entry<String, Literal>> notification() {
        List<Entry<String, Literal>> ret = new ArrayList<>();
        myStack.forEach(
                scope -> ret.addAll(scope.entrySet()
                                         .stream()
                                         .sorted(Comparator.comparing(Entry<String, Literal>::getKey))
                                         .collect(Collectors.toList())));
        return ret;
    }

}
