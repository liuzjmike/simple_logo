package model.turtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import model.info.PoolInfo;
import model.info.TurtleInfo;
import util.SLogoException;
import util.SLogoObservable;

/**
 * The area that turtles move in.
 * @author Mike Liu
 *
 */
public class TurtlePool extends SLogoObservable<PoolInfo> implements PoolInfo {
	
	public static final int INITIAL_ID = 1;

    private Pen defaultPen;
    private Map<Integer, Turtle> allTurtles;
    private List<Integer> activeIDs;
    private int activeIndex;
    private int myBG;
    
    public TurtlePool() {
        super();
        defaultPen = new Pen();
        allTurtles = new HashMap<>();
        activeIDs = new ArrayList<>();
        allTurtles.put(INITIAL_ID, new ToroidalTurtle(defaultPen));
        activeIDs.add(INITIAL_ID);
        activeIndex = 0;
        myBG = 0;
    }
    
    public <T> T apply(Function<Turtle, T> function) {
        return apply(function, true);
    }
    
    public <T> T applyAll(Function<Turtle, T> function) {
        validateActive();
    	T ret = null;
    	for(int i = 0; i < activeSize(); i++) {
    	    ret = apply(function, false);
    	    switchTurtle();
    	}
    	notifyObservers();
    	activeIDs.forEach(id -> allTurtles.get(id).clearReset());
        return ret;
    }
    
    public void applyPen(Consumer<Pen> consumer) {
        allTurtles.values().forEach(turtle -> consumer.accept(turtle.getPen()));
        defaultPen = getActiveTurtle().getPen().copy();
    }
    
    public void applyShape() {
    }
    
    public int activeID() {
    	return activeIDs.get(activeIndex);
    }
    
    public List<Integer> allActiveID() {
    	return new ArrayList<>(activeIDs);
    }
    
    public int activeSize() {
        return activeIDs.size();
    }
    
    public int fullSize() {
    	return allTurtles.size();
    }
    
    @Override
    public int getBackground() {
        return myBG;
    }
    
    public int setBackground(int index) {
        myBG = index;
        notifyObservers();
        return index;
    }

    @Override
    public Map<Integer, TurtleInfo> getTurtles() {
    	Map<Integer, TurtleInfo> ret = new HashMap<>();
    	for(int id: activeIDs) {
    		ret.put(id, allTurtles.get(id));
    	}
        return Collections.unmodifiableMap(ret);
    }
    
    public void tell(List<Integer> ids) {
        List<Integer> backup = allActiveID();
    	activeIDs.clear();
    	activeIndex = -1;
    	for(int id: ids) {
    		if(id <= 0) {
    		    activeIDs = backup;
    			throw new SLogoException(SLogoException.ILLEGAL_ID);
    		}
    		if(allTurtles.containsKey(id)) {
    			activeIDs.add(id);
    		} else {
    			for(int i = allTurtles.size()+1; i <= id; i++) {
        			activeIDs.add(i);
        			allTurtles.put(i, new ToroidalTurtle(defaultPen));
        		}
    		}
    		activeIndex++;
    	}
        notifyObservers();
    }
    
    public void askWith(Predicate<Turtle> predicate) {
    	activeIDs.clear();
    	activeIDs.addAll(allTurtles.keySet());
    	Set<Integer> newActive = new HashSet<Integer>();
    	for (int i = 0; i < activeSize(); i++) {
    		if (predicate.test(allTurtles.get(activeIDs.get(activeIndex)))) {
    			newActive.add(i);
    		}
    		switchTurtle();
    	}
    	activeIDs.clear();
    	activeIDs.addAll(newActive);
    	activeIndex = activeIDs.isEmpty() ? -1 : activeIDs.size()-1;
    }
 
    @Override
    protected PoolInfo notification() {
        return this;
    }
    
    private <T> T apply(Function<Turtle, T> function, boolean notify) {
        validateActive();
        Turtle current = getActiveTurtle();
        T ret = function.apply(current);
        if(notify) {
            notifyObservers();
            current.clearReset();
        }
        return ret;
    }
    
    private void switchTurtle() {
        validateActive();
        ++activeIndex;
        activeIndex %= activeIDs.size();
    }
    
    private Turtle getActiveTurtle() {
        return allTurtles.get(activeIDs.get(activeIndex));
    }
    
    private void validateActive() {
        if(activeIDs.isEmpty()) {
            throw new SLogoException(SLogoException.NO_ACTIVE_TURTLE);
        }
    }
}
