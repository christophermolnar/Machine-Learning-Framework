readme - Milestone 2
Group: MZGA - Christopher Molnar, Callum Kirby, Brandon Hartford, and Geoff Scornaienchi

Classes:

Attribute <abstract class>:
-Abstract class for Key, Num and Point

Calculation <interface>:
-Interface for the different types of Calculations

CalculationDifference:
-Calculates the linear distance between numbers

CalculationEuclidian:
-Calculates the Euclidian distance between number or points

CalculationKey:
-Calculate the distance between keys (See if they are the same key)

Example:
-Creates Testing and Training Examples

Key:
-Responsible for managing instances of Strings (keys)

KNNController:
-Controls all of the ActionListeners 

KNNView:
-GUI View for out project

Num:
-Responsible for managing instances of integer and floating point value

Point:
-Responsible for managing instances of coordinate values

Project:
-Main class


Changes Made Since Last Deliverable:
-Removed: Age, House, and Videogame classes
-Changed/Updated: Numclass -> Num and Pointclass -> Point 
-New Classes:
	-Calculation, CalculationDifference, CalculationEuclidean and CalculationKey
	-Key
	-KNNController and KNNView
	-Example
	-Attribute
-Fixes:
	-Added User Manual
	-Implemented KNN properly (before we were returning the closest object for each attribute and averaging them)
	-Distance calculation is no longer responsibility of Point, Num or Key but its own interface and classes
	-Updated Point to allow 2D, 3D, or ND points
	-Removed Videogame and House classes and replace with Object -> Example, this allows us to create what ever examples we like
	-Removed Age (Enum class) and replaced it with a Key class (allows user to enter String values or "keys")
	-Added a GUI interface
	-Added the MCV pattern (M = Project/Object -> Example, C = KNNController and V = KNNView)
	-GUI allows users to 
		-Create example (format for the example)
		-Create training examples
		-Create one testing example
		-Calculate the KNN with a user entered K
		-Displays the result(s)
	-Added the Strategy Pattern to Calculation 
	-Users can choose distance metrics


Future Deliverables:
Milestone3:
-GUI should allow the user to choose distance metrics for features from a predefined set (BONUS: we allow the useres to choose distance metrics at Milestone 2!)
-We will be provided with another kNN problem to solve
Milestone 4:
-Add load and save data functions


Known Issues:
-Can't close or cancel when editting the testing or training example
-Can't edit testing example properly


RoadMap Ahead:
-Make it so they can run a second calculation
-Add a function to remove all testing and training examples
-Remove all created training and testing examples if they create a new format
-Resolve all Known Issues (see above)
-Fix issues addressed by TA
-Communicate with TA and Professor Esfandiari about any questions we have or problems we run into



