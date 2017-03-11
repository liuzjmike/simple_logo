# CompSci 308 SLogo Project 
Wei-Ting Yeh, Mike Liu, Bihan Zhuang, Sarah Zhou

Date started: 2/21

Date finished: 3/10

Hours in total: 150

## Each person's role in developing the project

#### Mike Liu: 
1) 

#### Bihan Zhuang: 
1) Together designed the class hierarchy.

2) Designed, implemented, and refactored the Command hierarchy and resource files. Unlimited parameters.

3) Wrote Interpreter, RegexParser, error handling, some Info files, and CommandSaver.

4) PaletteView, ShapeView, ViewSupplier.

#### Wei-Ting Yeh:
1) Wrote TurtleView, PoolView, and PenView

2) Worked on some parts of GUI

#### Sarah Zhou: Front end and controller

## Resources used
Oracle, StackOverflow

## Files used to start the project
Main.java

## Files used to test the project
All the provided .logo files.

## Data or resource files required by the project
Classpath.properties, Syntax.properties, all the language resource files, and default.css	

## Information about using the program
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

## Known bugs, crashes, or problems with the project's functionality
Incomplete error handling for tell commands

## Extra features included in the project
* to see the state of the turtle 
* to see palettes of images/colors with numbers
* to set, save, and load workspace preferences
* to create multiple workspaces
* to select active turtles graphically and toggle whether or not to graphically identify the currently active turtles in a workspace's display window
* to move the current turtle(s) graphically
* to change the current pen's properties graphically
* recognize these additional commands 
* grouping: allow variable number of parameters to procedures where appropriate by using parentheses
* scope: allow dynamic variable scope and local variables
* recursion: allow user defined commands to include recursive calls

## Impressions of the assignment
Challenging!
