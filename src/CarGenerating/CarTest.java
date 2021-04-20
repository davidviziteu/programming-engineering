package CarGenerating;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @org.junit.jupiter.api.Test
    void getInitialPosition() {
        Car car = new Car();
        car.setInitialPosition(1);
        if(car.getInitialPosition() != 1)
            fail("incorrect result!");
        //assertEquals("Result",1,car.setInitialPosition(1));
    }

    @Test
    void getFinalPosition() {
        
    }

    @Test
    void getSpeed() {
    }

    @org.junit.jupiter.api.Test
    void getDistance() {
        Car car = new Car();
        car.setDistance(5);
        if(car.getDistance()!=5)
            fail("incorrect result");

    }

    @org.junit.jupiter.api.Test
    void setDistance() {
        Car car = new Car();
        car.setDistance(5);
        if(car.getDistance()!=5)
            fail("incorrect result");
    }

    @Test
    void testSetInitialPosition() {

    }

    @Test
    void setFinalPosition() {
    }

    @Test
    void setSpeed() {
    }
}