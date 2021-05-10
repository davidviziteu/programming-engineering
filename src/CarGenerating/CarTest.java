package CarGenerating;


import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

class CarTest {

    @org.junit.jupiter.api.Test
    void testSetSpeed_5() {
        Car car = new Car();
        int expectedSpeed=car.getSpeed();
        car.setSpeed(-5);

        Assertions.assertEquals(expectedSpeed, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testSetSpeed_50() {
        Car car = new Car();
        car.setSpeed(50);

        Assertions.assertEquals(50, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testSetSpeed_150() {
        Car car = new Car();
        car.setSpeed(150);

        Assertions.assertEquals(150, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testSetSpeed_0() {
        Car car = new Car();
        car.setSpeed(0);

        Assertions.assertEquals(0, car.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void testSetCurrentPosition_1() {
        Car car = new Car();
        car.setCurrentPosition(1);
        Assertions.assertEquals(1, car.getCurrentPosition());
    }


    @org.junit.jupiter.api.Test
    void testSetCurrentPosition_13() {
        Car car = new Car();
        car.setCurrentPosition(13);
        Assertions.assertEquals(0, car.getCurrentPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetCurrentPosition_neg1() {
        Car car = new Car();
        car.setCurrentPosition(-1);
        Assertions.assertEquals(0, car.getCurrentPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetCurrentPosition_6() {
        Car car = new Car();
        car.setCurrentPosition(6);
        Assertions.assertEquals(6, car.getCurrentPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetDistance_2() {
        Car car = new Car();
        car.setDistance(2);
        Assertions.assertEquals(2, car.getDistance());
    }

    @org.junit.jupiter.api.Test
    void testSetDistance_6() {
        Car car = new Car();
        car.setDistance(6);
        Assertions.assertEquals(-1, car.getDistance());
    }

    @org.junit.jupiter.api.Test
    void testSetDistance_neg3() {
        Car car = new Car();
        car.setDistance(-3);
        Assertions.assertEquals(-1, car.getDistance());
    }

    @org.junit.jupiter.api.Test
    void testEquals(){
        Car first=new Car();
        Car second=first;
        Assertions.assertEquals(true,first==second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_null(){
        Car first=new Car();
        Car second=null;
        Assertions.assertEquals(false,first==second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_nullFirst(){
        Car first=null;
        Car second=new Car();
        Assertions.assertEquals(false,first==second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_different(){
        Car first=new Car();
        Car second=new Car();
        Assertions.assertEquals(false,first==second);
    }

    @org.junit.jupiter.api.Test
    void testEquals_bothNull(){
        Car first=null;
        Car second=null;
        Assertions.assertEquals(true,first==second);
    }

    @org.junit.jupiter.api.Test
    void testSetDirection_1(){
        Car car = new Car();
        car.setDirection(1);
        Assertions.assertEquals(1, car.getDirection());
    }

    @org.junit.jupiter.api.Test
    void testSetDirection_2(){
        Car car = new Car();
        int expectedDirection=car.getDirection();
        car.setDirection(2);

        Assertions.assertEquals(expectedDirection, car.getDirection());
    }

    @org.junit.jupiter.api.Test
    void testSetFinalPosition_10(){
        Car car=new Car();
        int expectedFinalPosition=car.getFinalPosition();
        car.setFinalPosition(10);

        Assertions.assertEquals(expectedFinalPosition, car.getFinalPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetFinalPosition_neg5(){
        Car car=new Car();
        int expectedFinalPosition=car.getFinalPosition();
        car.setFinalPosition(-5);

        Assertions.assertEquals(expectedFinalPosition, car.getFinalPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetFinalPosition_6(){
        Car car=new Car();
        car.setFinalPosition(6);

        Assertions.assertEquals(6, car.getFinalPosition());
    }

    @org.junit.jupiter.api.Test
    void testSetShortestPathDistance(){
        Car car=new Car();
        car.setShortestPathDistance(5);

        Assertions.assertEquals(5, car.getShortestPathDistance());
    }

    @org.junit.jupiter.api.Test
    void testSetShortestPathTime(){
        Car car=new Car();
        car.setShortestPathTime(5);

        Assertions.assertEquals(5, car.getShortestPathTime());
    }

    @org.junit.jupiter.api.Test
    void testSetShortestPath(){
        Car car=new Car();
        List<Integer> help = new ArrayList<Integer>();
        help.add(2);
        help.add(3);
        car.setShortestPath(help);

        Assertions.assertEquals(help, car.getShortestPath());
    }

    @org.junit.jupiter.api.Test
    void testHashCode(){
        Car car=new Car();
        int helpNeedABetterVariableName = car.hashCode();

        Assertions.assertEquals(helpNeedABetterVariableName, car.hashCode());
    }

    @org.junit.jupiter.api.Test
    void testCompareTo(){
        Car first=new Car();
        Car second=new Car();
        int result=first.compareTo(second);

        Assertions.assertEquals(result,first.compareTo(second));
    }

    @org.junit.jupiter.api.Test
    void testToString_causeWhyNot(){
        Car car= new Car();
        String help = car.toString();

        Assertions.assertEquals(help,car.toString());
    }


}