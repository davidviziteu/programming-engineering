package CarGenerating;

import java.util.ArrayList;

public class CarGenerator {
    private int frequency;
    public ArrayList<Car> cars;

    public void generate(){
        //todo: make cars go vroom
        //in functie de frecventa pe fiecare strada vor fi generate un numar de masini(strict <= capacitatea strazii)
    }

    public CarGenerator(int frequency) {
        cars=new ArrayList<Car>();
        this.frequency = frequency;
    }
}
