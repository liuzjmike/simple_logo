# Simple Logo
Mike Liu, Bihan Zhuang, Wei-Ting Yeh, Sarah Zhou  
Feb 21 ~ March 10, 2017  
Time Spent: ~150 hours

## Roles

#### Mike Liu: 
1. Designed the class hierarchy and Observer pattern used in the project.
2. Designed Interfaces including ControlHandler, Executable, Command, Turtle, SLogoObserver and all interfaces in model.info package except ShapeInfo.
3. Implemented all classes in model root package, model.executable package, model.turtle package, view.factory package, util package except RegexParser and XMLParserWriter,
AbstractCommand, CustomizedCommand, Declaration and To in model.executable.command package, and View and ScrollView in view package.
4. Heavily refactored Workspace, GUI, ConsoleView, CommandView and VariableView. Slightly refactored TurtleView and PoolView.

#### Bihan Zhuang: 
1. Together designed the class hierarchy.
2. Designed, implemented, and refactored the Command hierarchy and resource files. Unlimited parameters.
3. Wrote Interpreter, RegexParser, error handling, some Info files, and CommandSaver.
4. PaletteView, ShapeView, ViewSupplier.

#### Wei-Ting Yeh:
1. Wrote TurtleView, PoolView, and PenView
2. Worked on some parts of GUI

#### Sarah Zhou
Implemented Workspace, GUI, ConsoleView, CommandView and VariableView.

## Resources used
Java 8 Documentations  
[StackOverflow](http://stackoverflow.com/)

## Get Started
Program is started by running Main.java

## Testing
Example code that can be used test this program are in data directory.

## Resource Files
Resource files for input processing and reflection as well as default.css for GUI are in resources package.  
Input translation are in resources.languages package.

## How to use the program
* To graphically move the turtle in the pool, there are two ways. One thing the user has to do first in either way is to click on the turtle to set it active. The active turtle will be larger. One way after that is to use “w, a, s, d” keys to move forward, turn left, backwards, and turn right. The other way is to simply drag the turtle around with your mouse. To view the current properties of the turtle, press “I” to get a popup window. Once the user releases “I”, the window disappears.
* Use the “set” command to save user-defined variables and they will show up in the “Variable” tab.
* All user-defined commands will show up in the “Command” tab. Click on them to run them again.
* Use the “Pen Setting” tab to change stroke size and pen down or pen up.
* Use the “Palette” tab to change the color of Pen and Background of the turtle-pool. Click on the colored block to change colors.
* Use the “Shapes” tab to change to a turtle of choice. Click on the turtle to make change.
* Use the “Options” tab to get the Help page, create a new Workspace, save and load Workspace preferences, and change language of the commands. 
* Use the Textfield to enter basic commands, define variables, and make user-defined commands. Click the “Run” button to run or hit “Ctrl+Enter” to run.
* Use the “Load” button next to the Textfield to load .logo files. 
* Use the “Save” button next to the Textfield to save all command history.
* All return values of all commands will be printed under the corresponding command.
* Click any command in the command history field to run it again. 

## Bugs
None detected.
