yf59 bz44

## Part 1
1. What about your API/design is intended to be flexible?
The MVC model is very flexible.


2. How is your API/design encapsulating your implementation decisions?
For instance, the frontend has observer that observes the backend observable so that the backend doesn't has to pass stuff back to frontend. This hides detail about the implementation.


3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
For example, wrong command, not enough arguments to create a command, wrong type of arguments etc,


4. Why do you think your API/design is good (also define what your measure of good is)?
Because we hide the implemtation details from classes that don't need to see them.


## Part 2

1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
Observer Pattern: The change of a state in one object must be reflected in another object without keeping the objects tight coupled.
Factory Pattern: Creates objects without exposing the instantiation logic to the client. Refers to the newly created object through a common interface.

2. How do you think the "advanced" Java features will help you implement your design?

Reflection, binding, generics, regular expression

3. What feature/design problem are you most excited to work on?

Recursively reading command.

4. What feature/design problem are you most worried about working on?

Error checking.

5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

* *The user wants to change the color of the background of the turtle’s area to blue*
 * The user clicks on “Background Color” button which prompts them to select a color or input 6 hexadecimals. OptionsView -> View: changeBackground(String) -> TurtleView: setBackgroundColor(String)

* *The user wants to change the language to French*
 * The user clicks on the “Language” button which prompts them to select a language from a dropdown menu. OptionsView -> View: changeLanguage(String)

* *The user wants a full list of commands available to them*
 * The user clicks on the "Help" button which opens a new window with a a list of documented commands. OptionsView displayHelp()

* *The user types ‘make var 50’, which creates a variable named var, which is equals to 50*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> VariableFactory: makeVariable(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)

* *The user types ‘make var 60’, which reassigns the value of previously made variable var to 60*
 * PromptView -> View: void runCommand(String input) -> Model: void handleInput(String input) -> Parser: List<Commands> parse(String input) -> Model -> CommandHandler: add(List<Commands>) -> CommandHandler: pop() (Pops off recent command) -> MovementFactory: executeCommand(Command) -> VariableFactory: makeVariable(String, String) -> Model: updateVar(String, String) -> VariableManager: add(String, String)*
 * Our flow for typing out a reassignment of a variable is the same for making a new variable -- within our VariableManager: add(String, String) method, we check for existence then update.


