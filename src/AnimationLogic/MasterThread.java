package AnimationLogic;

import CityGenerating.CityGenerator;
import static AnimationLogic.Utilities.*;


public class MasterThread {
    public static void main(String[] args) {
        CityGenerator.generateCity();
        computeShortestPathForAllCars();
        correctDistanceOfAllCars();
        var carsControllerInstance = new CarController();
        var semaphoreControllerInstance = new SemaphoreController();
        var threadController1 = new Thread(carsControllerInstance);
        var threadController2 = new Thread(semaphoreControllerInstance);
        threadController1.start();
        threadController2.start();
        try {
            threadController1.join();
            threadController2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
