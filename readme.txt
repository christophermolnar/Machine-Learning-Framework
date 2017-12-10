readme - Milestone 4
Group: MZGA - Christopher Molnar, Callum Kirby, Brandon Hartford, and Geoff Scornaienchi

Packages and there Classes:

junitTesting:
	AllTests:
		-Runs all of the tests
	TestCalculationsWithTrainingExamples
		-Test Calculations
	TestKeyAttribute:
		-Test the Key Class
	TestNumAttribute:
		-Test the Num Class
	TestPointWithDifference:
		-Test the Point Class with Difference Value
	TestPointWithEuclidean:
		-Test the Point Class with Euclidean Value
	TestScoreCalculationWithTrainingExample:
		-Test the Score Calculation

machineLearning:
	Attribute <abstract class>:
		-Abstract class for Key, Num and Point
	Calculation <interface>:
		-Interface for the different types of Calculations
	CalculationDifference:
		-Calculates the linear distance between numbers
	CalculationEuclidean:
		-Calculates the Euclidian distance between number or points
	CalculationKey:
		-Calculate the distance between keys (See if they are the same key)
	CalculationPolar:
		-Calculate the distance between nums as if they were polar coordinates (angles)
	Example:
		-Creates Testing and Training Examples
	Key:
		-Responsible for managing instances of Strings (keys)
	KNNModel:
		-The model for kNN
	Num:
		-Responsible for managing instances of integer and floating point value
	Point:
		-Responsible for managing instances of coordinate values

main:
	MachineLearning:
		-The Main Class

userInterface:
	KNNController:
		-Controls all of the ActionListeners 
	KNNView:
		-GUI View for out project

Changes Made Since Last Deliverable:
-Import and Export buttons added
-Edit fixed (from ta comments)
-Changed Project class to KNNModel
-General Refactoring


