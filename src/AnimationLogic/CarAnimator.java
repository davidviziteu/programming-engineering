package AnimationLogic;

import CityGenerating.Street;

import java.util.concurrent.locks.ReentrantLock;

/**
 * face masinile sa mearga patratel cu patratel pe harta
 */
public class CarAnimator extends Thread {
    static public boolean isRunning = false;
    static public ReentrantLock lock = new ReentrantLock();


    static public void updateStreetQueues(Street st){

    }
    CarAnimator() {}
    @Override
    public void run() {
        synchronized (this) {
            //sper ca merge codul asta
            if (isRunning) {
                System.err.println("deja ai pornit un thread care animeaza masinile pe harta, nu mai poti porni inca unul");
                return;
            } else isRunning = true;
        }

    }
}
