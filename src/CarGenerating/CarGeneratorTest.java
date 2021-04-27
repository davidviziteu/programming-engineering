package CarGenerating;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarGeneratorTest {

    @Test
    void testGenerate_Low() {
        CarGenerator carGenerator = new CarGenerator("Low");
        Assertions.assertEquals(CarGenerator.numberOfCars , (int) (0.2 * carGenerator.totalStreetsLength()));
    }

    @Test
    void testGenerate_Medium() {
        CarGenerator carGenerator = new CarGenerator("Medium");
        Assertions.assertEquals(CarGenerator.numberOfCars , (int) (0.4 * carGenerator.totalStreetsLength()));
    }

    @Test
    void testGenerate_High() {
        CarGenerator carGenerator = new CarGenerator("High");
        Assertions.assertEquals(CarGenerator.numberOfCars , (int) (0.6 * carGenerator.totalStreetsLength()));
    }


}