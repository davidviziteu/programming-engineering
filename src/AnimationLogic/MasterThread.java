package AnimationLogic;

import AnimationLogic.Miscellaneous.CarFollower;
import AnimationLogic.Miscellaneous.ConsoleColors;
import CarGenerating.Car;
import CityGenerating.CityGenerator;

import java.util.ArrayList;

import static AnimationLogic.Miscellaneous.Utilities.*;


public class MasterThread {
    public static ArrayList<Thread> followCarThreadPool = new ArrayList<>();

    public static void followAllCars() {
        ArrayList<Car> cars = CityGenerator.city.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            var carFollower = new CarFollower(i, Integer.toString(i), ConsoleColors.GREEN);
            var th = new Thread(carFollower);
            followCarThreadPool.add(th);
            th.start();
        }
    }

    public static void followCar(int carIdxToFollow, String consoleColor) {
        var carFollower = new CarFollower(carIdxToFollow, Integer.toString(carIdxToFollow), consoleColor);
        var th = new Thread(carFollower);
        followCarThreadPool.add(th);
        th.start();
    }

    public static void joinFollowCarThreads() throws InterruptedException {
        for (var th : followCarThreadPool)
            th.join();
    }

    public static void main(String[] args) {
        CityGenerator.generateCity();
        computeShortestPathForAllCars();
        correctDistanceOfAllCars();
        setAllCarsSpeed(2); //- patratele pe secunda. pls nu pune ceva negativ nush ce se intampla
        var carsControllerInstance = CarController.getInstance();
        var carsControllerThread = new Thread(carsControllerInstance);

        var carAnimatorInstance = CarAnimator.getInstance();
        var carAnimatorThread = new Thread(carAnimatorInstance);

        var semaphoreControllerInstance = SemaphoreController.getInstance();
        var semaphoreControllerThread = new Thread(semaphoreControllerInstance);
        //asta
//        followAllCars();
        //sau
        followCar(0, ConsoleColors.YELLOW);
//        followCar(1, ConsoleColors.GREEN);
        carsControllerThread.start();
        semaphoreControllerThread.start();
        carAnimatorThread.start();
        try {
            carsControllerThread.join();
            semaphoreControllerThread.join();
            carAnimatorThread.join();
            joinFollowCarThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
