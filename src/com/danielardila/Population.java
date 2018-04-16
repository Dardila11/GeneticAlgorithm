package com.danielardila;

/**
 * Individual: individuo
 * Fittest: El mas apto
 * Fitness: La aptitud del individuo
 */

/**
 * La población es un set de individuals
 */

public class Population {

    Individual[] individuals = new Individual[10];
    private int fittest = 0; //El mas adecuado

    // Iniciamos la poblacion
    public void initializePopulation() {
        for (int i = 0; i < individuals.length ; i++) {
            individuals[i] = new Individual();
        }
    }

    public int getFittest() {
        return fittest;
    }


    /**
     * Obtenemos el individual mas apto
     * Recorremos el array de individuals y comparamos cada uno del "fitness" del individual con el maxfit.
     * Si este es mas alto que el maxfit, el nuevo maxFit sera el fitness de ese individual. y le damos la posicion
     * donde se encuentra.
     * maxfit = fitness mas alto
     * maxFitIndex = posicion del fitness mas alto
     * @return el individual mas adecuado el cual se encuentra en maxFitIndex
     */
    public Individual getTheFittest(){
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;

        for (int i = 0; i < individuals.length ; i++) {
            if(maxFit <= individuals[i].getFitness()){
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].getFitness();
        return individuals[maxFitIndex];
    }

    /**
     * Obtehemos el segundo individual mas apto.
     * Para esto
     * @return el individual mas apto el cual se encuentra en la posicion maxFit2
     */
    public Individual getSecondFittest(){
        int maxFit1 = 0;
        int maxFit2 = 0;

        for (int i = 0; i < individuals.length; i++) {
            if(individuals[i].getFitness() > individuals[maxFit1].getFitness()){
                maxFit2 = maxFit1;
                maxFit1 = i;
            }
            else if(individuals[i].getFitness() > individuals[maxFit2].getFitness())
                maxFit2 = i;
        }
        return individuals[maxFit2];
    }

    /**
     * Obtenemos la posicion del individual menos apto "leastFittest"
     * @return minFitIndex la cual es la posicion del menos adecuado
     */

    public int getLeastFittestIndex(){
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;

        for (int i = 0; i < individuals.length; i++) {
            if(individuals[i].getFitness() <= minFitVal){
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }


    /**
     * Calculamos el fitness de cada individual y
     * Obtenemos el individual mas apto "fittest"
     */
    public void calculateFitness(){
        for (Individual individual : individuals) {
            individual.calcFitness();
        }
        getTheFittest();
    }





}
