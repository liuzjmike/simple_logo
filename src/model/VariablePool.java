package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;

import model.executable.Literal;
import util.SLogoObservable;

public class VariablePool extends SLogoObservable<List<Entry<String, Literal>>> {
    
    private Deque<Deque<Entry<String, Literal>>> myStack;
    private int callStack;
    
    public VariablePool() {
        myStack = new ArrayDeque<>();
        callStack = 0;
    }
    
    public void add(String name, double value) {
        add(name, new Literal(value));
    }
    
    public void add(String name, Literal value) {
        myStack.getFirst().push(new SimpleEntry<>(name, value));
        notifyObservers();
    }
    
    public Literal get(String name) {
        for(Deque<Entry<String, Literal>> dq: myStack) {
            for(Entry<String, Literal> entry: dq) {
                if(entry.getKey().equals(name)) {
                    return entry.getValue();
                }
            }
        }
        throw new RuntimeException();
    }
    
    public void alloc() {
        if(myStack.isEmpty() || callStack > 0) {
            myStack.push(new ArrayDeque<>());
        }
        callStack++;
    }
    
    public void release() {
        if(callStack == 0) {
            throw new RuntimeException();
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
        System.out.println(myStack.size());
        List<Entry<String, Literal>> ret = new ArrayList<>();
        for(Deque<Entry<String, Literal>> dq: myStack) {
            ret.addAll(dq);
        }
        return ret;
    }

}
