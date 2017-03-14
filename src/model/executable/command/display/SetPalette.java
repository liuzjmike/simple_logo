package model.executable.command.display;

import model.Environment;

/**
 * sets color corresponding at given index to given r g b color values
 * returns given index
 * @author zhuangbihan
 *
 */
public class SetPalette extends DisplayCommand {

	public SetPalette() {
		super(4);
	}

	@Override
	protected double run(Environment env) {
		env.getPalette().setColor((int)getParamValue(env,0), 
				                  (int)getParamValue(env,1), 
				                  (int)getParamValue(env,2), 
				                  (int)getParamValue(env,3));
		return getParamValue(env,0);
	}

}
