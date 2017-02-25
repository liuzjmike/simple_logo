package model.executable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a SLogo list
 * When execute is called, returns the value of the final command executed
 * @author Mike Liu
 *
 */
public class ExecutableList implements Executable {
    
    List<Executable> myExecs;
    
    public ExecutableList() {
        myExecs = new ArrayList<>();
    }
    
    public void add(Executable exec) {
        myExecs.add(exec);
    }

    @Override
    public Literal execute() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
