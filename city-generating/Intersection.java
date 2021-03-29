package CityGenerator;
import java.util.ArrayList;

public class Intersection {
    private String name;
    private ArrayList<Integer> streets= new ArrayList<Integer>();
    private Integer posX;
    private Integer posY;
    private bool canPark;
    //add an array of cars

    Intersection(String nume, ArrayList<Integer> strazi){
        name=nume;
        streets=strazi;
        canPark=false;
    }
    Intersection(String nume, ArrayList<Integer> strazi, Boolean park){
        name=nume;
        streets=strazi;
        canPark=true;
    }

    Intersection(String nume, ArrayList<Integer> strazi, Integer pozX, Integer pozY){
        name=nume;
        streets=strazi;
        posX=pozX;
        posY=pozY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Street[] getStreets() {
        return streets;
    }

    //geters and setters
    public void setStreets(Street[] streets) {
        this.streets = streets;
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

}
