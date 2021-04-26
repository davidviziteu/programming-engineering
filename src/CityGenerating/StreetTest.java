package CityGenerating;

import CarGenerating.Car;

import static org.junit.jupiter.api.Assertions.*;

class StreetTest {
    Street instance = new Street("Strada1",6,8, 2,1,1,0, 0, 1);

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
    void getCars() {
        var result = instance.getCars(1);
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
}