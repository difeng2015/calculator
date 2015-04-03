## Synopsis

This is a calculator program that reads an expression and prints the evaluation result. A log file can provide useful information if the expression is invalid.

Sample inputs and outputs are as follows:

     Input                                                       Output
     add(1, 2)                                                   3
     add(1, mult(2, 3))                                          7
     mult(add(2, 2), div(9, 3))                                  12
     let(a, 5, add(a, a))                                        10
     let(a, 5, let(b, mult(a, 10), add(b, a)))                   55
     let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))        40

## How to Build

The project is already built. A calculator.jar file can be found under the "target" folder.

One can also use maven to build the project easily. Assuming you are using Mac/Linux/Unix with Maven installed and can use <code>mvn</code> command. Go to the calculator project directory in shell, run the following command:

     mvn package

This will generate a calculator.jar file that includes all dependencies in the project.

## How to Run

In order to execute the program, run the following command:

     java -jar calculator.jar "mult(add(2, 2), div(9, 3))"

This will output the result on screen if no error occurred, and generate a "logging.log" file in the directory where you execute the program.

## Logs

The project is currently configured to save the log file at the directory where you run the program. It's configurable by assigning the "log4j.appender.file.File" property a different path in the log4j.properties file.

## Tests

Two JUnit test classes are responsible for testing methods in class <code>Arithmetic</code> and <code>Parser</code>.

## Issues Encountered

The main issue is where is the most proper place to put an ERROR level logging.

In this project, there are 3 levels of logging: DEBUG, INFO, ERROR. It's easy to decide for DEBUG level and INFO level logging, since DEBUG is used for logging fine-grained information to provide useful data when debugging the code, and INFO is used to provide information regarding the progress of the program at a coarse-grained level.

On the other hand, ERROR level logging exists mainly in order to provide detailed error information and at the same time give the program a chance to restore from an error and continue to execute. However, for this calculator program, if an error happened, it means the expression input is invalid, and the program will not be able to give a correct answer whatsoever. So there is no way to "restore" from the error. Therefore, it's reasonable to throw an exception whenever the input is found invalid to end the program, and put the ERROR level logging within a try/catch clause in the main method just to show the user a message why the input is invalid, so that the user can examine his input.

## What Could Be Further Improved

When there is an undefined variable encountered in the expression, the logger will tell the user the name of the undefined variable, and the user can examine the expression based on this. The logging code can be improved to further points out the undefined variable's position in the expression, so that it's faster for the user to pinpoint the problem and make corrections.

## Jenkins Integration

A Jenkins server and a job called "calculator" are set up for this repository. Please visit it here: http://ec2-52-0-244-198.compute-1.amazonaws.com:8080

Whenever a new commit is made to this repository, GitHub will trigger the Jenkins job "calculator" to pull from this repository and automatically produce a build on the server using Maven configuration for the Java project.

Immediately after the build is done, the job will run the JUnit test cases. In the job's page (http://ec2-52-0-244-198.compute-1.amazonaws.com:8080/job/calculator), you can see the Build History, Test Result Trend, and details about a test if you want.

## Time Spent on Different Parts of the Project

1.5 hours for writing the core code that can correctly output evaluation result for a valid expression.

2 hours for writing custom exceptions, setting up "log4.properties" file, adding logging code, writing javadoc.

30 minutes for writing JUnit test cases.

1 hour for configuring AWS EC2 server and setting up Maven, Git and Jenkins. 
