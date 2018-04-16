package com.danielardila;

import java.util.Random;

/**
 * Individual: individuo
 * Fittest: El mas apto
 * Fitness: La aptitud del individuo
 */


/**
 * Cada individual es una solucion al problema que se va a resolver.
 * Un individual esta caracterizado por un set de parametros llamados variables conocidas como Genes.
 * Estos genes se unen y forman una cadena para armar el cromosoma (Individual)
 */

public class Individual {


    private int geneLength = 5;
    private int fitness = 0;
    private int[] genes = new int[5];

    public int getFitness() {
        return fitness;
    }
    public int getGeneLength() {
        return geneLength;
    }

    public int getGenes(int i) {
        return genes[i];
    }

    public void setGenes(int gen) {
        this.genes[gen] = gen;
    }

    public void setGenesPos(int pos, int gen){
        this.genes[pos] = gen;
    }

    public Individual() {
        // Creamos un set genes aleatoriamente

        Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            // Crea un array de numeros binarios
            genes[i] = Math.abs(random.nextInt() % 2);
        }
        fitness = 0;
    }

    /**
     * Calculamos el fitness del indidual.
     * Para esto es necesario colocar un Fitness Score = 1
     */
    public void calcFitness(){
        fitness = 0;
        for (int gene : genes) {
            if (gene == 1)
                fitness++;
        }
    }

}
