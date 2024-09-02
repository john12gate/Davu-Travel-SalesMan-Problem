package com.davu.davu;

import java.util.ArrayList;

public class Route implements Comparable<Route> {
    private ArrayList<City> cities;
    private double fitness = 0;
    private double distance = 0;

    public Route(ArrayList<City> cities) {
        this.cities = new ArrayList<>(cities);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public double getDistance() {
        if (distance == 0) {
            double totalDistance = 0;
            for (int i = 0; i < cities.size(); i++) {
                City fromCity = cities.get(i);
                City toCity = cities.get((i + 1) % cities.size());
                totalDistance += fromCity.distanceTo(toCity);
            }
            distance = totalDistance;
        }
        return distance;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }

    @Override
    public int compareTo(Route other) {
        return Double.compare(this.getFitness(), other.getFitness());
    }
}

