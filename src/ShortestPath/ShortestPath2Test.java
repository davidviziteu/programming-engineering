package ShortestPath;

import CityGenerating.CityGenerator;
import CityGenerating.Intersection;
import CityGenerating.Street;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestPath2Test {

    @org.junit.jupiter.api.Test
    void minimumDistance() {
    }

    @org.junit.jupiter.api.Test
    void compute() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        System.out.println("compute function test:");
        var test1 = ShortestPath2.compute(ctyInst.getStreets(), 8, 11, ctyInst.getNrOfIntersections());
        var res1 = new Tuple<Integer, List<Integer>>(4, Arrays.asList(10, 11));
        assertEquals(res1.toString(), test1.toString());

    }

    @org.junit.jupiter.api.Test
    void adjacent() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean value=ShortestPath2.adjacent(8, 1, ctyInst.getStreets() );
        boolean value2=ShortestPath2.adjacent(3, 5,ctyInst.getStreets());
        boolean value3=ShortestPath2.adjacent(10, 11,ctyInst.getStreets());

        assertEquals(value, false);
        assertEquals(value2, false);
        assertEquals(value3, true);

    }

    @org.junit.jupiter.api.Test
    void getStreetLength() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;

        int value=ShortestPath2.getStreetLength(9,11,ctyInst.getStreets());
        assertEquals(value, 2);
    }

    @org.junit.jupiter.api.Test
    void getStreetLength2() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;

        int value=ShortestPath2.getStreetLength(2,7,ctyInst.getStreets());
        assertEquals(value, 100000);
    }
    @org.junit.jupiter.api.Test
    void getLengthStreetNumber() {
    }

    @org.junit.jupiter.api.Test
    void getTime() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        int val=ShortestPath2.getTime(100, 20);
        assertEquals(val, 200/6);

    }

    @org.junit.jupiter.api.Test
    void areStreetsConnected() {
        Street s1= CityGenerator.city.getStreetByIndex(1);
        Street s2= CityGenerator.city.getStreetByIndex(2);
        boolean expected= ShortestPath2.areStreetsConnected(s1, s2);
        assertEquals(true, expected);
    }
    @org.junit.jupiter.api.Test
    void areStreetsConnected1() {
        Street s1= CityGenerator.city.getStreetByIndex(1);
        Street s2= CityGenerator.city.getStreetByIndex(2);
        boolean expected= ShortestPath2.areStreetsConnected(s2, s1);
        assertEquals(true, expected);
    }


    @org.junit.jupiter.api.Test
    void areIntersectionsConnected() {

        CityGenerator.generateCity();
        Intersection intersection1= CityGenerator.city.getIntersectionByIndex(1);
        Intersection intersection2=CityGenerator.city.getIntersectionByIndex(2);
        boolean val=ShortestPath2.areIntersectionsConnected(intersection1, intersection2);
        assertEquals(val,false);

    }

    @org.junit.jupiter.api.Test
    void setGetTuple1(){
        Tuple<Integer, Integer> a=new Tuple();
        a.setFirst(3);
        assertEquals(a.getFirst(),3);
    }
    @org.junit.jupiter.api.Test
    void setGetTuple2(){
        Tuple<Integer, Integer> a= new Tuple();
        a.setSecond(3);
        assertEquals(a.getSecond(),3);
    }

}
