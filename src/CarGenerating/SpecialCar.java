package CarGenerating;

public class SpecialCar extends Car{

    public SpecialCar(int initialPosition, int finalPosition, int distance) {
        this.currentPosition = initialPosition;
        this.finalPosition = finalPosition;
        this.speed = 0;
        this.distance = distance;
    }
}
