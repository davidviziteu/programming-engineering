package AnimationLogic;

import AnimationLogic.Miscellaneous.CarFollower;
import CityGenerating.CityGenerator;

import static AnimationLogic.Miscellaneous.Utilities.*;


public class MasterThread {
    public static void main(String[] args) {
        CityGenerator.generateCity();
        computeShortestPathForAllCars();
        correctDistanceOfAllCars();
        setAllCarsSpeed(1); //1 patratel pe secunda. pls nu pune ceva negativ nush ce se intampla
        var carsControllerInstance = new CarController();
        var carsControllerThread = new Thread(carsControllerInstance);

        var carAnimatorInstance = new CarAnimator();
        var carAnimatorThread = new Thread(carAnimatorInstance);

        var semaphoreControllerInstance = new SemaphoreController();
        var semaphoreControllerThread = new Thread(semaphoreControllerInstance);

        var CarFollower1 = new CarFollower(0, "0");
        var Follower1Thread = new Thread(CarFollower1);

        carsControllerThread.start();
        semaphoreControllerThread.start();
        carAnimatorThread.start();
        Follower1Thread.start();
        try {
            carsControllerThread.join();
            semaphoreControllerThread.join();
            carAnimatorThread.join();
            Follower1Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
