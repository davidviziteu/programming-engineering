package CarGenerating;

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {


    @Test
    void setDistance() {
    }

    @Test
    void testSetInitialPosition() {
    }

    @Test
    void setFinalPosition() {
    }

    @org.junit.jupiter.api.Test
    void setSpeed_5() {
        Car car = new Car();
        car.setSpeed(-5);

        Assert.assertEquals(0, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_50() {
        Car car = new Car();
        car.setSpeed(50);

        Assert.assertEquals(50, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_150() {
        Car car = new Car();
        car.setSpeed(150);

        Assert.assertEquals(150, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setSpeed_0() {
        Car car = new Car();
        car.setSpeed(0);

        Assert.assertEquals(0, car.getSpeed());
    }
}