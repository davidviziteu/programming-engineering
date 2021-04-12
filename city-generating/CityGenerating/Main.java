package CityGenerating;

public class Main {
    public static void main(String[] args){
        System.out.println("Voi genera");
        CityGenerator.generateCity();
        System.out.println("Am generat");
        Street temp;
        for(int i=0;i<CityGenerator.city.getNrOfStreets();i++){
            temp=CityGenerator.city.getStreetByIndex(i);
            System.out.println(temp.getName());
        }
        Intersection temp2;
        for(int i=0;i<CityGenerator.city.getNrOfIntersections();i++){
            temp2=CityGenerator.city.getIntersectionByIndex(i);
            System.out.println(temp2.getName());
        }
    }
}
