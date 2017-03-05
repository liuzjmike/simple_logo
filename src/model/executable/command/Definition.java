package model.executable.command;

import model.Environment;

public class Definition extends AbstractCommand {
    
    private String myName;
    
    public Definition(String name, int numParams) {
        super(numParams);
        myName = name;
    }

    @Override
    protected double concreteExecute(Environment env) {
        Command command = env.getCommandPool().getCommand(myName);
        for(int i = 0; i < numParams(); i++) {
            command.addParam(getParam(i));
        }
        return command.execute(env).getValue();
    }

}
