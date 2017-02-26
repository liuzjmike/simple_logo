package model.executable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Environment;

/**
 * Represents a SLogo list
 * When execute is called, returns the value of the final command executed
 * @author Mike Liu
 *
 */
public class ExecutableList implements Executable, Iterable<Executable> {
    
    List<Executable> myExecs;
    
    public ExecutableList() {
        myExecs = new ArrayList<>();
    }
    
    public void add(Executable exec) {
        myExecs.add(exec);
    }
    
    public int size() {
    	return myExecs.size();
    }
    
    public Executable get(int index) {
    	return myExecs.get(index);
    }

    @Override
    public Literal execute(Environment env) throws Exception {
        if (myExecs.size() == 0) {
            return new Literal(0);
        }
        Literal ret = new Literal(0);
        for(int i = 0; i < myExecs.size(); i++) {
            ret = myExecs.get(i).execute(env);
        }
        return ret;
    }

	@Override
	public Iterator<Executable> iterator() {
		return myExecs.iterator();
	}

}
