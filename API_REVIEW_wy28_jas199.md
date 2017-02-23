##What about your API/design is intended to be flexible?
###Wei-Ting: Can implement Observer interface to add a different page display to the GUI. 
###Jimmy: Can implement PageView to add different page display to GUI.

##How is your API/design encapsulating your implementation decisions?
###Wei-Ting: When calling the display() method in the GUI class, all the elements in the front end displays without knowing what each subclass does. 
###Jimmy: Display method in LogoController displays all of the views without needing to know what they are. Controller can get InputBox text and the backend can do whatever it wants to make a command based on the String that is returned.

##What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
###Wei-Ting: bad syntax--show an alert box (“don’t know how to interpret ….”). The Interpreter Class in the back end will pass a String that contains the error message to the Controller. The Controller then asks the front end to display this error.
###Jimmy: Only the backend will throw an error, the front-end will just display it. The Controller class will catch the error and make the alert box.

##Why do you think your API/design is good (also define what your measure of good is)? 
###Wei-Ting: View and Model are independent and are only connected by Controller. Good means code is flexible and few dependencies.
###Jimmy: Views and Models are independent of each other, and only communicate through the controller/observers. Good means there are minimal dependencies and maximal encapsulation.

##How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
###Wei-Ting: MVC is currently used. 
###Jimmy: MVC is currently used. For the front-end, it might be helpful to have a factory to create different types of view depending on the model.
##How do you think the "advanced" Java features will help you implement your design?
###Wei-Ting: I can use reflection to create any command in the constructor of the CommandView Class.
###Jimmy: The Observer class will allow the ToolBar/Tools to directly interact with other parts of the design, and will make that class a lot easier to write. Generics will allow the View class to reduce repeated code between all of its subclasses. Reflection will allow the CommandFactory to generate commands so that the Controller won’t have to directly worry about them.
##What feature/design problem are you most excited to work on?
###Wei-Ting: I am excited to work on the TurtleView class. I think it is pretty straightforward.
###Jimmy:I’m excited to work on the Controller class so I can better understand how the front-end can communicate with the back-end.
##What feature/design problem are you most worried about working on?
###Wei-Ting: I am worried about the ConsoleView. The communication of the front end and the back end runs through Controller and have to update the console. Storing the variables in the console is something I am worried about too.
###Jimmy: I’m worried about how the Toolbar/Tool will interact with other parts of the project (for example how the background color will change when a button is pressed).
##Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
You run the project
###Wei-Ting: The GUI will create instances of the ConsoleView and Interface Observer. After getting the Views, GUI displays everything.
###Jimmy: The RunLogo class makes a new Controller object and adds a new Toolbar with tools to it. The Controller object makes the inputbox and simulation view and displays them.
Somebody enters ‘fd 50’
###Wei-Ting: The string is passed to the controller. The Controller passes the String to SLogo Model. SLogo Model passes back a command, and GUI displays it.
###Jimmy: After enter is pressed, the controller gets the String ‘fd 50’ from the input box and passes it to the CommandFactory. Then, a command is created and the controller calls its .execute() method.
Somebody clicks a button to update the background color
###Wei-Ting: Will call the setColor() method and GUI displays it.
###Jimmy: The Tool’s observers are updated, resulting in SimulationView’s background color being updated to the new color.
An incorrect command is entered
###Wei-Ting: The String will be passed to the Controller and then the SLogo Model. The Interpreter will check the validity of the String and pass back an error message to the Controller and then GUI. GUI will display the error message in the end.
###Jimmy: The Controller gets the string command from the input-box and passes it to the CommandFactory, which throws an error. The Controller catches the error and displays an alert box.
A command is entered in which the turtle runs off the screen
###Wei-Ting: The command is passed to the Controller and then to the back end. The back end checks the bounds and decides the position of the turtle by wrapping around. GUI will display by calling update.
###Jimmy: The Controller gets the input string from the inputbox, makes the method in CommandFactory. The command is then executed, and this updates the TurtleView. The backend determines whether or not it is an error for the Turtle to run off the screen. If it is an error, the Controller will catch an error from the execute() method, and display an alert box.
