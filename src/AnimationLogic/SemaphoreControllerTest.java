package AnimationLogic;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.TrafficLights;

import static org.junit.jupiter.api.Assertions.*;


/**
 * clasa de test pt semaphore controller
 */
class SemaphoreControllerTest {
    @org.junit.jupiter.api.Test
    void changeTest(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=cityInst.getTLightsById(0);
        if(t1.getTimer()>0)
        {    ok=1; val=t1.getTimer();}
        SemaphoreController.change(t1);
        System.out.println(ok);
        if(ok==1)
            assertEquals(t1.getTimer(), val-1);
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Green) || t1.getStare().equals(TrafficLights.StareSemafor.Red))
            assertEquals(t1.getTimer(),60);
        else
            assertEquals(t1.getTimer(), 3);
    }
    @org.junit.jupiter.api.Test
    void changeTest1(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=cityInst.getTLightsById(5);
        if(t1.getTimer()>0)
        {    ok=1; val=t1.getTimer();}
        for(int i=0; i<60; i++)
            SemaphoreController.change(t1);
        if(ok==1)
            assertEquals(t1.getTimer(), 0);
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Green) || t1.getStare().equals(TrafficLights.StareSemafor.Red))
            assertEquals(t1.getTimer(),60);
        else
            assertEquals(t1.getTimer(), 3);
    }
    @org.junit.jupiter.api.Test
    void changeTest3(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=new TrafficLights(60, 2, 9, 3);
        t1.setTimer(0);
        t1.setStare(TrafficLights.StareSemafor.YellowGreen);
        if(t1.getTimer()>0)
        {    ok=1; val=t1.getTimer();}
        SemaphoreController.change(t1);
        System.out.println(ok);
        if(ok==1)
            assertEquals(t1.getTimer(), val-1);
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Green) || t1.getStare().equals(TrafficLights.StareSemafor.Red))
            assertEquals(t1.getTimer(),60);
        else
            assertEquals(t1.getTimer(), 3);
    }

    @org.junit.jupiter.api.Test
    void changeColor(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=cityInst.getTLightsById(0);
        TrafficLights.StareSemafor expected;

        if(t1.getStare().equals(TrafficLights.StareSemafor.Green))
            expected= TrafficLights.StareSemafor.YellowGreen;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Red))
            expected= TrafficLights.StareSemafor.YellowRed;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.YellowRed))
            expected= TrafficLights.StareSemafor.Green;
        else
            expected= TrafficLights.StareSemafor.Red;

        int index=t1.getTimer();
        for(int i=0; i<=index; i++)
            SemaphoreController.change(t1);
        assertEquals(expected, t1.getStare());
    }
    @org.junit.jupiter.api.Test
    void changeColor2(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=cityInst.getTLightsById(3);
        TrafficLights.StareSemafor expected;

        if(t1.getStare().equals(TrafficLights.StareSemafor.Green))
            expected= TrafficLights.StareSemafor.YellowGreen;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Red))
            expected= TrafficLights.StareSemafor.YellowRed;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.YellowRed))
            expected= TrafficLights.StareSemafor.Green;
        else
            expected= TrafficLights.StareSemafor.Red;

        int index=t1.getTimer();
        for(int i=0; i<=index; i++)
            SemaphoreController.change(t1);
        assertEquals(expected, t1.getStare());
    }
    @org.junit.jupiter.api.Test
    void changeColor3(){
        CityGenerator.generateCity();
        int ok=0, val=0;
        var cityInst=CityGenerator.city;
        TrafficLights t1=cityInst.getTLightsById(cityInst.getTrafficLights().size()-1);
        TrafficLights.StareSemafor expected;

        if(t1.getStare().equals(TrafficLights.StareSemafor.Green))
            expected= TrafficLights.StareSemafor.YellowGreen;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.Red))
            expected= TrafficLights.StareSemafor.YellowRed;
        else
        if(t1.getStare().equals(TrafficLights.StareSemafor.YellowRed))
            expected= TrafficLights.StareSemafor.Green;
        else
            expected= TrafficLights.StareSemafor.Red;

        int index=t1.getTimer();
        for(int i=0; i<=index; i++)
            SemaphoreController.change(t1);
        assertEquals(expected, t1.getStare());
    }
    @org.junit.jupiter.api.Test
    void changeAll(){
        CityGenerator.generateCity();
        SemaphoreController.changeAll();
        assertEquals(CityGenerator.city.getTrafficLights().get(5).getTimer(), 59);
    }
    @org.junit.jupiter.api.Test
    void changeAll2(){
        CityGenerator.generateCity();
        SemaphoreController.changeAll();
        SemaphoreController.changeAll();
        SemaphoreController.changeAll();

        assertEquals(CityGenerator.city.getTrafficLights().get(3).getTimer(), 57);
    }
    @org.junit.jupiter.api.Test
    void isRunning(){
        SemaphoreController s= SemaphoreController.getInstance();
        assertEquals(SemaphoreController.isRunning(),false);
    }

}