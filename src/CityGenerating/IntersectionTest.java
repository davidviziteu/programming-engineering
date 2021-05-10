package CityGenerating;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {
    @org.junit.jupiter.api.Test
    void testSetNamePositive() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try3");
        var name = intersection.getName();
        assertEquals(name, "try3");
    }

    @org.junit.jupiter.api.Test
    void testSetNameNegative() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try3");
        intersection.setName("");
        var name = intersection.getName();
        assertEquals(name, "try3");
    }

    @org.junit.jupiter.api.Test
    void testSetStreetsNegative() {
        Street s = new Street("a", 1, 1, 1, 1, 1, 1, 1, 1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Intersection intersection = new Intersection("try", list);
        intersection.setStreets(null);
        var streets = intersection.getStreets();
        assertEquals(streets, list);
    }

    @org.junit.jupiter.api.Test
    void testSetStreetsPositive() {
        Street s = new Street("a", 1, 1, 1, 1, 1, 1, 1, 1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Intersection intersection = new Intersection("try", null);
        intersection.setStreets(list);
        var streets = intersection.getStreets();
        assertEquals(streets, list);
    }

    @org.junit.jupiter.api.Test
    void testCanParkConstructor() {
        Intersection intersection = new Intersection("try", null, true);
        var park = intersection.isCanPark();
        assertEquals(park, true);
    }

    @org.junit.jupiter.api.Test
    void testCanParkConstructorPoz() {
        Intersection intersection = new Intersection("try", null, 1, 2);
        var name = intersection.getName();
        var strazi = intersection.getStreets();
        var x = intersection.getPosX();
        var y = intersection.getPosY();
        assertEquals(name, "try");
        assertEquals(strazi, null);
        assertEquals(x, 1);
        assertEquals(y, 2);
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try2");
        assertEquals(intersection.getName(), "try2");
    }

    @org.junit.jupiter.api.Test
    void testGetStreets() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try2");
        assertEquals(intersection.getName(), "try2");
    }

    @org.junit.jupiter.api.Test
    void testSetStreets() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try2");
        assertEquals(intersection.getName(), "try2");
    }

    @org.junit.jupiter.api.Test
    void testGetPosX() {
        Intersection intersection = new Intersection("try", null, 2, 1, false);
        var poz = intersection.getPosX();
        assertEquals(poz, 2);
    }

    @org.junit.jupiter.api.Test
    void testSetPosX() {
        Intersection intersection = new Intersection("try", null);
        intersection.setPosX(5);
        var poz = intersection.getPosX();
        assertEquals(poz, 5);
    }

    @org.junit.jupiter.api.Test
    void testGetPosY() {
        Intersection intersection = new Intersection("try", null, 2, 1, false);
        var poz = intersection.getPosY();
        assertEquals(poz, 1);
    }

    @org.junit.jupiter.api.Test
    void testSetPosY() {
        Intersection intersection = new Intersection("try", null);
        intersection.setPosY(5);
        var poz = intersection.getPosY();
        assertEquals(poz, 5);
    }

    @org.junit.jupiter.api.Test
    void testIsCanPark() {
        Intersection intersection = new Intersection("try", null, false);
        var canPark = intersection.isCanPark();
        assertEquals(canPark, false);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Intersection intersection = new Intersection("try", null, false);
        System.out.println(intersection.toString());
        var string = intersection.toString();
        assertEquals(string, "Intersection{name='try', streets=null, posX=null, posY=null, canPark=false}");
    }
}