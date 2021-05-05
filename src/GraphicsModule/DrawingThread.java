package GraphicsModule;

import AnimationLogic.CarAnimator;
import javafx.application.Application;
import javafx.stage.Stage;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;
//
public class DrawingThread extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }
//    public static Graphics ourCity;
//    @Override
//    public void run() {
//        while(existsACarOnStreets()) {
//            try{
//                CarAnimator.rwLock.readLock().lock();
//                ourCity.drawCars();
//                System.out.println("redrawed");
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                CarAnimator.rwLock.readLock().unlock();
//            }
//        }
//    }
}
