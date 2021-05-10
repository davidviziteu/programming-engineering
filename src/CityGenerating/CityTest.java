package CityGenerating;


import CarGenerating.Car;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;

class CityTest {
    Street street = new Street("Street", 9, 11, 2, 9, 10, 2, 5, -1);

    Intersection intersection = new Intersection("Intersection", new ArrayList<Integer>(Arrays.asList(2)), 0, 2, true);

    TrafficLights trafficLight = new TrafficLights(60, 1, 3, 3);

    ArrayList<Street> streets = new ArrayList<>();
    ArrayList<Intersection> intersections = new ArrayList<>();
    ArrayList<TrafficLights> trafficLights = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();

     @org.junit.jupiter.api.Test
    void getStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setStreets(streets);
        assertEquals(streets, instance.getStreets());
    }

     @org.junit.jupiter.api.Test
    void setStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setStreets(streets);
        assertEquals(streets, instance.getStreets());
    }

     @org.junit.jupiter.api.Test
    void getCity() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setIntersections(intersections);
        assertEquals(intersections, instance.getCity());
    }

     @org.junit.jupiter.api.Test
    void setIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setIntersections(intersections);
        assertEquals(intersections, instance.getCity());
    }

     @org.junit.jupiter.api.Test
    void getNrOfStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfStreets(0);
        assertEquals(0, instance.getNrOfStreets());
    }

     @org.junit.jupiter.api.Test
    void setNrOfStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfStreets(0);
        assertEquals(0, instance.getNrOfStreets());
    }

     @org.junit.jupiter.api.Test
    void getNrOfIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfIntersections(0);
        assertEquals(0, instance.getNrOfIntersections());
    }

     @org.junit.jupiter.api.Test
    void setNrOfIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfIntersections(0);
        assertEquals(0, instance.getNrOfIntersections());
    }

     @org.junit.jupiter.api.Test
    void getStreetByName() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        assertEquals(street, instance.getStreetByName("Street"));
    }

     @org.junit.jupiter.api.Test
    void getIntersectionByName() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        intersections.add(intersection);
        instance.setIntersections(intersections);
        assertEquals(intersection, instance.getIntersectionByName("Intersection"));
    }

     @org.junit.jupiter.api.Test
    void getStreetByIndex() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        assertEquals(street, instance.getStreetByIndex(0));
    }

     @org.junit.jupiter.api.Test
    void getStreetByCoordonates() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        assertEquals(0, instance.getStreetByCoordonates(2, 5));
    }

     @org.junit.jupiter.api.Test
    void getIntersectionByIndex() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        intersections.add(intersection);
        instance.setIntersections(intersections);
        assertEquals(intersection, instance.getIntersectionByIndex(0));
    }

     @org.junit.jupiter.api.Test
    void getTLightsById() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
    }

     @org.junit.jupiter.api.Test
    void getCars() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        Car car = new Car();
        cars.add(car);
        instance.setCars(cars);
        assertEquals(cars, instance.getCars());
    }

     @org.junit.jupiter.api.Test
    void setCars() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        Car car = new Car();
        cars.add(car);
        instance.setCars(cars);
        assertEquals(cars, instance.getCars());
    }
}