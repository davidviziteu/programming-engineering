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
        var CarFollower2 = new CarFollower(1, "1");
        var Follower2Thread = new Thread(CarFollower2);
        carsControllerThread.start();
        semaphoreControllerThread.start();
        carAnimatorThread.start();
        Follower1Thread.start();
        Follower2Thread.start();
        try {
            carsControllerThread.join();
            semaphoreControllerThread.join();
            carAnimatorThread.join();
            Follower1Thread.join();
            Follower2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
