package com.danielardila;

import java.util.Random;

/**
 * Population: Población
 * Individual: individuo
 * Fittest: El mas apto
 * Fitness: La aptitud del individuo
 * Offspring: descendiente (la nueva generación)
 */



public class GeneticAlgorithm {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;


    public static void main(String[] args) {
        Random random = new Random();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        geneticAlgorithm.population.initializePopulation();
        geneticAlgorithm.population.calculateFitness();

        System.out.println("Generation: (Generación) " + geneticAlgorithm.generationCount +
                          " Fittest: (El más apto) " + geneticAlgorithm.population.getFittest());


        while (geneticAlgorithm.population.getFittest() < 5){
            geneticAlgorithm.generationCount ++;

            geneticAlgorithm.selection();
            geneticAlgorithm.crossover();

            // realizar la mutacion mediante una probabilidad aleatoria
            if(random.nextInt()%7 < 5)
                geneticAlgorithm.mutation();

            // Agregamos el offspring mas adecuado a la poblacion
            geneticAlgorithm.addFittestOffspring();

            // Calculamos el nuevo valor del fitness de todos los individuos de la poblacion
            geneticAlgorithm.population.calculateFitness();


            /**
             * El algoritmo finaliza si la población ha convergido
             * (no produce descendencia que sea significativamente diferente de la generación anterior).
             * Luego se dice que el algoritmo genético ha proporcionado una serie de soluciones a nuestro problema.
             */

            System.out.println("Generation: (Generación) " + geneticAlgorithm.generationCount +
                    " Fittest: (El más apto) " + geneticAlgorithm.population.getFittest());


            System.out.println("\nSolución encontrada en la generación  " + geneticAlgorithm.generationCount);
            System.out.println("Fitness: (El más apto) "+geneticAlgorithm.population.getTheFittest().getFitness());
            System.out.print("Genes: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(geneticAlgorithm.population.getTheFittest().getGenes(i));
            }

            System.out.println("");

        }


    }

    /**
     * La idea de la fase de selección es seleccionar a los
     * individuals mas aptos y dejarles pasar sus genes a la próxima generación.
     *
     * Se seleccionan dos pares de individuos (padres) en función de sus puntajes de aptitud.
     * Los individuals con una buena forma física tienen más posibilidades de ser
     * seleccionados para la reproducción.
     */

    private void selection(){
        fittest = population.getTheFittest();
        secondFittest = population.getSecondFittest();

    }

    /**
     * Crossover es la fase más importante en un algoritmo genético.
     * Para cada par de padres que se aparearán,
     * se elige al azar un punto (crossoverPoint) de cruce dentro de los genes.
     */

    private void crossover(){
        Random random = new Random();

        int crossoverPoint = random.nextInt(population.individuals[0].getGeneLength());

        //Se intercambian los valores entre los padres
        for (int i = 0; i < crossoverPoint ; i++) {
            int aux = fittest.getGenes(i);
            fittest.setGenes(secondFittest.getGenes(i));
            secondFittest.setGenes(aux);
        }
    }

    /**
     * En ciertos OffSprings formados, algunos de sus genes pueden ser sometidos a una mutación
     * con una baja probabilidad aleatoria.
     * Esto implica que algunos de los bits en la cadena de bits se pueden voltear.
     */

    private void mutation(){
        Random random = new Random();

        int mutationPoint = random.nextInt(population.individuals[0].getGeneLength());

        // Cambiamos los valores en el punto de mutacion
        if(fittest.getGenes(mutationPoint) == 0){
            fittest.setGenesPos(mutationPoint,1);
        }
        else
            fittest.setGenesPos(mutationPoint,0);

        mutationPoint = random.nextInt(population.individuals[0].getGeneLength());

        if(secondFittest.getGenes(mutationPoint) == 0)
            secondFittest.setGenesPos(mutationPoint,1);
        else
            secondFittest.setGenesPos(mutationPoint,0);
    }

    private Individual getFittestOffspring(){
        if(fittest.getFitness() > secondFittest.getFitness()){
            return fittest;
        }
        return secondFittest;
    }

    /**
     * Reemplazamos el individual mas adecuado con el mas adecuado de la nueva generacion
     */
    private void addFittestOffspring(){

        fittest.calcFitness();
        secondFittest.calcFitness();
        int leastFitnessIndex = population.getLeastFittestIndex();
        population.individuals[leastFitnessIndex] = getFittestOffspring();
    }
}
