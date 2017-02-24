##Part 1

**What about your API/design is intended to be flexible?**

You can have multiple turtles because of the TurtlePool which manages and executes commands on any specified turtle.

Command being an abstract class so that you can extend on it to provide implementation of each command. 

**How is your API/design encapsulating your implementation decisions?**

The API design conforms to the MVC design pattern. The model classes encapsulate all program's logic and hide the implementation details from the front end and vice versa. The controller serves as the communicator between the two parts. 

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

* invalid type of file for updating turtle's image view. 
* turtle going off the screen. 

**Why do you think your API/design is good (also define what your measure of good is)?**

Our API is well-encapsulated in that it is easy to classify each class to either Model , View, or Controller component. The program logic processed in the Model components is encapsulated/hidden from the View components, and the front end display is encapsulated/hidden from the Model components. 

Also, interface and subclasses will allow for inheritance design which is extensible.

##Part 2

**How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

We are using the MVC design pattern and the Observer/Observable design pattern.

**How do you think the "advanced" Java features will help you implement your design?**

An "advanced" Java feature that front end can utilize is the factory design pattern. I could have a class TurtleViewFactory that returns instances of TurtleViews, which would encapsulate some of the details of creating Turtles. This would make TurtleView more extensible if there are more "types" of TurtleViews.  

**What feature/design problem are you most excited to work on?**

I'm most excited to work on the Controller class because it's the interface between the Model and View, and ties the seperate logics together. 

**What feature/design problem are you most worried about working on?**

I think one of the harder features to implement will be the console that the user types on, particularly the extra features such as making previous commands "clickable". 

**Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

*Example: The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.*

Front end passes raw user input String (“fd 50”) to Controller, which calls SLogoModel’s interpret() method, which calls Interpreter’s parseCommand() method on the String. This creates an instance of “fd Command”, which takes in value “50” as a parameter, which executes the command on TurtlePool. TurtlePool calls moveTurtle(int ID, double dist) method on the specified turtle (using a unique String id) in the back end. Whenever any of the turtles are updated, notifyObservers() method of Observable class (which TurtlePool extends) notifies PoolView (the observer of TurtlePool) of the update. GUI calls step() method on all of the Views (PoolView, TerminalView, VariablesView, FunctionView, OptionsView). PoolView updates the front end TurtleViews to reflect updated back end. Each TurtleView has a Pen object which draws the trail if it is down. TerminalView stores the user’s String input command in the environment’s history and updates to notify user that it is ready to accept next command.

*Example: The user types “LEFT 90” in the command window, and sees the turtle turn 90 degrees left in the display window, and the command is added to the environment's history.*

Front end passes raw user input String (“LEFT 90”) to Controller, which calls SLogoModel’s interpret() method, which calls Interpreter’s parseCommand() method on the String. This creates an instance of “Turn Command”, which takes in value “90” and “left” as parameters, which executes the command on TurtlePool. TurtlePool calls turnTurtle(int ID, double angle) method on the specified turtle (using a unique String id) in the back end. Whenever any of the turtles are updated, notifyObservers() method of Observable class (which TurtlePool extends) notifies PoolView (the observer of TurtlePool) of the update. Controller calls update() on GUI calls step() method on all of the Views (PoolView, TerminalView, VariablesView, FunctionView, OptionsView). PoolView updates the front end TurtleViews to reflect updated back end. TerminalView stores the user’s String input command in the environment’s history and updates to notify user that it is ready to accept next command.

*Example: The user types “PENUP” in the command window, and the command is added to the environment's history.*

Front end passes raw user input String (“PENUP”) to Controller, which calls SLogoModel’s interpret() method, which calls Interpreter’s parseCommand() method on the String. This creates an instance of “Pen Command”, which takes in a boolean value as a parameter, which executes the command on TurtlePool. TurtlePool calls setPen(boolean) method on the specified turtle (using a unique String id) in the back end. Whenever any of the turtles are updated, notifyObservers() method of Observable class (which TurtlePool extends) notifies PoolView (the observer of TurtlePool) of the update. Controller calls update() on GUI calls step() method on all of the Views (PoolView, TerminalView, VariablesView, FunctionView, OptionsView). PoolView updates the front end TurtleViews to reflect updated back end; specifically, it updates the TurtleView’s Pen instance. TerminalView stores the user’s String input command in the environment’s history and updates to notify user that it is ready to accept next command.

*Example: The user types “SHOWTURTLE” in the command window, and sees the turtle show up in the display window, returns 1 to TerminalView (console) and the command is added to the environment's history.*

Front end passes raw user input String (“SHOWTURTLE”) to Controller, which calls SLogoModel’s interpret() method, which calls Interpreter’s parseCommand() method on the String. This creates an instance of “Show Command”, which takes in a boolean value as a parameter, and executes (through execute()) the command on TurtlePool. The execute() method also returns String “1” and passes to TerminalView. TurtlePool calls setTurtleVisibility(int ID, boolean visibility) method on the specified turtle (using a unique String id) in the back end. Whenever any of the turtles are updated, notifyObservers() method of Observable class (which TurtlePool extends) notifies PoolView (the observer of TurtlePool) of the update. Controller calls update() on GUI calls step() method on all of the Views (PoolView, TerminalView, VariablesView, FunctionView, OptionsView). PoolView updates the front end TurtleViews to reflect updated back end; specifically, it sets TurtleView’s boolean visibility instance variable to “true”, and thus the user will be able to see the turtle’s ImageView on screen. TerminalView displays the String passed from Command’s execute method (“1”), stores the user’s String input command in the environment’s history, and updates to notify user that it is ready to accept next command.

*Example: The user types “SUM 1 1” in the command window, returns the result of the summation to TerminalView (console) and the command is added to the environment's history.*

Front end passes raw user input String (“SUM 1 1”) to Controller, which calls SLogoModel’s interpret() method, which calls Interpreter’s parseCommand() method on the String. This creates an instance of “Sum Command”, which takes in “1” as expr1 and “1” as expr2, and executes (through execute()) the command. The execute() method returns String “2” and passes to Controller. Controller calls update() on GUI calls step() method on all of the Views (PoolView, TerminalView, VariablesView, FunctionView, OptionsView) and passes String to TerminalView. TerminalView displays the String passed from Command’s execute method (“2”), stores the user’s String input command in the environment’s history, and updates to notify user that it is ready to accept next command.

