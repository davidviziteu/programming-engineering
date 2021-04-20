package CarGenerating;

import CityGenerating.City;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static CityGenerating.CityGenerator.city;
import static CityGenerating.CityGenerator.generateCity;

public class Car implements Comparable<Car> {
    protected int initialPosition;
    protected int finalPosition;
    protected int speed;
    protected int distance;
    protected int direction;

    // These numbers represent the IDs of the final streets.
    protected final int[] finalDestinationID = {0, 1, 2, 3, 4, 5, 6, 7};


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
        if (speed >= 0 && speed <= 150)
            this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public Car() {
        this.speed = 0;
        Random rand = new Random();
        // There are only 23 streets.
        this.initialPosition = rand.nextInt(12);
        int index;
        int maximumLengthOfStreet;

        // We generate a final position until the final position is not equal to the initial one.
        do {
            index = rand.nextInt(8);
            this.finalPosition = finalDestinationID[index];
        } while (this.initialPosition == this.finalPosition);
        Integer directionOption = city.getStreetByIndex(this.initialPosition).getLane();
        switch (directionOption) {
            case 1:
                this.direction = 1;
                break;
            case -1:
                this.direction = -1;
                break;
            default:
                this.direction = Math.random() > 0.5 ? 1 : -1;
                break;
        }

        // A street has the maximum length 5.
        if (CityGenerating.CityGenerator.city != null) {
            maximumLengthOfStreet = city.getStreetByIndex(initialPosition).getLength();
            this.distance = city.getStreetByIndex(this.initialPosition).getQueuePosition(this.direction);
        }
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

    @Override
    public int compareTo(Car o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return initialPosition == car.initialPosition && finalPosition == car.finalPosition && speed == car.speed && distance == car.distance && Arrays.equals(finalDestinationID, car.finalDestinationID);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(initialPosition, finalPosition, speed, distance);
        result = 31 * result + Arrays.hashCode(finalDestinationID);
        return result;
    }
}
