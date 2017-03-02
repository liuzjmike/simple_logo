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
    
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Literal execute(Environment env) throws Exception {
        Literal ret = new Literal(0);
        for(Executable exec: this) {
            ret = exec.execute(env);
        }
        return ret;
    }

	@Override
	public Iterator<Executable> iterator() {
		return myExecs.iterator();
	}

}
