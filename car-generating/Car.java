package CarGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Car {
    protected int initialPosition;
    protected int finalPosition;
    protected int speed;
    protected int distance;

    //getters and setters for debugging

    public int getInitialPosition() {
        return initialPosition;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void setFinalPosition(int finalPosition) {
        this.finalPosition = finalPosition;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //constructors

    public Car() {
        //todo stuff
        this.speed=0;
        Random rand = new Random();
        this.initialPosition=rand.nextInt();//in paranteza trebuie sa vina limita maxima de strazi
        this.finalPosition=rand.nextInt();//in viitor vom verifica ca initial si final sa nu fie aceeasi strada
        this.distance=rand.nextInt();//in paranteza vine lungimea strazii initiale ca limita maxima
    }

}
