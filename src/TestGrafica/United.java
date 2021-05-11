package TestGrafica;

import AnimationLogic.CarAnimator;
import AnimationLogic.CarController;
import AnimationLogic.MasterThread;
import AnimationLogic.Miscellaneous.Utilities;
import CityGenerating.CityGenerator;

public class United {


    public static void main(String[] args) {


        CityGenerator.generateCity();
        Utilities.correctDistanceOfAllCars();
        Utilities.computeShortestPathForAllCars();
        Utilities.setAllCarsSpeed(1);

        var carsControllerInstance = CarController.getInstance();
        var carsControllerThread = new Thread(carsControllerInstance);

        var carAnimatorInstance = CarAnimator.getInstance();
        var carAnimatorThread = new Thread(carAnimatorInstance);



        carsControllerThread.start();
        carAnimatorThread.start();
        MasterThread.followAllCars();

        try {
            Main.incepe();

            carsControllerThread.join();
            carAnimatorThread.join();
            MasterThread.joinFollowCarThreads();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}