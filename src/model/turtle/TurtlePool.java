package model.turtle;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<Entry<Integer, TurtleInfo>>> {
	
	public static final int INITIAL_ID = 1;
    
    private Map<Integer, Turtle> allTurtles;
    private List<Integer> activeIDs;
    private int activeID;
    
    public TurtlePool() {
        super();
        allTurtles = new HashMap<>();
        activeIDs = new ArrayList<>();
        allTurtles.put(INITIAL_ID, new ToroidalTurtle());
        activeIDs.add(INITIAL_ID);
        activeID = INITIAL_ID;
    }
    
    public <T> T apply(Function<Turtle, T> function) {
    	if(activeID < 1) {
    		throw new RuntimeException();
    	}
    	T ret = function.apply(allTurtles.get(activeID));
    	notifyObservers();
    	allTurtles.get(activeID).clearReset();
        return ret;
    }
    
    public int activeID() {
    	return activeID;
    }
    
    public int size() {
        return activeIDs.size();
    }
    
    public void switchTurtle() {
    	for(int i = 0; i < activeIDs.size(); i++) {
    		if(activeIDs.get(i) == activeID) {
    			activeID = activeIDs.get((i+1)%activeIDs.size());
    			return;
    		}
    	}
    }
    
    public Collection<TurtleInfo> getTurtles() {
        return Collections.unmodifiableCollection(allTurtles.values());
    }
    
    public void tell(List<Integer> ids) {
    	activeIDs.clear();
    	activeID = 0;
    	for(int id: ids) {
    		if(id <= 0) {
    			throw new RuntimeException();
    		}
    		if(allTurtles.containsKey(id)) {
    			activeIDs.add(id);
    		} else {
    			for(int i = allTurtles.size(); i <= id; i++) {
        			activeIDs.add(i);
        			allTurtles.put(i, new ToroidalTurtle());
        		}
    		}
    		activeID = id;
    	}
        notifyObservers();
    }

    @Override
    protected Collection<Entry<Integer, TurtleInfo>> notification() {
    	List<Entry<Integer, TurtleInfo>> ret = new ArrayList<>();
    	activeIDs.forEach(id -> ret.add(new SimpleEntry<>(id, allTurtles.get(id))));
        return ret;
    }
}
