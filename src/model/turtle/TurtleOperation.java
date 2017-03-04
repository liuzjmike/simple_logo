package model.turtle;

import java.util.function.Function;

import model.Environment;

public class TurtleOperation {
	
	public double operate(Environment env, Function<Turtle, Double> function) {
		double ret = 0;
		TurtlePool pool = env.getTurtlePool();
		for(int i = 0; i < pool.size(); i++) {
			ret = pool.apply(function);
			pool.switchTurtle();
		}
		return ret;
	}
}
