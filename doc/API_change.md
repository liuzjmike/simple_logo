SLogo External API Change
============

## GUI

1) void show(Stage stage)

Added the stage parameter to initialize a stage.

2) void setViewHandler(ControlHandler handler)

Added the setViewHandler method for initializing all necessary handlers for each View.

3) double getPoolViewHeight()

Added this method to return the PoolView's height.

4) double getPoolViewWidth()

Added this method to return the PoolView's width.

5) SLogoObserver<Collection<Turtle>> getPoolObserver()

Added this method to return the Turtle Pool View.

6) SLogoObserver<List<Entry<String, Literal>>> getVariableObserver()

Added this method to return the VariableView.

7) SLogoObserver<List<Entry<String, Command>>> getCommandObserver()

Added this method to return the CommandView.


## SLogoModel

1) double interpret(String commands) throws Exception

We changed the return type of interpret from void to double because ultimately every command would return a double. Also if we
ever wanted to run the backend logic without front-end, double could easily let us see how the program runs. 

2) public void setLanguage(String language)

setLanguage was added to set the language in which the commands are written.

3) public void setSize(double width, double height)

setSize was added because the backend TurtlePool needs to know the dimension of the TurtleView so that we can deal with out of 
bounds issues.

4) public List<Entry<String, Command>> getCommands()

We modified the return type of getCommands to a List with entries in the form of Entry<String, Command> as a representation of 
all the commands that are defined by the user, i.e. using the "to" keyword.

5) public List<Entry<String, Literal>> getVariables()

We modified the return type of getVariables to a List with entries in the form of Entry<String, Literal> as a representation of 
all the variables that are defined by the user.


## Turtle

1) int getID()

Added this method to return ID of Turtle in order to distinguish them.

2) List<TurtleHist> getLastMove()

Added the getLastMove method to return the last movement (for normal movement within the window -- one point; for toroidal turtle
movement, this will be a series of points for calculation's sake) of the Turtle if we ever need to clear the traces. 

3) boolean isReset()

Added isReset to get the signal of whether to reset the environment or not.  


## Corresponding Views in the front-end and Pools in the backend

1) 
