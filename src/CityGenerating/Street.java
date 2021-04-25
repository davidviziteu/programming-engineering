package CityGenerating;

import CarGenerating.Car;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static CityGenerating.CityGenerator.city;

public class Street {
    Queue<Pair<Integer, Car>> cars = new LinkedList<>();
    Queue<Pair<Integer, Car>> carsReversed = new LinkedList<>();
    private String name;
    private Integer intersectionSource;
    private Integer intersectionDestination;
    private Integer length; //capacitatea unei strazi (ambele sensuri au aceeasi capacitate)
    private Integer posX;
    private Integer posY;
    private Integer trafficLights;
    private final Integer trafficLightsReversed;
    private final Integer direction;

    public Queue<Pair<Integer, Car>> getCars() {
        return cars;
    }

    public Queue<Pair<Integer, Car>> getCarsReversed() {
        return carsReversed;
    }

    public Street(String nume, Integer idIntersectie1, Integer idIntersectie2, Integer lungime, Integer idSemafor, Integer idSemaforReversed, Integer pozX, Integer pozY, Integer direction) {
        name = nume;
        intersectionSource = idIntersectie1;
        intersectionDestination = idIntersectie2;
        length = lungime;
        trafficLights = idSemafor;
        trafficLightsReversed = idSemaforReversed;
        this.direction = direction;
        posX = pozX;
        posY = pozY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntersectionSource() {
        return intersectionSource;
    }

    public void setIntersectionSource(Integer intersection1) {
        this.intersectionSource = intersection1;
    }

    public Integer getIntersectionDestination() {
        return intersectionDestination;
    }

    public void setIntersectionDestination(Integer intersection2) {
        this.intersectionDestination = intersection2;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getTrafficLights() {
        return trafficLights;
    }

    public void setTrafficLights(Integer trafficLights) {
        this.trafficLights = trafficLights;
    }

    //methods
    public void addCar(Car car, Integer direction) {
        if (direction == 1) {
            if (cars.size() > length) {
                System.out.println("Street" + name + "is full!");
                return;
            }
            cars.add(new Pair<>(cars.size() + 1, car));
            car.setDistance(cars.size());
        } else {
            if (carsReversed.size() > length) {
                System.out.println("Street" + name + "is full!");
                return;
            }
            carsReversed.add(new Pair<>(carsReversed.size() + 1, car));
            car.setDistance(carsReversed.size());
        }
    }

    public Integer getQueuePosition(Integer direction) {
        if (direction == 1) {
            return cars.size() + 1;
        }
        return carsReversed.size() + 1;
    }

    public Queue<Pair<Integer, Car>> getCars(Integer direction) {
        if (direction == 1)
            return cars;
        return carsReversed;
    }

    public Integer getDirection() {
        return direction;
    }

    public void removeCar(Integer direction) {
        if (direction == 1) {
            if (cars.isEmpty()) {
                System.out.println("The street " + name + "is empty!");
                return;
            }
            cars.poll();
            Integer i = 1;
            for (Pair<Integer, Car> item : cars) {
                item.setKey(i);
                item.getValue().setDistance(i);
                i++;
            }
        } else {
            if (carsReversed.isEmpty()) {
                System.out.println("The street " + name + "is empty!");
                return;
            }
            carsReversed.poll();
            Integer i = 1;
            for (Pair<Integer, Car> item : carsReversed) {
                item.setKey(i);
                item.getValue().setDistance(i);
                i++;
            }
        }
    }

    public Car peekQueue(Integer direction) {
        if (direction == 1)
            return cars.peek().getValue();
        return carsReversed.peek().getValue();
    }

    public Integer getLane() {
        if (city.getIntersectionByIndex(this.getIntersectionSource()).isCanPark())
            return 1;
        if (city.getIntersectionByIndex(this.getIntersectionDestination()).isCanPark())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Street{" +
                "cars=" + cars +
                "carsReversed=" + carsReversed +
                ", name='" + name + '\'' +
                ", intersectionSource=" + intersectionSource +
                ", intersectionDestination=" + intersectionDestination +
                ", length=" + length +
                ", posX=" + posX +
                ", posY=" + posY +
                ", direction=" + (direction.equals(1) ? "horizontal" : "vertical") +
                ", trafficLights=" + trafficLights +
                ", trafficLightsReversed=" + trafficLightsReversed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(intersectionSource, street.intersectionSource) && Objects.equals(intersectionDestination, street.intersectionDestination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intersectionSource, intersectionDestination);
    }
}
