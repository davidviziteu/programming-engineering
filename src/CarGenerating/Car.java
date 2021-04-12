package CarGenerating;

import java.util.Arrays;
import java.util.Random;

import static CityGenerating.CityGenerator.city;

public class Car implements Comparable<Car> {
    protected int initialPosition;
    protected int finalPosition;
    protected int speed;
    protected int distance;

    // These numbers represent the IDs of the final streets.
    protected final int[] finalDestinationID = {1,3,7,9,15,19,21,23};


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

    @Override
    public String toString() {
        return "Car{" +
                "initialPosition=" + initialPosition +
                ", finalPosition=" + finalPosition +
                ", speed=" + speed +
                ", distance=" + distance +
                ", finalDestinationID=" + Arrays.toString(finalDestinationID) +
                '}';
    }

    public Car() {
        this.speed=0;
        Random rand = new Random();
        // There are only 23 streets.
        this.initialPosition=rand.nextInt(24);

        int index;
        int maximumLengthOfStreet;

        // We generate a final position until the final position is not equal to the initial one.
        do {
            index = rand.nextInt(8);
            this.finalPosition = finalDestinationID[index];
        }while(this.initialPosition == this.finalPosition);

        // A street has the maximum length 5.
        maximumLengthOfStreet = city.getStreetByIndex(initialPosition).getLength();
        this.distance=rand.nextInt(maximumLengthOfStreet);
        this.distance=rand.nextInt(city.getStreetByIndex(5).getLength());
    }

    @Override
    public int compareTo(Car o) {
        return 0;
    }
}
