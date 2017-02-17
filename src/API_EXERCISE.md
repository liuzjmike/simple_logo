Bihan Zhuang
Mike Liu
Sarah Zhou
Wei-Ting Yeh

SLogo Architecture Design
=========================

1. Parsing needs to take place when the user hits "Enter." It needs UI to pass it the raw String.
2. Parsing constructs abstract Command objects from the Strings to Controller class
3. Error checking happens at the parsing stage during the construction of the Command objects. They are reported by Exception classes that will alert the user where/why the error occured.
4. The commands know the values the user entered (passed from parser), and what object it is operating on (passed from Controller)
5. Back end evokes handler class, which updates the GUI. 
