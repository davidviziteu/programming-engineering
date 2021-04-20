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

    @Test
    void getDistance() {
    }

    @Test
    void setDistance() {
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