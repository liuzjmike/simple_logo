package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns sum of the values of expr1 and expr2
 * @author zhuangbihan
 *
 */
public class Sum extends TwoParamCommand {

    @Override
    protected double concreteExecute(Environment env) {
        return getParamValue(0, env) + getParamValue(1, env);
    }
}
