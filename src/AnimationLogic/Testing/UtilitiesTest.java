package AnimationLogic.Testing;

import AnimationLogic.Miscellaneous.Utilities;
import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import static CityGenerating.CityGenerator.city;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilitiesTest {

//    @Test
//    void computeShortestPathForAllCars()
//      shortest path a fost testat si merge, noi acum doar punem ala pt fiecare masina cu un for
//    }

    private Queue<Pair<Integer, Car>> generateQueueOfCars(){
        Queue<Pair<Integer, Car>> testStructure = new LinkedList<>();
        CityGenerator.generateCity();
        var cars = city.getCars();
        for (int i = 0, carsSize = cars.size(); i < carsSize; i++) {
            Car car = cars.get(i);
            testStructure.offer(new Pair<>(i, car));
        }
        return testStructure;
    }
    @Test
    void getIndexOfCarInQueue0() {
        var test1 = generateQueueOfCars();
        var test1Array = test1.stream().map(Pair::getValue).collect(Collectors.toCollection(ArrayList::new));
        var carSubject = city.getCars().get(0);
        var result = Utilities.getIndexOfCarInQueue(test1, carSubject);
        assertEquals(test1Array.indexOf(carSubject), result);
    }
    @Test
    void getIndexOfCarInQueue1() {
        var test1 = generateQueueOfCars();
        var test1Array = test1.stream().map(Pair::getValue).collect(Collectors.toCollection(ArrayList::new));
        var carSubject = city.getCars().get(1);
        var result = Utilities.getIndexOfCarInQueue(test1, carSubject);
        assertEquals(test1Array.indexOf(carSubject), result);
    }

    @Test
    void correctDistanceOfAllCars() {
        //s-ar putea sa nu mai fie nevoie de el
    }

    @Test
    void existsACarOnStreetsTrue() {
        CityGenerator.generateCity();
        assertEquals(city.getCars().size() > 0, Utilities.existsACarOnStreets());
    }

    @Test
    void existsACarOnStreetsFalse() {
        CityGenerator.generateCity();
        city.getStreets().forEach(street -> {
            street.getCarsReversed().clear();
            street.getCars().clear();
        });
        assertEquals(false, Utilities.existsACarOnStreets());
    }

    @Test
    void setAllCarsSpeed1() {
        CityGenerator.generateCity();
        int targetSpeed = 1;
        int okCars;
        Utilities.setAllCarsSpeed(targetSpeed);
        okCars = (int) city.getCars().stream().filter(car -> car.getSpeed() == targetSpeed).count();
        assertEquals(okCars, city.getCars().size());
    }
    @Test
    void setAllCarsSpeed2() {
        CityGenerator.generateCity();
        int targetSpeed = 2;
        int okCars;
        Utilities.setAllCarsSpeed(targetSpeed);
        okCars = (int) city.getCars().stream().filter(car -> car.getSpeed() == targetSpeed).count();
        assertEquals(okCars, city.getCars().size());
    }
    @Test
    void setAllCarsSpeed3() {
        CityGenerator.generateCity();
        int targetSpeed = 3;
        int okCars;
        Utilities.setAllCarsSpeed(targetSpeed);
        okCars = (int) city.getCars().stream().filter(car -> car.getSpeed() == targetSpeed).count();
        assertEquals(okCars, city.getCars().size());
    }
    @Test
    void setAllCarsSpeed4() {
        CityGenerator.generateCity();
        int targetSpeed =4;
        int okCars;
        Utilities.setAllCarsSpeed(targetSpeed);
        okCars = (int) city.getCars().stream().filter(car -> car.getSpeed() == targetSpeed).count();
        assertEquals(okCars, city.getCars().size());
    }
}