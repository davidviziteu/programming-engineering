package AnimationLogic;

import AnimationLogic.Miscellaneous.CarFollower;
import AnimationLogic.Miscellaneous.ConsoleColors;
import CarGenerating.Car;
import CityGenerating.CityGenerator;

import java.util.ArrayList;

import static AnimationLogic.Miscellaneous.Utilities.*;


public class MasterThread {
    public static ArrayList<Thread> followCarThreadPool = new ArrayList<>();

    /**
     * urm 3 functii sunt pentru a putea face debug in consola.
     * se bazeaza pe src/AnimationLogic/Miscellaneous/CarFollower.java
     */


    /**
     * lanseaza un pool de thread (1 pt fiecare masina)
     */
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

    /**
     * lanseaza 1 thread pt o masina
     */
    public static void followCar(int carIdxToFollow, String consoleColor) {
        var carFollower = new CarFollower(carIdxToFollow, Integer.toString(carIdxToFollow), consoleColor);
        var th = new Thread(carFollower);
        followCarThreadPool.add(th);
        th.start();
    }


    /**
     * face join in cazul in care una din cele 2 metode de mai sus a fost apealata
     */
    public static void joinFollowCarThreads() throws InterruptedException {
        for (var th : followCarThreadPool)
            th.join();
    }

    /**
     * main ul este pt teste
     * @param args -
     */
    public static void main(String[] args) {
        CityGenerator.generateCity();
        computeShortestPathForAllCars();
        correctDistanceOfAllCars();
        setAllCarsSpeed(1); //- patratele pe secunda. pls nu pune ceva negativ nush ce se intampla
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
