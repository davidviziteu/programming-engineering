package ShortestPath;

import CityGenerating.CityGenerator;

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
        assertEquals(value, false);
    }

    @org.junit.jupiter.api.Test
    void adjacent1() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean value2=ShortestPath2.adjacent(3, 5,ctyInst.getStreets());
        assertEquals(value2, false);

    }
    @org.junit.jupiter.api.Test
    void adjacent2() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;

        boolean value3=ShortestPath2.adjacent(10, 11,ctyInst.getStreets());
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
    void getStreetLength1() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;

        int value=ShortestPath2.getStreetLength(3,11,ctyInst.getStreets());
        assertEquals(value, 4);
    }

    @org.junit.jupiter.api.Test
    void getStreetLength2() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;

        int value=ShortestPath2.getStreetLength(8,11,ctyInst.getStreets());
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
        assertEquals(val, 600/2);

    }
    @org.junit.jupiter.api.Test
    void areStreetsConnected(){
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areStreetsConnected(ctyInst.getStreets().get(1), ctyInst.getStreets().get(2));
        assertEquals(val, true);
    }
    @org.junit.jupiter.api.Test
    void areStreetsConnected2(){
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areStreetsConnected(ctyInst.getStreets().get(3), ctyInst.getStreets().get(9));
        assertEquals(val, false);
    }
    @org.junit.jupiter.api.Test
    void areStreetsConnected4(){
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areStreetsConnected(ctyInst.getStreets().get(8), ctyInst.getStreets().get(11));
        assertEquals(val, true);
    }

    @org.junit.jupiter.api.Test
    void areIntersectionsConnected() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areIntersectionsConnected(ctyInst.getIntersectionByIndex(1), ctyInst.getIntersectionByIndex(3));
        assertEquals(val, false);
    }

    @org.junit.jupiter.api.Test
    void areIntersectionsConnected2() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areIntersectionsConnected(ctyInst.getIntersectionByIndex(1), ctyInst.getIntersectionByIndex(9));
        assertEquals(val, true);
    }

    @org.junit.jupiter.api.Test
    void areIntersectionsConnected3() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        boolean val=ShortestPath2.areIntersectionsConnected(ctyInst.getIntersectionByIndex(9), ctyInst.getIntersectionByIndex(1));
        assertEquals(val, true);
    }
}
