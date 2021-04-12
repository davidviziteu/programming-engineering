package ShortestPath;

import CarGenerating.CarGenerator;
import CityGenerating.CityGenerator;

    public class CarCityConn {
        public CarCityConn(){
            CarGenerator c1 = new CarGenerator(2);
            c1.generate();
            System.out.println("am intrat in CarCityCONN");
            System.out.println(c1.numberOfCars);
            for(int i=0; i< c1.numberOfCars; i++){
                //numarul strazii initiale, cu id-ul intersectiei?
                int initialPosition=c1.cars.get(i).getInitialPosition();
                CityGenerator.city.getStreetByIndex(initialPosition).addCar(c1.cars.get(i));
            }
            for(int i=0; i<CityGenerator.city.getNrOfStreets(); i++){
                System.out.println(i + "\tmasina\t");
                System.out.println(CityGenerator.city.getStreetByIndex(i).getCars());
            }
        }
    }
