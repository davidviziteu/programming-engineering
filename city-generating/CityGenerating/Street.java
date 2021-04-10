package CityGenerating;
import java.util.Queue;

import CarGenerating.Car;
import javafx.util.Pair;
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

    Street(String nume, Integer idIntersectie1, Integer idIntersectie2, Integer lungime, Integer idSemafor){
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

    public Integer getIntersection1() {
        return intersectionSource;
    }

    public void setIntersection1(Integer intersection1) {
        this.intersectionSource = intersection1;
    }

    public Integer getIntersection2() {
        return intersectionDestination;
    }

    public void setIntersection2(Integer intersection2) {
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
        cars.add(new Pair<Integer,Car>(length,car));
    }

    public void removeCar(){
        cars.poll();
    }
}
