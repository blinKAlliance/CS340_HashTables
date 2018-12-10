/*
 * Creates new Hash Table, Inserts Values into Hash Table
 * Checks values for repeated words, logs number of 
 * Occurences
 */
package cs340.hash.table;

import java.util.LinkedList;

/**
 *
 * @author Jordan
 */
public class HashTable {
    
    public LinkedList<Counter>[] hashTable;
     
    public HashTable( int fileSize ) {
        hashTable = new LinkedList[ fileSize ];
        
        for( int i = 0; i < fileSize; i++ ) {
            hashTable[i] = new LinkedList<Counter>();
        }
    } // End Hash Table Method

    public void hashInsert( int location, String key ) {
        boolean located = false;
        
        if( hashTable[ location ].size() == 0 ){
            
            Counter newWord = new Counter( key );
            newWord.numOfSteps += 1;
            hashTable[ location ].add( newWord );
        } else{
            
            int i;
            for( i = 0; i < hashTable[ location ].size(); i++ ){
                if( hashTable[ location ].get( i ).key.contentEquals( key ) ){
                    hashTable[ location ].get( i ).numOfOccurrences++;
                    hashTable[ location ].get( i ).numOfSteps += ( i + 1 );
                    located = true;
                    i = hashTable[ location ].size() + 1;
                }
            }

            if(!located){
                
                Counter newWord = new Counter( key );
                newWord.numOfSteps += ( i + 1 );
                hashTable[ location ].add( newWord );
            }
        }
    } // End hashInsert
}// End Hash Class
