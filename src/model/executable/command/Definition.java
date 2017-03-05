package model.executable.command;

import model.Environment;
import model.executable.Executable;
import model.executable.ExecutableList;

public class Definition extends AbstractCommand {
    
    private String myName;
    private ExecutableList varParams;
    
    public Definition(String name, ExecutableList params) {
        super(params.size());
        myName = name;
        varParams = params;
    }

    @Override
    protected double concreteExecute(Environment env) {
        Command command = env.getCommandPool().getCommand(myName);
        for(Executable exec: varParams) {
            command.addParam(exec);
        }
        return command.execute(env).getValue();
    }

}
