package CityGenerating;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLightsTest {
    TrafficLights instance = new TrafficLights(60, 1 ,0,60);

    @org.junit.jupiter.api.Test
    void getStareGreetTest() {
        assertEquals(instance.getStare(), TrafficLights.StareSemafor.Green);
    }
    @org.junit.jupiter.api.Test
    void getStareRedTest() {
        instance.setStare(TrafficLights.StareSemafor.Red);
        assertEquals(instance.getStare(), TrafficLights.StareSemafor.Red);
    }

    @org.junit.jupiter.api.Test
    void setStare() {
        instance.setStare(TrafficLights.StareSemafor.Red);
        assertEquals(instance.getStare(), TrafficLights.StareSemafor.Red);
    }

    @org.junit.jupiter.api.Test
    void getTimeMax2Test() {
        assertEquals(60,instance.getTimeMax2());
    }

    @org.junit.jupiter.api.Test
    void setTimeMax2() {
        instance.setTimeMax2(50);
        assertEquals(50,instance.getTimeMax2());
    }
    @org.junit.jupiter.api.Test
    void setTimeMax2NegativeTest() {
        instance.setTimeMax2(-2);
        assertEquals(60,instance.getTimeMax2());
    }

    @org.junit.jupiter.api.Test
    void getTimeMax() {
        assertEquals(60,instance.getTimeMax());
    }

    @org.junit.jupiter.api.Test
    void setTimeMaxTest() {
        instance.setTimeMax(20);
        assertEquals(20,instance.getTimeMax());
    }
    @org.junit.jupiter.api.Test
    void setTimeMaxMegativeTest() {
        instance.setTimeMax(-2);
        assertEquals(60,instance.getTimeMax());
    }

    @org.junit.jupiter.api.Test
    void getPoz() {
        assertEquals(0,instance.getPoz());
    }

    @org.junit.jupiter.api.Test
    void setPoz() {
        instance.setPoz(1);
        assertEquals(1,instance.getPoz());
    }

    @org.junit.jupiter.api.Test
    void getColor() {
        assertEquals(instance.getColor(), TrafficLights.StareSemafor.Green);
    }

    @org.junit.jupiter.api.Test
    void setColor() {
        instance.setColor(TrafficLights.StareSemafor.Red);
        assertEquals(instance.getColor(), TrafficLights.StareSemafor.Red);
    }

    @org.junit.jupiter.api.Test
    void getTimer() {
        assertEquals(60,instance.getTimer());
    }

    @org.junit.jupiter.api.Test
    void setTimer() {
        instance.setTimer(20);
        assertEquals(20,instance.getTimer());
    }

    @org.junit.jupiter.api.Test
    void setMaxTime() {
        instance.setMaxTime(30);
        assertEquals(30,instance.getMaxTime());
    }
    @org.junit.jupiter.api.Test
    void setMaxTimeNegative() {
        instance.setMaxTime(-2);
        assertEquals(60,instance.getMaxTime());
    }

    @org.junit.jupiter.api.Test
    void getMaxTime() {
        assertEquals(60,instance.getMaxTime());
    }
}