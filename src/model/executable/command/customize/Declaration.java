package model.executable.command.customize;

import model.Environment;
import model.executable.command.ActionCommand;
import model.executable.command.Command;

/**
 * Represents the declaration of a command, which is
 * executed as the implemented command at run time.
 * @author Mike Liu
 *
 */
public class Declaration extends ActionCommand {
    
    private String myName;
    
    public Declaration(String name, int numParams) {
        super(numParams);
        myName = name;
    }
    
    @Override
    public Declaration newInstance() {
        return new Declaration(myName, numParams());
    }

    @Override
    protected double run(Environment env) {
        Command command = env.getCommandPool().getCommand(myName);
        for(int i = 0; i < numParams(); i++) {
            command.addParam(getParam(i));
        }
        return command.execute(env).getValue();
    }

}
