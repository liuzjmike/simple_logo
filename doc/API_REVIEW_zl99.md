Mike Liu, Timmy Huang

# Part 1
## Q1
MVC architecture allows Model and View to not have direct dependency on each other, so they can be extended independently
without affecting each other. Command superclass also allows other part of Model to not deal with specific implementation of
each concrete command, allowing additional commands to be added easily.

## Q2
Communication to front-end are limited to the GUI API and communication to back-end are limited to the SLogalModel and Turtle
API, so all the other inner implementation are encapsulated and can be changed without influencing client code

## Q3
I'm implementing the back-end turtle and I don't see clear error cases for now

## Q4
The implementation details are well encapsulated so implementations can be changed without affecting client code

# Part 2
## Q1
The communication of our front-end and back-end utilizes the Observer pattern to allow different, multiple font-ends be
acknowledged changes made in back-end without any dependency of back-end on front-end

## Q2
Reflection can help use instantiate command without having chain if-statements.

## Q3
Designing a good MVC architecture

## Q4
The effective communication between back-end and front-end