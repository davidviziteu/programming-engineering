package CityGenerator;
import java.util.ArrayList;

public class City {
    private ArrayList<Street> streets= new ArrayList<Streets>();
    private ArrayList<Intersection> intersections= new ArrayList<Intersection>();
    private ArrayList<TrafficLights> trafficLights= new ArrayList<TrafficLights>();
    private ArrayList<Cars> cars= new ArrayList<Cars>();
    private Integer nrOfStreets;
    private Integer nrOfIntersections;
    
    City(ArrayList<Street> strazi, ArrayList<Intersection> intersectii, ArrayList<TrafficLights> semafoare, ArrayList<Cars> masini){
        streets=strazi;
        intersections=intersectii;
        trafficLights=semafoare;
        cars=masini;
    }


    public ArrayList<Street> getStreets() {
        return streets;
    }

    public void setStreets(ArrayList<Street> streets) {
        this.streets = streets;
    }

    public ArrayList<Intersection>  getCity() {
        return intersections;
    }

    public void setIntersections(ArrayList<Intersection> intersections) {
        this.intersections = intersections;
    }

    public Integer getNrOfStreets() {
        return nrOfStreets;
    }

    public void setNrOfStreets(Integer nrOfStreets) {
        this.nrOfStreets = nrOfStreets;
    }

    public Integer getNrOfIntersections() {
        return nrOfIntersections;
    }

    public void setNrOfIntersections(Integer nrOfIntersections) {
        this.nrOfIntersections = nrOfIntersections;
    }

    //methods
    public Street getStreetByName(String streetName){
        for (Street s: streets
             ) {
            if (s.getName().equals(streetName)){
                return s;
            }
        }
        return null;
    }

    public Street getStreetByIndex(int i){
       return streets.get(i);
    }

    public Intersection getIntersectionByName(String intersectionName){
        for (Intersection i: intersections
        ) {
            if (i.getName().equals(intersectionName)){
                return i;
            }
        }
        return null;
    }

    public Intersection getIntersectionByIndex(int i){
        return intersections.get(i);
    }
    public TrafficLights getTLightsById(int i){
        return trafficLights.get(i);
    }
    public Cars[] getCars(){
        return cars;
    }
}
