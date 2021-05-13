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
            if(args.length > 0 && args[0].equals("full app")) {
                var main = new MainAndreeaCi();
                main.showWindow();
            }
            MainAndreeaCi.incepe();
            carsControllerThread.join();
            carAnimatorThread.join();
            MasterThread.joinFollowCarThreads();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//https://www.programmersought.com/article/43064649273/