package com.proiect;

import java.util.*;

public class Pair<K, V extends Comparable<V>> implements Comparable<CityGenerating.Pair<K, V>> {
    private K key;
    private V value;

    public Pair (K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey () {
        return key;
    }

    public void setKey (K key) {
        this.key = key;
    }

    public V getValue () {
        return value;
    }

    public void setValue (V value) {
        this.value = value;
    }


    @Override
    public int compareTo (CityGenerating.Pair<K, V> o) {
        return this.value.compareTo(o.getValue());
    }

    @Override
    public String toString () {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

public class Main {

    private City city;
    private List<List<Street>> population;

    public double fitness(List<Street> chromosome, City city) {
        List<Street> streets = city.getStreets();
        int countTrafficLights = streets.size();
        int[] capacity = new int[countTrafficLights];
        int[] load = new int[countTrafficLights];

//        pentru fiecare intersectie:
//        calculeaza gradul de incarcare
//        (grad incarcare: suma numarului de masini care intra/asteapta la intrarea in intersectie
//                => procentaj din capacitatea maxima al strazilor)
        for (Street street : streets) {
            // TODO: add getCapacity, return street capacity considering "average" car size
            capacity[street.intersectionDestination] += street.getCapacity();
            // TODO: add getLoad; return number of cars on street
            load[street.intersectionDestination] += street.getLoad();
        }

        float[] loadDensity = new float[countTrafficLights];
        for (int i = 0; i < countTrafficLights; ++i) {
            loadDensity[i] = float(load[i]) / float(capacity[i]);
        }

//        fitness:
//        pentru o secventa de strazi, vedem cu cat ar creste masina user "incarcarea" intersectiilor
//        adica cat creste prin procentaj
//
//        cu cat aceasta crestere de incarcare este mai MARE, cu atat mai bine!
//                (daca creste mult, inseamna ca erau putin incarcate inainte)
//
//        mergem pe presupunerea ca intersectiile incarcate se mentin incarcate (si deci, incete)
//        iar strazile putin incarcate, raman putin incarcate (si deci, mai rapide)
        float fitness = 0.0f;
        for (Street gene : chromosome) {
            int id = gene.intersectionDestination;
            fitness += (float(load[id] + 1) / float(capacity[id])) - loadDensity[id];
        }
        return fitness;
    }

    public void selection() {
        // k = 10; j = 5;
        Integer maxNumberOfSelectionChromosomes = 9;
        Integer numberOfBestChromosomes = 4;
        Set<Integer> indexList = new HashSet<Integer>();

        for (int k = 0; k < maxNumberOfSelectionChromosomes; k++) {
            Random random = new Random();
            indexList.add(random.nextInt(population.size()));
        }
        Collections.sort(indexList, new Comparator<Integer>() {
            public int compare(Integer index1, Integer index2) {
                return Double.compare(fitness(population.get(index1), city), fitness(population.get(index2), city));
            }
        });

        List<List<Street>> secondPopulation = new ArrayList<List<Street>>();
        for (int i = 0; i < 5; i++) {
            for (int j = numberOfBestChromosomes; j >= 0; j--) {
                secondPopulation.add(population.get(j));
            }
        }
        population = secondPopulation;
    }

    public List<Street> mutate(List<Street> chromosome) {
        Random random = new Random();
        double ratio =  random.nextDouble();
        int index = random.nextInt(chromosome.size());
        int randomChoice = random.nextInt(6);  // cate strazi sunt - va fi schimbat

        if(ratio < 0.01) {
            chromosome.get(index) = streets.get(randomChoice);
        }

        return chromosome;
    }

    public void crossover(List<List<Street>> population) {

        List<Street> chromosome1 = new ArrayList<Street>();
        List<Street> chromosome2 = new ArrayList<Street>();


        Pair<Integer, Integer> pair = new Pair<Integer, Integer>;

        for (int i = 0; i < population.size(); i = i + 2) {
            int j = i + 1;
            pair = findCommonNodeOfTwoChromosomes(population.get(i), population.get(j));

            // add left side of chromosome1 to chromosome1
            for (int k = 0; k < pair.getKey(); k++) {
                chromosome1.add(population.get(i).get(k));
            }

            // add right side of chromosome2 to chromosome1
            for (int k = pair.getValue() + 1; k < population.get(j).size(); k++) {
                chromosome1.add(population.get(j).get(k));
            }

            // add left side of chromosome2 to chromosome2
            for (int k = 0; k < pair.getValue(); k++) {
                chromosome2.add(population.get(j).get(k));
            }

            // add right side of chromosome1 to chromosome2
            for (int k = pair.getKey() + 1; k < population.get(i).size(); k++) {
                chromosome2.add(population.get(i).get(k));
            }

            population.get(i) = chromosome1;
            population.get(j) = chromosome2;
        }
    }

    public Pair<Integer, Integer> findCommonNodeOfTwoChromosomes(List<Street> chromosome1, List<Street> chromosome2) {
        // random common vertex of 2 chromosomes
        List<Street> commonStreets = new ArrayList<Street>();
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>();

        // get the common streets of our 2 chromosomes
        for (Street street1 : chromosome1) {
            for (Street street2 : chromosome2) {
                if (street1 == street2) {
                    commonStreets.add(street1);
                }
            }
        }

        // get random street from common streets
        Random rand = new Random();
        int n = rand.nextInt(commonStreets.size());

        // set pair of index from our chromosomes
        for (int i = 0; i < chromosome1.size(); i++) {
            if (chromosome1.get(i) == commonStreets.get(n)) {
                pair.setKey(i);
            }
        }
        for (int i = 0; i < chromosome2.size(); i++) {
            if (chromosome2.get(i) == commonStreets.get(n)) {
                pair.setValue(i);
            }
        }
        return pair;
    }


    // 5 common node
    // 2 position of chromosome i
    // 4 position of chromosome ++i

    // chromosome = [1,2,3,5,4,8,6,7]

    public static void main(String[] args) {
        // write your code here
    }


}
