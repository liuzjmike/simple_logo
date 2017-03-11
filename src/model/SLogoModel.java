package model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.info.PaletteInfo;
import model.info.PoolInfo;
import model.info.TurtleInfo;
import util.SLogoObserver;

public class SLogoModel {
    
    public static final double DEFAULT_WIDTH = 800;
    public static final double DEFAULT_HEIGHT = 600;
    
    private Environment myEnv;
    private Interpreter myInterpreter;
    
    public SLogoModel() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public SLogoModel(double width, double height) {
        myEnv = new Environment(width, height);
        myInterpreter = new Interpreter();
    }
    
    public double interpret(String commands) {
    	return myInterpreter.parse(commands, myEnv).execute(myEnv).getValue();
    }
    
    public void setLanguage(String language) {
        myEnv.getCommandPool().setLanguage(language);
    }
    
    public String getLanguage() {
    	return myEnv.getCommandPool().getLanguage();
    }
    
    public PaletteInfo getPalette() {
        return myEnv.getPalette();
    }
    
    public void setSize(double width, double height) {
        myEnv.setWidth(width);
        myEnv.setHeight(height);
    }
    
    public void addPoolObserver(SLogoObserver<PoolInfo> so) {
        myEnv.getTurtlePool().addObserver(so);
    }
    
    public void removePoolObserver(SLogoObserver<PoolInfo> so) {
        myEnv.getTurtlePool().removeObserver(so);
    }
    
    public void addCommandObserver(SLogoObserver<List<String>> so) {
        myEnv.getCommandPool().addObserver(so);
    }
    
    public void addPaletteObserver(SLogoObserver<PaletteInfo> so) {
    	myEnv.getPalette().addObserver(so);
    }
    
    public void removeCommandObserver(SLogoObserver<List<String>> so) {
        myEnv.getCommandPool().removeObserver(so);
    }
    
    public void addVariableObserver(SLogoObserver<List<Entry<String, Double>>> so) {
        myEnv.getVariablePool().addObserver(so);
    }
    
    public void removeVariableObserver(SLogoObserver<List<Entry<String, Double>>> so) {
        myEnv.getVariablePool().removeObserver(so);
    }
    
    public void removePaletteObserver(SLogoObserver<PaletteInfo> so) {
    	myEnv.getPalette().removeObserver(so);
    }
    
    public Map<Integer, TurtleInfo> getTurtles() {
        return myEnv.getTurtlePool().getTurtles();
    }
    
    public List<String> getCommands() {
        return myEnv.getCommandPool().getUserCommands();
    }
    
    public List<Entry<String, Double>> getVariables() {
        return myEnv.getVariablePool().getVariables();
    }
}
