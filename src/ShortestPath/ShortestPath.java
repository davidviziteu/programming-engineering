package ShortestPath;

import CarGenerating.CarGenerator;
import CityGenerating.CityGenerator;
import CityGenerating.Street;

import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
//    public static void main(String[] args) {
//
//        ShortestPath2 p1= new ShortestPath2();
//        p1.ShortestPath();
//        List<Street> streets= new ArrayList<>();
//        Street s1= new Street("s1",1, 2, 4, 1);
//        Street s2= new Street("s2",2, 3, 3, 1);
//        Street s3= new Street("s3",3, 4, 8, 1);
//        Street s4= new Street("s4",4, 1, 5, 1);
//
//        streets.add(s1);
//        streets.add(s2);
//        streets.add(s3);
//        int numberOfIntersections=4;
//        int startingPoint=1;//plec din intersectia 1
//        int finishPoint=3; // ajung in intersectia
//        ShortestPath p= new ShortestPath();
//        System.out.println("Shortest Path: "+p.Dijkstra(streets, startingPoint, finishPoint,numberOfIntersections));
//
//    }
    int INT_MAX=10000;
    int minimumDistance(int distance[], boolean included[]){
        int min=INT_MAX, index=0;

        for(int i=0; i< distance.length; i++){
            if(included[i]==false && distance[i]<= min)
            {
                min=distance[i];
                index= i;
            }
        }
        return index;
    }
    public int Dijkstra(List<Street> streets, int startPoint, int finishPoint, int numberofIntersections ){

        int distance[]=new int[numberofIntersections];
        boolean included[]= new boolean[numberofIntersections];

        for(int i=0; i<numberofIntersections; i++){
            distance[i]=INT_MAX;
            included[i]=false;
        }
        distance[startPoint]=0;

        for(int i=0; i< numberofIntersections - 1; i++){

            int index = minimumDistance(distance, included);

            included[index]= true;
            for(int j=0; j< numberofIntersections; j++){
                if(included[j]==false &&distance[index]!=INT_MAX && adiacent(j, index, streets) && distance[index] + getStreetLength(j, index,streets) < distance[j]){
                    distance[j]=distance[index] + getStreetLength(j, index,streets) ;
                }
            }
        }
        return distance[finishPoint];
    }
    boolean adiacent(int x, int y, List<Street> streets){

        for(int i=0; i<streets.size(); i++) {

            if (streets.get(i).getIntersectionSource() == x && streets.get(i).getIntersectionDestination() == y)
                return true;

            if (streets.get(i).getIntersectionSource() == y && streets.get(i).getIntersectionDestination()== x)
                return true;
        }
        return false;
    }
    int getStreetLength(int x, int y, List<Street> streets){
        if(adiacent(x,y,streets)){
            for(int i=0; i<streets.size(); i++) {

                if (streets.get(i).getIntersectionSource() == x && streets.get(i).getIntersectionDestination() == y)
                    return streets.get(i).getLength();

                if (streets.get(i).getIntersectionSource() == y && streets.get(i).getIntersectionDestination()== x)
                    return streets.get(i).getLength();
            }
        }
        return INT_MAX;
    }


}