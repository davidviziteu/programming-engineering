package AnimationLogic;

import CityGenerating.CityGenerator;
import CityGenerating.TrafficLights;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;


public class SemaphoreController extends Thread  {

    SemaphoreController(){}

    @Override
    public void run() {

        while (existsACarOnStreets()){

            for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++) {
                TrafficLights trafficLights=CityGenerator.city.getTLightsById(i);
                if (trafficLights.getTimer() > 0) {
                    trafficLights.setTimer(trafficLights.getTimer() - 1);
                } else {
                    trafficLights.setTimer(trafficLights.getMaxTime());
                    trafficLights.setColor(trafficLights.getStare().next());
                    trafficLights.setPoz((trafficLights.getPoz() + 1) % 4);
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Dupa o secunda: ");
            for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++){
                System.out.println(CityGenerator.city.getTLightsById(i));
            }
        }
        System.out.println("semaphore controller run");
    }
}
