package AnimationLogic;

import AnimationLogic.Miscellaneous.CarFollower;
import AnimationLogic.Miscellaneous.ConsoleColors;
import AnimationLogic.Miscellaneous.Utilities;
import CarGenerating.Car;
import CityGenerating.CityGenerator;
import CityGenerating.Pair;
import CityGenerating.TrafficLights;

import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static AnimationLogic.Miscellaneous.Utilities.existsACarOnStreets;
import static CityGenerating.CityGenerator.city;

/**
 * face masinile sa mearga patratel cu patratel pe harta in functie de viteza
 * singleton, 1 thread
 *
 * face notify pentru pool ul de instante a clasei/thread urilor CarFollower
 */
public class CarAnimator extends Thread {
    public static boolean isRunning() {
        return running;
    }

    static private boolean running = false;
    static public ReadWriteLock rwLock = new ReentrantReadWriteLock();
    static public int carToFollow = -1; //for debugging
    static private int carSpeed;
    static private CarAnimator instance = null;
    /**
     * actualizeaza pozitia masinilor dintr o coada de pe o strada.
     *
     * daca pozitia din instanta unei masini == 0, seteaza flag ul ReachedIntersection din instanta unei Masini pe true
     *
     * @param q
     */
    static public void updateStreetQueue(Queue<Pair<Integer, Car>> q) {
        rwLock.writeLock().lock();
        try {
            q.stream().forEachOrdered(pair -> {
                var currentOffset = pair.getValue().getDistance();
                if (currentOffset > -1) //daca e 0 sau -1 inseamna ca tre mutata sau ca e scoasa
                    if (currentOffset != Utilities.getIndexOfCarInQueue(q, pair.getValue()))
                        pair.getValue().setDistance(--currentOffset);
                    else if (currentOffset == 0)
//                        pair.getValue().setDistance(--currentOffset);
                        pair.getValue().setReachedIntersection(true);


            });
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    static
    public CarAnimator getInstance(){
        if(instance == null)
            instance = new CarAnimator();
        return instance;
    }
    private CarAnimator() {
        carSpeed = city.getCars().get(0).getSpeed();
        if (carSpeed == 0)
            System.out.println(ConsoleColors.RED_BOLD + "Car speed set to 0 => they are moving as fast as possible" + ConsoleColors.RESET);
    }

    /**
     * itereaza prin strazi si apelaza (in functie de viteza masinilor) updateStreetQueue() pentru fiecare queue din strada
     */
    @Override
    public void run() {
        synchronized (this) {
            if (running) {
                System.err.println("deja ai pornit un thread care animeaza masinile pe harta, nu mai poti porni inca unul");
                return;
            } else running = true;
        }
        try {
            System.out.println("started animator thread");
            while (existsACarOnStreets()) {
                for (var st : city.getStreets()) {
                    updateStreetQueue(st.getCars());
                    updateStreetQueue(st.getCarsReversed());
                }
                if (CarFollower.pool.size() > 0)
                    for(var th : CarFollower.pool)
                        synchronized (th) {
                            th.notify();
                        }
                sleep(1000 / carSpeed);
            }
            sleep(2); // for car follower
            if (CarFollower.pool.size() > 0) {
                for(var th : CarFollower.pool)
                    synchronized (th) {
                        th.notify();
                    }

            }
            System.out.println("animator thread ended");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
