package CityGenerating;

public class Main {
    public static void main(String[] args){
        System.out.println("Voi genera");
        CityGenerator.generateCity();
        System.out.println("Am generat");
        Street temp;
        System.out.println(CityGenerator.city.getNrOfIntersections());
        for(int i=0;i<CityGenerator.city.getNrOfStreets();i++){
            temp=CityGenerator.city.getStreetByIndex(i);
            System.out.println(temp.toString());
        }
        Intersection temp2;
        System.out.println("Intersectiile sunt: ");
        for(int i=0;i<CityGenerator.city.getNrOfIntersections();i++){
            temp2=CityGenerator.city.getIntersectionByIndex(i);
            System.out.println(temp2.getName());
        }
    }
}
