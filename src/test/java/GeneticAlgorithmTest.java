import com.davu.davu.City;
import com.davu.davu.GeneticAlgorithm;
import com.davu.davu.Route;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class GeneticAlgorithmTest {

    private ArrayList<City> cities;
    private GeneticAlgorithm ga;

    @Before
    public void setUp() {
        // Initialize cities and GeneticAlgorithm before each test
        cities = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            cities.add(new City("City " + (i + 1), random.nextInt(200), random.nextInt(200)));
        }

        ga = new GeneticAlgorithm(cities);
    }

    @Test
    public void testInitialization() {
        // Test if the GeneticAlgorithm initializes properly
        ArrayList<Route> population = ga.initializePopulation();

        assertEquals("Population size should be correct", 500, population.size());
        for (Route route : population) {
            assertEquals("Each route should have the same number of cities", cities.size(), route.getCities().size());
            assertTrue("Each route should contain all cities", route.getCities().containsAll(cities));
        }
    }

    @Test
    public void testFitnessFunction() {
        // Test if the fitness function works correctly
        Route route = new Route(new ArrayList<>(cities));
        double fitness = route.getFitness();

        assertTrue("Fitness should be a positive value", fitness > 0);
    }

    @Test
    public void testSelection() {
        // Test if the selection method works correctly
        ArrayList<Route> population = ga.initializePopulation();
        Route selectedRoute = ga.tournamentSelection(population);

        assertNotNull("Selected route should not be null", selectedRoute);
        assertEquals("Selected route should have the same number of cities as the original routes", cities.size(), selectedRoute.getCities().size());
    }

    @Test
    public void testMutation() {
        // Test mutation function
        Route originalRoute = new Route(new ArrayList<>(cities));
        Route mutatedRoute = new Route(new ArrayList<>(cities));
        ga.mutate(mutatedRoute);

        assertNotEquals("Mutated route should differ from the original route", originalRoute.getCities(), mutatedRoute.getCities());
    }

    @Test
    public void testCrossover() {
        // Test crossover function
        Route parent1 = new Route(new ArrayList<>(cities));
        List<City> shuffledCities = new ArrayList<>(cities);
        Collections.shuffle(shuffledCities);
        Route parent2 = new Route(new ArrayList<>(shuffledCities));

        Route child = ga.crossover(parent1, parent2);

        assertEquals("Child should have the same number of cities as parents", cities.size(), child.getCities().size());
        assertTrue("Child should contain all cities", child.getCities().containsAll(cities));
    }

    @Test
    public void testTwoOptOptimization() {
        // Test 2-opt optimization
        ArrayList<City> route = new ArrayList<>(cities);
        ArrayList<City> optimizedRoute = ga.optimizeRoute(route);

        assertTrue("Optimized route should have equal or lower distance",
                ga.calculateTotalDistance(optimizedRoute) <= ga.calculateTotalDistance(route));
    }

    @Test
    public void testCalculateTotalDistance() {
        // Test calculation of the total distance for a route
        Route route = new Route(new ArrayList<>(cities));
        double totalDistance = ga.calculateTotalDistance(route.getCities());

        assertTrue("Total distance should be a positive value", totalDistance > 0);
    }


    @Test
    public void testPopulationEvolve() {
        ArrayList<Route> initialPopulation = ga.initializePopulation();
        Route bestInitialRoute = initialPopulation.get(0);

        ArrayList<Route> evolvedPopulation = ga.evolve(initialPopulation);
        Route bestEvolvedRoute = evolvedPopulation.get(0);

        double initialDistance = ga.calculateTotalDistance(bestInitialRoute.getCities());
        double evolvedDistance = ga.calculateTotalDistance(bestEvolvedRoute.getCities());

        // Allow for very small differences due to floating-point precision
        assertTrue("Best evolved route should be equal or better than the best initial route",
                evolvedDistance <= initialDistance || Math.abs(evolvedDistance - initialDistance) < 1e-6);

        // Additionally, check that the evolved distance is not significantly worse than the initial one
        assertTrue("Evolved route should not be significantly worse than the initial route",
                evolvedDistance <= initialDistance * 1.05); // Allow a 5% tolerance
    }
    @Test
    public void testOptimizeFinalRoute() {
        // Test if the final route is optimized after running the algorithm
        ArrayList<City> initialBestRoute = ga.run();
        ArrayList<City> optimizedRoute = ga.optimizeRoute(initialBestRoute);

        double initialDistance = ga.calculateTotalDistance(initialBestRoute);
        double optimizedDistance = ga.calculateTotalDistance(optimizedRoute);

        assertTrue("Optimized distance should be less than or equal to initial distance", optimizedDistance <= initialDistance);
    }

//    @Test
//    public void testAlgorithmIntegration() {
//        // Test the complete integration of the GeneticAlgorithm class
//        ArrayList<City> finalRoute = ga.run();
//
//        assertNotNull("Final route should not be null", finalRoute);
//        assertEquals("Final route should contain all cities", cities.size(), finalRoute.size());
//        assertTrue("Final route should contain all cities", finalRoute.containsAll(cities));
//    }

    @Test
    public void testAlgorithmIntegration() {
        long startTime = System.currentTimeMillis();

        // Test the complete integration of the GeneticAlgorithm class
        ArrayList<City> finalRoute = ga.run();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Time taken for algorithm to run: " + duration + " milliseconds");

        assertNotNull("Final route should not be null", finalRoute);
        assertEquals("Final route should contain all cities", cities.size(), finalRoute.size());
        assertTrue("Final route should contain all cities", finalRoute.containsAll(cities));
    }

    @Test
    public void testAlgorithmPerformance() {
        int[] sizes = {10, 50, 100, 200, 500};

        for (int size : sizes) {
            cities.clear();
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                cities.add(new City("City " + (i + 1), 100 + random.nextInt(600), 100 + random.nextInt(400)));
            }

            long startTime = System.currentTimeMillis();
            GeneticAlgorithm ga = new GeneticAlgorithm(cities);
            ArrayList<City> bestRoute = ga.run();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("Time taken for " + size + " cities: " + duration + " milliseconds");

            assertNotNull("Final route should not be null", bestRoute);
            assertEquals("Final route should contain all cities", cities.size(), bestRoute.size());
            assertTrue("Final route should contain all cities", bestRoute.containsAll(cities));
        }
    }
}
