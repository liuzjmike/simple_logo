package model.executable.command;

import java.util.ArrayList;
import java.util.List;

import model.executable.Executable;
import model.executable.Literal;

public abstract class AbstractCommand implements Command {

    List<Executable> myParams;
    
    public AbstractCommand() {
        myParams = new ArrayList<>();
    }
    
    @Override
    public Literal execute() throws Exception {
        if(getParams().size() != numParams()) {
            throw new Exception();
        }
        return concreteExecute();
    }
    
    @Override
    public void addParam(Executable exec) throws Exception {
        if(myParams.size() >= numParams()-1) {
            throw new Exception();
        }
        myParams.add(exec);
    }
    
    protected List<Executable> getParams() {
        return myParams;
    }
    
    protected double getParamValue(int index) throws Exception {
        return getParams().get(index).execute().getValue();
    }
    
    protected abstract Literal concreteExecute() throws Exception;
}
