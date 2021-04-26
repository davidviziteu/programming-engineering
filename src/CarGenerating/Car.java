package CarGenerating;

import java.util.List;
import java.util.Objects;
import java.util.Random;


import static CityGenerating.CityGenerator.city;

public class Car implements Comparable<Car> {
    static private int LastID = 0;
    private int ID;
    protected int currentPosition; //INDEX-ul unei strazi (pe poza strazile incep de la 1, in cod incep de la 0)
    protected int finalPosition; //ID-ul unei intersectii
    protected int speed;
    protected int distance; //offset ul fata de intersectie. dar daca se spawneaza direct in queue... atunci idk pare useless
    protected int direction; // 1 pt normal, -1 pt reversed
    protected List<Integer> shortestPath;
    protected int shortestPathTime;
    protected int shortestPathDistance;


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getShortestPathDistance() {
        return shortestPathDistance;
    }

    public void setShortestPathDistance(int shortestPathDistance) {
        this.shortestPathDistance = shortestPathDistance;
    }

    public List<Integer> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Integer> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public int getShortestPathTime() {
        return shortestPathTime;
    }

    public void setShortestPathTime(int shortestPathTime) {
        this.shortestPathTime = shortestPathTime;
    }


    // These numbers represent the IDs of the final streets.
    protected final int[] finalDestinationID = {0, 1, 2, 3, 4, 5, 6, 7};


    public int getCurrentPosition() {
        return currentPosition;
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

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
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

    public int getID() {
        return ID;
    }

    public Car() {
        ID=LastID++;
        this.speed = 0;
        int index;
        int maximumLengthOfStreet;
        Random rand = new Random();

        do{
            // There are only 12 streets.
            this.currentPosition = rand.nextInt(12) + 1;

            Integer directionOption = 1;
            if (CityGenerating.CityGenerator.city != null)directionOption= city.getStreetByIndex(this.currentPosition).getLane();
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
            if (CityGenerating.CityGenerator.city != null) {
                maximumLengthOfStreet = city.getStreetByIndex(currentPosition).getLength();
                this.distance = city.getStreetByIndex(this.currentPosition).getQueuePosition(this.direction);
            }

        }while(city.getStreetByIndex(this.currentPosition).getQueuePosition(this.direction)>city.getStreetByIndex(currentPosition).getLength());


        // We generate a final position until the final position is not equal to the initial one.
        do {
            index = rand.nextInt(8);
            this.finalPosition = finalDestinationID[index];
        } while (this.currentPosition == this.finalPosition);

    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + ID +
                ", currentPositionStreetIdx=" + currentPosition +
                ", finalPosition=" + finalPosition +
                ", speed=" + speed +
                ", distance=" + distance +
                ", direction=" + direction +
                ", shortestPath=" + shortestPath +
                ", shortestPathTime=" + shortestPathTime +
                ", shortestPathDistance=" + shortestPathDistance +
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
        return ID == car.ID;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ID);
        return result;
    }
}
