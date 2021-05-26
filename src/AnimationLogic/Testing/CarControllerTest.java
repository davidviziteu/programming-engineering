package AnimationLogic.Testing;

import AnimationLogic.CarAnimator;
import AnimationLogic.CarController;
import AnimationLogic.Miscellaneous.Utilities;
import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Pair;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static CityGenerating.CityGenerator.city;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test pt car controller
 */
class CarControllerTest {

//    @Test
//    void isFinalIntersection() { //hardcoded
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
    void carCanBeMovedFalseToFull() {
        Queue<Pair<Integer, Car>> testq = new LinkedList<>();
        CityGenerator.generateCity();
        var carObject = city.getCars().get(0);
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        assertFalse(CarController.carCanBeMoved(carObject, testq, 4, 0));
    }
    @Test
    void carCanBeMovedFalseNotArrived() {
        Queue<Pair<Integer, Car>> testq = new LinkedList<>();
        CityGenerator.generateCity();
        var carObject = city.getCars().get(0);
        carObject.setDistance(2);
        Utilities.setAllCarsSpeed(1);
        var t = new Thread(CarAnimator.getInstance());
        t.start();
        assertTrue(CarController.carCanBeMoved(carObject, testq, 5, 0));
    }
    @Test
    void carCanBeMovedTrue1() {
        Queue<Pair<Integer, Car>> testq = new LinkedList<>();
        CityGenerator.generateCity();
        var carObject = city.getCars().get(0);
        carObject.setDistance(0);
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        testq.offer(new Pair<>(0, carObject));
        assertTrue(CarController.carCanBeMoved(carObject, testq, 5, 0));
    }


//    @Test
//    void tryMoveFirstCar() {
//
//    }
}
