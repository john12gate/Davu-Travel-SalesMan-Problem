# Traveling Salesman Problem Solver with Genetic Algorithm

This project implements a solution to the Traveling Salesman Problem (TSP) using a Genetic Algorithm (GA) in Java. The solution includes visualization of the TSP route using JavaFX and automated testing with JUnit.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Key Components](#key-components)
- [Running the Application](#running-the-application)
- [Algorithm Details](#algorithm-details)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Traveling Salesman Problem (TSP) is a classic optimization problem where the goal is to find the shortest possible route that visits a set of cities exactly once and returns to the origin city. This project uses a Genetic Algorithm (GA) to approximate a solution to this NP-hard problem. The solution is visualized using JavaFX, allowing users to see the path traveled by the salesman.

## Project Structure

```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── davu
│   │   │           ├── City.java
│   │   │           ├── GeneticAlgorithm.java
│   │   │           ├── Route.java
│   │   │           └── HelloApplication.java
│   │   └── resources
│   ├── test
│       └── java
│           └── com
│               └── davu
│                   └── GeneticAlgorithmTest.java
├── README.md
└── pom.xml (or build.gradle)
```
## Key Components
**City.java:** Represents a city with x and y coordinates.<br/><br/>
**Route.java:** Represents a route consisting of a sequence of cities and includes methods to calculate the total distance and fitness of the route.<br/><br/>
**GeneticAlgorithm.java:** Implements the genetic algorithm to find the optimal TSP route. Includes methods for initialization, selection, crossover, mutation, and 2-opt optimization.<br/><br/>
**HelloApplication.java:** JavaFX application that visualizes the TSP solution, animating the path traveled by the salesman.<br/><br/>
**GeneticAlgorithmTest.java:** JUnit test cases to ensure the correctness of the genetic algorithm and its components.<br/><br/>

## Setup Instructions
Prerequisites
- Java Development Kit (JDK) 11 or higher<br/><br/>
- Maven or Gradle (depending on your build system)<br/><br/>
- IntelliJ IDEA, Eclipse, or any Java IDE<br/><br/>
- JavaFX SDK<br/><br/>

JavaFX Setup
- Download the JavaFX SDK from Gluon.<br/><br/>
- Extract the SDK to a directory on your machine.<br/><br/>
- Add the JavaFX libraries to your project's classpath.<br/><br/>

Project Setup
- Clone the repository:

```bash
git clone https://github.com/yourusername/tsp-genetic-algorithm.git
cd tsp-genetic-algorithm
```
- Import the project into your IDE.<br/><br/>
- Add the JavaFX SDK to the project’s libraries.<br/><br/>
- If using Maven, ensure your pom.xml includes the following dependency for JavaFX:
```xml
<dependency>
<groupId>org.openjfx</groupId>
<artifactId>javafx-controls</artifactId>
<version>17.0.2</version>
</dependency>
```
- If using Gradle, ensure your build.gradle includes:
```groovy
implementation 'org.openjfx:javafx-controls:17.0.2'
```
## Running the Application To run the TSP visualizer:

- Open HelloApplication.java in your IDE.
- Run the HelloApplication class:
A window will open showing the cities and the path calculated by the genetic algorithm. The path will be animated to show the travel between cities.

## Algorithm Details 

The genetic algorithm works by evolving a population of routes over several generations. 

### The key steps are:

- Initialization: Randomly generate a population of possible routes.<br/><br/>
- Selection: Choose the best routes from the current population to be parents.<br/><br/>
- Crossover: Combine two parent routes to produce a child route.<br/><br/>
- Mutation: Randomly alter the child route to introduce variation.<br/><br/>
- Optimization (2-opt): Refine the route by reversing segments to reduce the total distance.<br/><br/>
- Iteration: Repeat the selection, crossover, mutation, and optimization steps for a fixed number of generations.<br/><br/>
- Visualization: The solution is visualized using JavaFX, where cities are displayed as red dots and the route between them is shown as an animated blue line. The cities are placed randomly within the canvas, and the route is drawn dynamically.

## Testing Automated tests are provided using JUnit:

- Run Tests: Execute the GeneticAlgorithmTest class to run all tests. <br/><br/>
- Test Coverage: Tests cover initialization, fitness calculation, selection, crossover, mutation, 2-opt optimization, and integration of the algorithm.<br/><br/>
- Running Tests: In your IDE, right-click on GeneticAlgorithmTest.java and select "Run" to execute the tests.<br/><br/>

## Contributing 

Contributions are welcome! Please fork this repository and submit a pull request for any enhancements, bug fixes, or additional features.

### Guidelines

- Ensure that your code adheres to the existing style and structure.
- Write tests for any new functionality.
- Update the README.md if necessary.

## License 

This project is licensed under the MIT License. See the LICENSE file for details.

### Summary

- **Introduction**: Provides a brief overview of the project and the problem it solves.
- **Project Structure**: Outlines the organization of the project files.
- **Setup Instructions**: Guides the user through setting up the development environment and dependencies.
- **Running the Application**: Instructions for running the JavaFX-based TSP visualizer.
- **Algorithm Details**: Explanation of how the genetic algorithm works and its visualization.
- **Testing**: Details about running automated tests and ensuring the correctness of the implementation.
- **Contributing**: Guidelines for contributing to the project.
- **License**: Specifies the licensing terms.

This `README.md` should help users and contributors understand, set up, and work with your project effectively.









