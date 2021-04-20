package Alg2;

import CityGenerating.City;
import CityGenerating.CityGenerator;
import CityGenerating.Street;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Alg2Test {

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void run() {
    }

    @org.junit.jupiter.api.Test
    void populate() {
    }

    @org.junit.jupiter.api.Test
    void fitness() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        List<Street> path = new ArrayList<>();

        // TODO: pick a path

        double fit = algorithm.fitness(path);
        // TODO: check fit value with what it should be
        assert(true);
    }

    @org.junit.jupiter.api.Test
    void selection() {
    }

    @org.junit.jupiter.api.Test
    void mutate() {
    }

    @org.junit.jupiter.api.Test
    void createRandomPath() {
        CityGenerator.generateCity();
        City city = CityGenerator.city;
        Alg2 algorithm = new Alg2(city);

        List<Street> path = algorithm.createRandomPath(city.getStreetByIndex(0), city.getStreetByIndex(3), null);
        for (int index = 1; index < path.size(); ++index) {
            // check if sequence is properly connected
            assert(path.get(index - 1).getIntersectionDestination().equals(path.get(index).getIntersectionSource()));
        }
    }

    @org.junit.jupiter.api.Test
    void crossover() {
    }

    @org.junit.jupiter.api.Test
    void findCommonGeneOfTwoChromosomes() {
    }
}