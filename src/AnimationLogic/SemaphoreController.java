package AnimationLogic;

import CityGenerating.CityGenerator;
import CityGenerating.TrafficLights;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;


public class SemaphoreController extends Thread  {

    SemaphoreController(){}

    public static void change(TrafficLights trafficLights){
        if (trafficLights.getTimer() > 0) {
            trafficLights.setTimer(trafficLights.getTimer() - 1);
        } else {

            if(trafficLights.getStare().equals(TrafficLights.StareSemafor.Green) || trafficLights.getStare().equals(TrafficLights.StareSemafor.Red))
            {
                trafficLights.setTimer(trafficLights.getTimeMax2());
            }
            else
                trafficLights.setTimer(trafficLights.getMaxTime());

            trafficLights.setColor(trafficLights.getStare().next());
        }
    }
    static void changeAll(){
        for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++) {
            TrafficLights trafficLights=CityGenerator.city.getTLightsById(i);
            change(trafficLights);
        }
    }
   /* void printAll(){
        System.out.println("Dupa o secunda: ");
        for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++){
            System.out.println(CityGenerator.city.getTLightsById(i));
        }
    }

    */
    @Override
    public void run() {

        while (existsACarOnStreets()){
            changeAll();
            try {
                sleep(100);
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++){
                System.out.println(CityGenerator.city.getTLightsById(i));
            }
        }
    }
}
