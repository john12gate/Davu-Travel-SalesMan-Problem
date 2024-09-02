package com.davu.davu;

public class City {
    private String name;
    private int x;
    private int y;

    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(City city) {
        int dx = this.x - city.getX();
        int dy = this.y - city.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}

