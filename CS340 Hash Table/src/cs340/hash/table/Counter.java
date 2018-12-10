/*
 * Basic Word Class to log a word 
 * Stores word occurrences and steps 
 * to find said word
 */
package cs340.hash.table;

/**
 *
 * @author Jordan
 */

public class Counter {
    
    String key;
    int numOfOccurrences;
    int numOfSteps;
    
    public Counter( String tempKey ) {
        key = tempKey;
        numOfOccurrences = 1;
        numOfSteps = 0;
    } // End Counter Constructor
} // End Counter Class
