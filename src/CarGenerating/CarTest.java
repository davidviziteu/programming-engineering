package CarGenerating;


import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.AssertJUnit.assertEquals;

class CarTest {

    @org.junit.jupiter.api.Test
    void setSpeed_5() {
        Car car = new Car();
        car.setSpeed(-5);

        Assertions.assertEquals(0, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_50() {
        Car car = new Car();
        car.setSpeed(50);

        Assertions.assertEquals(50, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_150() {
        Car car = new Car();
        car.setSpeed(150);

        Assertions.assertEquals(150, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_0() {
        Car car = new Car();
        car.setSpeed(0);

        Assertions.assertEquals(0, car.getSpeed());
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

    @org.junit.jupiter.api.Test
    void testSetInitialPosition() {

    }


}