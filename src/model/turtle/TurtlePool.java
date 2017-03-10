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

import javafx.scene.paint.Color;
import model.turtle.info.PoolInfo;
import model.turtle.info.TurtleInfo;
import util.SLogoException;
import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<PoolInfo> implements PoolInfo {
	
	public static final int INITIAL_ID = 1;
	public static final Color DEFAULT_BACKGROUND = Color.WHITE;

    private Pen defaultPen;
    private Map<Integer, Turtle> allTurtles;
    private List<Integer> activeIDs;
    private int activeIndex;
    private Color myBG;
    
    public TurtlePool() {
        super();
        defaultPen = new Pen();
        allTurtles = new HashMap<>();
        activeIDs = new ArrayList<>();
        allTurtles.put(INITIAL_ID, new ToroidalTurtle(defaultPen));
        activeIDs.add(INITIAL_ID);
        activeIndex = 0;
        myBG = DEFAULT_BACKGROUND;
    }
    
    public <T> T apply(Function<Turtle, T> function) {
        return apply(function, true);
    }
    
    public <T> T applyAll(Function<Turtle, T> function) {
    	T ret = null;
    	for(int i = 0; i < activeSize(); i++) {
    	    ret = apply(function, false);
    	    switchTurtle();
    	}
    	notifyObservers();
        return ret;
    }
    
    private <T> T apply(Function<Turtle, T> function, boolean notify) {
    	validateActive();
        Turtle current = getActiveTurtle();
        T ret = function.apply(current);
        if(notify) {
        	notifyObservers();
        }
        current.clearReset();
        return ret;
    }
    
    public void applyPen(Consumer<Pen> consumer) {
        allTurtles.values().forEach(turtle -> consumer.accept(turtle.getPen()));
        defaultPen = getActiveTurtle().getPen().clone();
        notifyObservers();
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
    
    public void switchTurtle() {
        validateActive();
    	++activeIndex;
    	activeIndex %= activeIDs.size();
    }
    
    @Override
    public Color getBackground() {
        return myBG;
    }
    
    public void setBackground(Color color) {
        myBG = color;
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
    	activeIDs.clear();
    	activeIndex = -1;
    	for(int id: ids) {
    		if(id <= 0) {
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
    
    private Turtle getActiveTurtle() {
        return allTurtles.get(activeIDs.get(activeIndex));
    }
    
    private void validateActive() {
        if(activeIDs.isEmpty()) {
            throw new SLogoException(SLogoException.NO_ACTIVE_TURTLE);
        }
    }
}
