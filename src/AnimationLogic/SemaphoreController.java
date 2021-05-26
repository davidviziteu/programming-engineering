package AnimationLogic;

import CityGenerating.CityGenerator;
import CityGenerating.TrafficLights;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;

/**
 * ma folosesc de api ul dat de echipa de la strazi ca sa controlez timpii lor si sa
 * le schimb culorile
 *
 * singleton, 1 thread
 */
public class SemaphoreController extends Thread  {

    static
    private SemaphoreController instance = null;

    static
    public boolean isRunning() {
        return running;
    }
    static
    private boolean running = false;

    static synchronized
    public SemaphoreController getInstance(){
        if(instance == null)
            instance = new SemaphoreController();
        return instance;
    }

    private SemaphoreController(){}

    /**
     * animeaza un semafor
     * @param trafficLights
     */
    public static void change(TrafficLights trafficLights){
        if (trafficLights.getTimer() > 0) {
            trafficLights.setTimer(trafficLights.getTimer() - 1);
        } else {
            trafficLights.setColor(trafficLights.getStare().next());
            if(trafficLights.getStare().equals(TrafficLights.StareSemafor.Green)){
                trafficLights.setTimer(5);
            }
            if(trafficLights.getStare().equals(TrafficLights.StareSemafor.Red))
            {
                trafficLights.setTimer(5);
            }
            else
                trafficLights.setTimer(1);

        }
    }

    /**
     * itereaza prin toate semafoarele si apelaza functia de mai sus
     */
    static void changeAll(){
        for(int i=1; i< CityGenerator.city.getTrafficLights().size(); i++) {
            TrafficLights trafficLights=CityGenerator.city.getTLightsById(i);
            change(trafficLights);
        }
    }

    /**
     * apelaza functia de mai sus o singura data pe secunda
     */
    @Override
    public void run() {
        if(!running) {
            running = true;
            System.out.println("Semaphore controller started");
        }
        else {
            System.err.println("Semaphore controller already running");
            return;
        }

        while (existsACarOnStreets()){
            changeAll();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            //for(int i=0; i< CityGenerator.city.getTrafficLights().size(); i++){
            //    System.out.println(CityGenerator.city.getTLightsById(i));
           // }
        }
    }
}
