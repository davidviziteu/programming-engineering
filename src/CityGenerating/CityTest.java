package CityGenerating;


import CarGenerating.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    Street street = new Street("Street", 9, 11, 2, 9, 10, 2, 5, -1);

    Intersection intersection = new Intersection("Intersection", new ArrayList<Integer>(Arrays.asList(2)), 0, 2, true);

    TrafficLights trafficLight = new TrafficLights(60, 1, 3, 3);

    ArrayList<Street> streets = new ArrayList<>();
    ArrayList<Intersection> intersections = new ArrayList<>();
    ArrayList<TrafficLights> trafficLights = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();

    @Test
    void getStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setStreets(streets);
        Assertions.assertEquals(streets, instance.getStreets());
    }

    @Test
    void setStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setStreets(streets);
        Assertions.assertEquals(streets, instance.getStreets());
    }

    @Test
    void getCity() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setIntersections(intersections);
        Assertions.assertEquals(intersections, instance.getCity());
    }

    @Test
    void setIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setIntersections(intersections);
        Assertions.assertEquals(intersections, instance.getCity());
    }

    @Test
    void getNrOfStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfStreets(0);
        Assertions.assertEquals(0, instance.getNrOfStreets());
    }

    @Test
    void setNrOfStreets() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfStreets(0);
        Assertions.assertEquals(0, instance.getNrOfStreets());
    }

    @Test
    void getNrOfIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfIntersections(0);
        Assertions.assertEquals(0, instance.getNrOfIntersections());
    }

    @Test
    void setNrOfIntersections() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        instance.setNrOfIntersections(0);
        Assertions.assertEquals(0, instance.getNrOfIntersections());
    }

    @Test
    void getStreetByName() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        Assertions.assertEquals(street, instance.getStreetByName("Street"));
    }

    @Test
    void getIntersectionByName() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        intersections.add(intersection);
        instance.setIntersections(intersections);
        Assertions.assertEquals(intersection, instance.getIntersectionByName("Intersection"));
    }

    @Test
    void getStreetByIndex() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        Assertions.assertEquals(street, instance.getStreetByIndex(0));
    }

    @Test
    void getStreetByCoordonates() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        streets.add(street);
        instance.setStreets(streets);
        Assertions.assertEquals(0, instance.getStreetByCoordonates(2, 5));
    }

    @Test
    void getIntersectionByIndex() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        intersections.add(intersection);
        instance.setIntersections(intersections);
        Assertions.assertEquals(intersection, instance.getIntersectionByIndex(0));
    }

    @Test
    void getTLightsById() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
    }

    @Test
    void getCars() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        Car car = new Car();
        cars.add(car);
        instance.setCars(cars);
        Assertions.assertEquals(cars, instance.getCars());
    }

    @Test
    void setCars() {
        CityGenerator.generateCity();
        City instance = CityGenerator.city;
        Car car = new Car();
        cars.add(car);
        instance.setCars(cars);
        Assertions.assertEquals(cars, instance.getCars());
    }
}