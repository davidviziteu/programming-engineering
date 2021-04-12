package CityGenerating;
import CarGenerating.Car;
import java.util.ArrayList;

public class City {
    private ArrayList<Street> streets= new ArrayList<Street>();
    private ArrayList<Intersection> intersections= new ArrayList<Intersection>();
    private ArrayList<TrafficLights> trafficLights= new ArrayList<TrafficLights>();
    private ArrayList<Car> cars= new ArrayList<Car>();
    public Integer[][] mapPreGenerated={
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {2, 2, 5, 2, 2, 5, 2, 2, 2, 2},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {2, 2, 5, 2, 2, 5, 2, 2, 2, 2},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 4, 0, 0, 0, 0}
    };
    public Integer[][] map=new Integer[9][9];
    private Integer nrOfStreets;
    private Integer nrOfIntersections;

    City(ArrayList<Street> strazi, ArrayList<Intersection> intersectii, ArrayList<TrafficLights> semafoare, ArrayList<Car> masini){
        streets=strazi;
        intersections=intersectii;
        trafficLights=semafoare;
        cars=masini;
        nrOfStreets= strazi.size();
        nrOfIntersections=intersectii.size();
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

    public Intersection getIntersectionByName(String intersectionName){
        for (Intersection i: intersections
        ) {
            if (i.getName().equals(intersectionName)){
                return i;
            }
        }
        return null;
    }
    public Street getStreetByIndex(int i){
        return streets.get(i);
    }
    public Intersection getIntersectionByIndex(int i){
        return intersections.get(i);
    }
    public TrafficLights getTLightsById(int i){
        return trafficLights.get(i);
    }
    public ArrayList<Car> getCars(){
        return cars;
    }

    public void generateMap() {
        for (int i = 0; i < streets.size(); i += 2) {
            Integer xBeg = streets.get(i).getPosX();
            Integer yBeg = streets.get(i).getPosY();
            Integer xFin = streets.get(streets.get(i).getIntersectionDestination()).getPosX();
            Integer yFin = streets.get(streets.get(i).getIntersectionDestination()).getPosY();
            if (xBeg == xFin) {
                for (int j = yBeg; j <= yFin; j++)
                    map[xBeg][j] = 2;
            } else if (yBeg == yFin) {
                for (int j = xBeg; j <= xFin; j++)
                    map[j][yBeg] = 4;
            }
        }
        //incep de la index 4
        for (int i = 8; i < intersections.size(); i++) {
            Integer x = intersections.get(i).getPosX();
            Integer y = intersections.get(i).getPosY();
            map[x][y] = 5;
        }
    }
}
