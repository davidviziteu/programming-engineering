package AnimationLogic.Miscellaneous;

import AnimationLogic.CarAnimator;
import CarGenerating.Car;
import CityGenerating.CityGenerator;

import java.util.ArrayList;

/**
 * for debugging on terminal
 */

public class CarFollower extends Thread {
    public static ArrayList<CarFollower> pool = new ArrayList<>();
    private int carIdxToFollow;
    private String name;
    private Car previousState; //nu stiu sa folosesc wait notify asa ca fac manevra
    private boolean dontRun;
    private int sleepSeconds;

    public CarFollower(int carIdxToFollow, String plsGiveName) {
        this.carIdxToFollow = carIdxToFollow;
        name = plsGiveName;
        name += " - ";
        sleepSeconds = 0;
        try {
            previousState = CityGenerator.city.getCars().get(carIdxToFollow);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ConsoleColors.RED_BOLD + "Car with index " + carIdxToFollow + " doesnt exist" + ConsoleColors.RESET);
            dontRun = true;
            return;
        }
        pool.add(this);
    }

    public void setRefreshRate(int sleepSeconds) {
        if (sleepSeconds < 50)
            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT +
                    "Ba esti nebun? "
                    + sleepSeconds + "ms?\nNu te las"
                    + ConsoleColors.RESET
            );
        else this.sleepSeconds = sleepSeconds;
    }

    @Override
    public void run() {
        System.out.println(
                ConsoleColors.GREEN + "following " + this.name + previousState.toString() + ConsoleColors.RESET
        );
        while (true) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (CarAnimator.isRunning()) {
                CarAnimator.rwLock.readLock().lock();
                try {
                    var newState = CityGenerator.city.getCars().get(carIdxToFollow);
                    System.out.println(
                            ConsoleColors.GREEN + this.name + newState.toString() + ConsoleColors.RESET
                    );
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ConsoleColors.GREEN +
                            "Car " + this.name + " has exited the map.\n"
                            + ConsoleColors.RESET
                            + "Last known: " + this.previousState.toString()
                    );
                    return;
                } finally {
                    CarAnimator.rwLock.readLock().unlock();
                }
            } else {
                System.out.println("Non animated module not implemented yet");
                return;
            }
        }
    }

}
