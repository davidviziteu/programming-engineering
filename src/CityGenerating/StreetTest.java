package CityGenerating;

import CarGenerating.Car;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static CityGenerating.CityGenerator.city;

class StreetTest {
    Street instance = new Street("Strada1",6,8, 2,1,1,0, 0, 1);
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @org.junit.jupiter.api.Test
    void getName() {
        var result = instance.getName();
        assertEquals("Strada1",result);
    }
    @org.junit.jupiter.api.Test
    void getNameNotNull() {
        var result = instance.getName();
        assertNotNull(instance.getName());
    }
    @org.junit.jupiter.api.Test
    void getCars() {
        var result = instance.getCars(1);
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void getDircetionTest() {
        var result = instance.getDirection();
        assertEquals(1,result);
    }

    @org.junit.jupiter.api.Test
    void setName() {
        instance.setName("test");
        assertEquals("test",instance.getName());

    }

    @org.junit.jupiter.api.Test
    void getIntersectionSource() {
        var result = instance.getIntersectionSource();
        assertEquals(6,result);
    }

    @org.junit.jupiter.api.Test
    void setIntersectionSource() {
        instance.setIntersectionSource(0);
        assertEquals(0,instance.getIntersectionSource());
    }

    @org.junit.jupiter.api.Test
    void getIntersectionDestination() {
        var result = instance.getIntersectionDestination();
        assertEquals(8,result);
    }

    @org.junit.jupiter.api.Test
    void setIntersectionDestination() {
        instance.setIntersectionDestination(0);
        assertEquals(0,instance.getIntersectionDestination());
    }

    @org.junit.jupiter.api.Test
    void getLength() {
        var result = instance.getLength();
        assertEquals(2,result);
    }

    @org.junit.jupiter.api.Test
    void setLength() {
        instance.setLength(1);
        assertEquals(1,instance.getLength());
    }

    @org.junit.jupiter.api.Test
    void getPosX() {
        var result = instance.getPosX();
        assertEquals(0,result);
    }

    @org.junit.jupiter.api.Test
    void setPosX() {
        instance.setPosX(1);
        assertEquals(1,instance.getPosX());
    }

    @org.junit.jupiter.api.Test
    void getPosY() {
        var result = instance.getPosY();
        assertEquals(0,result);
    }



    @org.junit.jupiter.api.Test
    void setPosY() {
        instance.setPosY(1);
        assertEquals(1,instance.getPosY());
    }

    @org.junit.jupiter.api.Test
    void getTrafficLights() {
        var result = instance.getTrafficLights();
        assertEquals(1,result);
    }

    @org.junit.jupiter.api.Test
    void setTrafficLights() {
        instance.setTrafficLights(2);
        assertEquals(2,instance.getTrafficLights());
    }

    @org.junit.jupiter.api.Test
    void getTrafficLightsReversed() {
        var result = instance.getTrafficLightsReversed();
        assertEquals(1,result);
    }

    @org.junit.jupiter.api.Test
    void getQueuePosition() {
        var result = instance.getQueuePosition(1);
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void getQueuePositionReversed() {
        var result = instance.getQueuePosition(0);
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void getCarsTest() {
        var result = instance.getCars();
        assertNotNull(result);
    }
    @org.junit.jupiter.api.Test
    void peekQueueTest(){
        Car testCar = new Car();
        instance.addCar(testCar,0);
        assertEquals(instance.peekQueue(0),testCar);
    }
    @org.junit.jupiter.api.Test
    void peekQueueReversedTest(){
        Car testCar = new Car();
        instance.addCar(testCar,1);
        assertEquals(instance.peekQueue(1),testCar);
    }
    @org.junit.jupiter.api.Test
    void getLaneThrowsExceptionTest(){
        Street inst = new Street("Strada1", 6, 8, 2, 1, 0, 1, 0, 1);
        assertThrows(NullPointerException.class,
                ()->{
                    inst.getLane();
                });
    }
    @org.junit.jupiter.api.Test
    void peekQueueThrowsException() {
        assertThrows(NullPointerException.class,
                ()->{
                    instance.peekQueue(1);
                });
    }

    @org.junit.jupiter.api.Test
    void addCarTest(){
        Car testCar = new Car();
        int expected = instance.cars.size();
        instance.addCar(testCar,1);
        assertEquals(expected,instance.cars.size()-1);
    }
    @org.junit.jupiter.api.Test
    void addCarOverFLowTest(){
        Car testCar1 = new Car();
        Car testCar2 = new Car();
        Car testCar3 = new Car();
        instance.addCar(testCar1,1);
        instance.addCar(testCar2,1);
        instance.addCar(testCar3,1);
        int expected = instance.cars.size();
        assertEquals(expected,instance.getLength());
    }

    @org.junit.jupiter.api.Test
    void addCarOverFLowOutputTest(){
        Car testCar1 = new Car();
        Car testCar2 = new Car();
        Car testCar3 = new Car();
        instance.addCar(testCar1,1);
        instance.addCar(testCar2,1);
        instance.addCar(testCar3,1);
        assertEquals("Street" + instance.getName() + "is full!", outputStreamCaptor.toString()
                .trim());
    }

    @org.junit.jupiter.api.Test
    void addCarRversedOverFLowTest(){
        Car testCar1 = new Car();
        Car testCar2 = new Car();
        Car testCar3 = new Car();
        instance.addCar(testCar1,0);
        instance.addCar(testCar2,0);
        instance.addCar(testCar3,0);
        int expected = instance.carsReversed.size();
        assertEquals(expected,instance.getLength());
    }

    @org.junit.jupiter.api.Test
    void addCarReversedOverFLowOutputTest(){
        Car testCar1 = new Car();
        Car testCar2 = new Car();
        Car testCar3 = new Car();
        instance.addCar(testCar1,0);
        instance.addCar(testCar2,0);
        instance.addCar(testCar3,0);
        assertEquals("Street" + instance.getName() + "is full!", outputStreamCaptor.toString()
                .trim());
    }

    @org.junit.jupiter.api.Test
    void addCarReverseTest(){
        Car testCar = new Car();
        int expected = instance.carsReversed.size();
        instance.addCar(testCar,0);
        assertEquals(expected,instance.carsReversed.size()-1);
    }

    @org.junit.jupiter.api.Test
    void getCarsReversedTest() {
        var result = instance.getCarsReversed();
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void getCarsReversed() {
        var result = instance.getCars(0);
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void removeCarWhenNoCars() {
        int initSize = instance.cars.size();
        instance.removeCar(1);
        assertEquals(initSize,instance.cars.size());
    }
    @org.junit.jupiter.api.Test
    void removeCarTest(){
        Car carTest = new Car();
        Car carTes2 = new Car();
        instance.addCar(carTest,1);
        instance.addCar(carTes2,1);
        int expected = instance.cars.size();
        instance.removeCar(1);
        assertEquals(expected-1,instance.cars.size());
    }
    @org.junit.jupiter.api.Test
    void removeCarReversedTest(){
        Car carTest = new Car();
        Car carTes2 = new Car();
        instance.addCar(carTest,0);
        instance.addCar(carTes2,0);
        int expected = instance.carsReversed.size();
        instance.removeCar(0);
        assertEquals(expected-1,instance.carsReversed.size());
    }

    @org.junit.jupiter.api.Test
    void removeCarReversedWhenNoCars() {
        int initSize = instance.cars.size();
        instance.removeCar(0);
        assertEquals(initSize,instance.cars.size());
    }



}