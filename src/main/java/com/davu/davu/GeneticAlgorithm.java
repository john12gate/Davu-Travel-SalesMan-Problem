package com.davu.davu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
    private ArrayList<City> cities;
    private Random random = new Random();
    private static final int POPULATION_SIZE = 500;
    private static final int NUM_GENERATIONS = 2000;
    private static final double MUTATION_RATE = 0.01;
    private static final int TOURNAMENT_SIZE = 5;

    public GeneticAlgorithm(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> run() {
        ArrayList<Route> population = initializePopulation();

        for (int i = 0; i < NUM_GENERATIONS; i++) {
            population = evolve(population);
        }

        Collections.sort(population);
        Route bestRoute = population.get(0);

        // Optimize the best route using 2-opt
        return optimizeRoute(bestRoute.getCities());
    }

    public ArrayList<Route> initializePopulation() {
        ArrayList<Route> population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            ArrayList<City> newRoute = new ArrayList<>(cities);
            Collections.shuffle(newRoute);
            population.add(new Route(newRoute));
        }
        return population;
    }

    public ArrayList<Route> evolve(ArrayList<Route> population) {
        ArrayList<Route> newPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            Route parent1 = tournamentSelection(population);
            Route parent2 = tournamentSelection(population);
            Route child = crossover(parent1, parent2);
            mutate(child);
            newPopulation.add(child);
        }

        Collections.sort(newPopulation);
        return newPopulation;
    }

    public Route tournamentSelection(ArrayList<Route> population) {
        ArrayList<Route> tournament = new ArrayList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomIndex = random.nextInt(POPULATION_SIZE);
            tournament.add(population.get(randomIndex));
        }
        Collections.sort(tournament);
        return tournament.get(0);
    }

    public Route crossover(Route parent1, Route parent2) {
        ArrayList<City> child = new ArrayList<>(Collections.nCopies(cities.size(), null));

        int start = random.nextInt(cities.size());
        int end = random.nextInt(cities.size());

        for (int i = start; i <= end; i++) {
            child.set(i, parent1.getCities().get(i));
        }

        int childIndex = 0;
        for (City city : parent2.getCities()) {
            if (!child.contains(city)) {
                while (child.get(childIndex) != null) {
                    childIndex++;
                }
                child.set(childIndex, city);
            }
        }

        return new Route(child);
    }


    public void mutate(Route route) {
        int size = route.getCities().size();
        boolean mutated = false;  // Track if mutation happens

        for (int i = 0; i < size; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                int swapWith = random.nextInt(size);
                if (swapWith != i) {  // Ensure that we swap different cities
                    Collections.swap(route.getCities(), i, swapWith);
                    mutated = true;
                }
            }
        }

        // Force a swap if no mutation happened due to random chance
        if (!mutated) {
            int i = random.nextInt(size);
            int swapWith = (i + 1) % size;  // Ensure a valid swap
            Collections.swap(route.getCities(), i, swapWith);
        }
    }
    // 2-opt optimization method
    public ArrayList<City> optimizeRoute(ArrayList<City> route) {
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            double bestDistance = calculateTotalDistance(route);
            for (int i = 1; i < route.size() - 1; i++) {
                for (int j = i + 1; j < route.size(); j++) {
                    ArrayList<City> newRoute = twoOptSwap(route, i, j);
                    double newDistance = calculateTotalDistance(newRoute);
                    if (newDistance < bestDistance) {
                        route = newRoute;
                        bestDistance = newDistance;
                        improvement = true;
                    }
                }
            }
        }
        return route;
    }

    private ArrayList<City> twoOptSwap(ArrayList<City> route, int i, int j) {
        ArrayList<City> newRoute = new ArrayList<>(route.subList(0, i));
        ArrayList<City> reversedSublist = new ArrayList<>(route.subList(i, j + 1));
        Collections.reverse(reversedSublist);
        newRoute.addAll(reversedSublist);
        newRoute.addAll(route.subList(j + 1, route.size()));
        return newRoute;
    }

    public double calculateTotalDistance(ArrayList<City> route) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += route.get(i).distanceTo(route.get(i + 1));
        }
        totalDistance += route.get(route.size() - 1).distanceTo(route.get(0));
        return totalDistance;
    }
}
