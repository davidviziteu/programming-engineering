package GraphicsModule;

import AnimationLogic.CarAnimator;
import javafx.application.Application;
import javafx.stage.Stage;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;
import static java.lang.Thread.sleep;

//
public class DrawingThread extends Thread {
//    @Override
//    public void start(Stage stage) throws Exception {
//        run();
//    }
    public static Graphics ourCity;

    public void run() {
        while(existsACarOnStreets()) {
            try{
                CarAnimator.rwLock.readLock().lock();
                ourCity.drawCars();
                //System.out.println("redrawed");
                //sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                CarAnimator.rwLock.readLock().unlock();
            }
        }
    }

}
