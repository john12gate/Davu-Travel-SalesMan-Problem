package com.davu.davu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {

    private static ArrayList<City> cities;
    private static ArrayList<City> bestRoute;
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Traveling Salesman Problem Visualization");

        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        // Draw the cities
        drawCities();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Animate the path
        animatePath();
    }

    private void drawCities() {
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Arial", 10));

        for (City city : cities) {
            double x = city.getX();
            double y = city.getY();
            gc.fillOval(x - 5, y - 5, 10, 10);
        }
    }

    private void drawPathSegment(int fromIndex, int toIndex) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);

        City from = bestRoute.get(fromIndex);
        City to = bestRoute.get(toIndex);

        gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
    }

    private void animatePath() {
        Timeline timeline = new Timeline();

        for (int i = 0; i < bestRoute.size(); i++) {
            int fromIndex = i;
            int toIndex = (i + 1) % bestRoute.size();

            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.1), event -> {
                drawPathSegment(fromIndex, toIndex);
            });

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
    }

    public static void main(String[] args) {
        // Example 1: Small number of cities
        cities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            cities.add(new City("City " + (i + 1), 100 + random.nextInt(600), 100 + random.nextInt(400)));
        }

        long startTime = System.currentTimeMillis();
        GeneticAlgorithm ga = new GeneticAlgorithm(cities);
        bestRoute = ga.run();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Time taken for 10 cities: " + duration + " milliseconds");

        // Example 2: Larger number of cities
        cities.clear();
        for (int i = 0; i < 100; i++) {
            cities.add(new City("City " + (i + 1), 100 + random.nextInt(600), 100 + random.nextInt(400)));
        }

        startTime = System.currentTimeMillis();
        ga = new GeneticAlgorithm(cities);
        bestRoute = ga.run();
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;

        System.out.println("Time taken for 100 cities: " + duration + " milliseconds");

        // Launch the JavaFX application for visualization
        launch(args);
    }
}
