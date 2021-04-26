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
    void getLengthStreetNumber() {
    }

    @org.junit.jupiter.api.Test
    void getTime() {
        CityGenerator.generateCity();
        var ctyInst = CityGenerator.city;
        int val=ShortestPath2.getTime(100, 20);
        assertEquals(val, 200/6);

    }

}