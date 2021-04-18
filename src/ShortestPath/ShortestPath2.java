package ShortestPath;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Street;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPath2 {
    private static final int INT_MAX = 100000;


//    void ShortestPath2() {
//        CityGenerator.generateCity();
//        System.out.println(CityGenerator.city.getNrOfIntersections());
//        List<Street> streets = CityGenerator.city.getStreets();
//        int numberOfIntersections = CityGenerator.city.getNrOfIntersections();
//
//        System.out.println("Am intrat in ShortestPath");
//        System.out.println("nr of intersections : " + numberOfIntersections);
//
//        int startingPoint = 1;//plec din intersectia 1
//        int finishPoint = 7; // ajung in intersectia
//        ShortestPath p = new ShortestPath();
//
//        System.out.println("Shortest Path: " + p.Dijkstra(streets, startingPoint, finishPoint, numberOfIntersections));
//        System.out.println("Am iesit din ShortestPath");
//        System.out.println("Fac o conexiune intre masini si strazi");
//        CarCityConn c1 = new CarCityConn();
//
//    }

    static int minimumDistance(int[] distance, boolean[] included) {
        int min = INT_MAX, index = 0;
        for (int i = 0; i < distance.length; i++) {
            if (!included[i] && distance[i] <= min) {
                min = distance[i];
                index = i;
            }
        }
        return index;
    }
    //dijkstra
    static public Tuple<Integer, List<Integer>> compute(List<Street> streets, int startPoint, int finishPoint, int numberofIntersections) {

        int[] distance = new int[numberofIntersections];
        boolean[] included = new boolean[numberofIntersections];
        Integer[]parent = new Integer[numberofIntersections];
        for (int i = 0; i < numberofIntersections; i++) {
            distance[i] = INT_MAX;
            included[i] = false;
            parent[i] = 0;
        }
        distance[startPoint] = 0;

        for (int i = 0; i < numberofIntersections - 1; i++) {

            int index = minimumDistance(distance, included);

            included[index] = true;
            for (int j = 0; j < numberofIntersections; j++) {
                if (!included[j] &&
                        distance[index] != INT_MAX &&
                        adjacent(j, index, streets) &&
                        distance[index] + getStreetLength(j, index, streets) < distance[j]) {
                    distance[j] = distance[index] + getStreetLength(j, index, streets);
                    parent[j] = index; //tre vazut mai departe
                }
            }
        }
        List<Integer> parents=Arrays.asList(parent);
        return new Tuple<Integer, List<Integer>>(distance[finishPoint],parents);
    }

    static boolean adjacent(int x, int y, List<Street> streets) {

        for (Street street : streets) {
            if (street.getIntersectionSource() == x && street.getIntersectionDestination() == y)
                return true;
            if (street.getIntersectionSource() == y && street.getIntersectionDestination() == x)
                return true;
        }
        return false;
    }

    static int getStreetLength(int x, int y, List<Street> streets) {
        if (adjacent(x, y, streets)) {
            for (Street street : streets) {
                if (street.getIntersectionSource() == x && street.getIntersectionDestination() == y)
                    return street.getLength();
                if (street.getIntersectionSource() == y && street.getIntersectionDestination() == x)
                    return street.getLength();
            }
        }
        return INT_MAX;
    }

    public static void main(String[] args) {
        CityGenerator.generateCity();
        var cityInstance = CityGenerator.city;
        System.out.println(cityInstance);
        var exampleCar = CityGenerator.city.getCars().get(0);
        var res = ShortestPath2.compute(
                cityInstance.getStreets(),
                0,
                3,
                cityInstance.getStreets().size()
        );
        System.out.println(res.toString());
    }
}

class Tuple<K, V> {
    public K first;
    public V second;
    public Tuple(K first, V second){
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second.toString() +
                '}';
    }
}