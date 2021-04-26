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
    private String consoleColor;
    public CarFollower(int carIdxToFollow, String plsGiveName, String consoleColor) {
        this.carIdxToFollow = carIdxToFollow;
        this.consoleColor = consoleColor;
        name = plsGiveName;
        name += " - ";
        sleepSeconds = 0;
        try {
            previousState = CityGenerator.city.getCars().get(carIdxToFollow);
            System.out.println(
                    consoleColor + this.name + previousState.toString() + ConsoleColors.RESET
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ConsoleColors.RED_BOLD + "Car with index " + carIdxToFollow + " doesnt exist" + ConsoleColors.RESET);
            dontRun = true;
            return;
        }
        pool.add(this);
    }


    @Override
    public void run() {
        while (Utilities.existsACarOnStreets()) {
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
                    if(newState.getDistance() == -1) break;
                    System.out.println(
                            consoleColor + this.name + newState.toString() + ConsoleColors.RESET
                    );
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(consoleColor +
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
        System.out.println(
                consoleColor + this.name + previousState.toString() + ConsoleColors.GREEN_UNDERLINED + " Has arrived at destination" + ConsoleColors.RESET
        );
    }

}
