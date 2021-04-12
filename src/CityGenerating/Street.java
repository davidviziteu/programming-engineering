package CityGenerating;

import java.util.Queue;
import CarGenerating.Car;
import java.util.LinkedList;

public class Street {
    Queue < Pair< Integer, Car> > cars= new LinkedList<>();
    private String name;
    private Integer intersectionSource;
    private Integer intersectionDestination;
    private Integer length;
    private Integer posX;
    private Integer posY;
    private Integer trafficLights;

    public Street(String nume, Integer idIntersectie1, Integer idIntersectie2, Integer lungime, Integer idSemafor){
        name=nume;
        intersectionSource=idIntersectie1;
        intersectionDestination=idIntersectie2;
        length=lungime;
        trafficLights=idSemafor;
    }

    Street(String nume, Integer idIntersectie1, Integer idIntersectie2, Integer lungime, Integer idSemafor, Integer pozX, Integer pozY){
        name=nume;
        intersectionSource=idIntersectie1;
        intersectionDestination=idIntersectie2;
        length=lungime;
        trafficLights=idSemafor;
        posX=pozX;
        posY=pozY;
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
    public void addCar(Car car){
        cars.add(new Pair<>(length,car));
    }

    public Queue<Pair<Integer, Car>> getCars() {
        return cars;
    }

    public void removeCar(){
        cars.poll();
    }

    @Override
    public String toString() {
        return "Street{" +
                "cars=" + cars +
                ", name='" + name + '\'' +
                ", intersectionSource=" + intersectionSource +
                ", intersectionDestination=" + intersectionDestination +
                ", length=" + length +
                ", posX=" + posX +
                ", posY=" + posY +
                ", trafficLights=" + trafficLights +
                '}';
    }
}
