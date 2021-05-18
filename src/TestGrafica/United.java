package TestGrafica;

import AnimationLogic.CarAnimator;
import AnimationLogic.CarController;
import AnimationLogic.MasterThread;
import AnimationLogic.Miscellaneous.Utilities;
import AnimationLogic.SemaphoreController;
import CityGenerating.CityGenerator;
import UI.UIController;

import java.util.concurrent.Semaphore;

public class United {


    public static void main(String[] args) {

        CityGenerator.generateCity(args[1], Integer.parseInt(args[2].replace("Strada", ""))-1, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        Utilities.correctDistanceOfAllCars();
        Utilities.computeShortestPathForAllCars();
        Utilities.setAllCarsSpeed(1);

        var carsControllerInstance = CarController.getInstance();
        var carsControllerThread = new Thread(carsControllerInstance);

        var carAnimatorInstance = CarAnimator.getInstance();
        var carAnimatorThread = new Thread(carAnimatorInstance);

        var semaphoreController = SemaphoreController.getInstance();
        var semaphoreControllerThread = new Thread(semaphoreController);

        semaphoreControllerThread.start();
        carsControllerThread.start();
        carAnimatorThread.start();
        MasterThread.followAllCars();

        try {
            if (args.length > 0 && args[0].equals("full app")) {
                var secondWindow = new MainAndreeaCi();
                secondWindow.showWindow();
            }
            else
                MainAndreeaCi.incepe();
            // NU DA JOIN LA THREADS, SE BLOCHEAZA cand vreau sa fac a2a fereastra
            // LASA GARBAGE COLLECTOR UL SA SE OCUPE DE ELE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//https://www.programmersought.com/article/43064649273/