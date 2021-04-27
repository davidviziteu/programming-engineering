package CityGenerating;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {
    @Test
    void testSetNamePositive() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try3");
        var name = intersection.getName();
        assertEquals(name,"try3");
    }

    @Test
    void testSetNameNegative() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try3");
        intersection.setName("");
        var name = intersection.getName();
        assertEquals(name,"try3");
    }

    @Test
    void getName() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try2");
        assertEquals(intersection.getName(), "try2");
    }

    @Test
    void getStreets() {
        Intersection intersection = new Intersection("try", null);
        intersection.setName("try2");
        assertEquals(intersection.getName(), "try2");
    }

    @Test
    void getPosX() {
        Intersection intersection = new Intersection("try",null, 2,1,false);
        var poz = intersection.getPosX();
        assertEquals(poz,2);
    }

    @Test
    void setPosX() {
        Intersection intersection = new Intersection("try", null);
        intersection.setPosX(5);
        var poz = intersection.getPosX();
        assertEquals(poz,5);
    }

    @Test
    void getPosY() {
        Intersection intersection = new Intersection("try",null, 2,1,false);
        var poz = intersection.getPosY();
        assertEquals(poz,1);
    }

    @Test
    void setPosY() {
        Intersection intersection = new Intersection("try", null);
        intersection.setPosY(5);
        var poz = intersection.getPosY();
        assertEquals(poz,5);
    }

    @Test
    void isCanPark() {
        Intersection intersection = new Intersection("try", null, false);
        var canPark = intersection.isCanPark();
        assertEquals(canPark,false);
    }
}