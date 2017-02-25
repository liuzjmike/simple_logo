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
        if (myExecs.size() == 0) {
            return new Literal(0);
        }
        Literal ret = null;
        for(int i = 0; i < myExecs.size(); i++) {
            ret = myExecs.get(i).execute();
        }
        return ret;
    }

}
