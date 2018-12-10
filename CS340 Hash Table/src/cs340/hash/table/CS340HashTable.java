/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340.hash.table;

import java.util.*;
import java.io.*;

/**
 *
 * @author Jordan
 */
public class CS340HashTable {

    public static void main( String[] args ) {
        int tableSize = -1;
        int fileSize = -1;
        //String fileName = "LittleWomen.txt";
        String fileName = "TheHuntersWife.txt";
        
        Scanner keyboard = new Scanner( System.in );
        
        System.out.println("How big would you like your hash table?");
        System.out.println("(1) 500\n(2) 1000\n(3) 2000\n(4) 5000\n(5) 10000\n");
        
        System.out.print( "Enter a number: " );
            
        // User chooses a file
        while( tableSize < 1 || tableSize > 5 ) { 
            tableSize = keyboard.nextInt();
                
            if ( tableSize < 0 || tableSize > 5 ) {
                System.out.print( "The number you entered was invalid.\nEnter a number: " );
            }
        }
        
        // Assigns file size and name
        switch (tableSize) { 

            case 1:
                fileSize = 500;
                break;
            case 2:
                fileSize = 1000;
                break;
            case 3:
                fileSize = 2000;
                break;
            case 4:
                fileSize = 5000;
                break;
            case 5:
                fileSize = 10000;
                break;
        }
        
        LinkedList<String>[] hashTable = new LinkedList[ fileSize ];
        
        Scanner inFile = null;
       
     
        try {
            inFile = new Scanner( new File( fileName ) );
        } catch ( FileNotFoundException e ) {
            System.err.println( "File not found." );
        }
        
        HashTable hash = new HashTable(fileSize);
        int storage;
            
        while( inFile.hasNext() ) {
            String curr = inFile.next();
            curr = curr.toLowerCase(); 
            curr = curr.replaceAll( "[^a-zA-Z]", "" );
            
            storage = Math.abs( curr.hashCode()%fileSize );
            
            if( !curr.contentEquals( "" ) ) {
                hash.hashInsert( storage, curr );
            }
        }
        
        
        inFile.close();
        
        PrintWriter printer = null;
        
        if( fileName == "LittleWomen.txt") {
            fileName = "LittleWomen";
        } else {
            fileName = "TheHuntersWife";
        }
        
        
        try {
            FileOutputStream out = new FileOutputStream( "HashTable_" + fileSize + "_" + fileName + ".csv" );
            printer = new PrintWriter( out );
        } catch ( FileNotFoundException e ) {
            System.err.println( "File not found." );
        }
        
        printer.print( "Word" );
        printer.append( "," );
        printer.print( "Number Of Occurences" );
        printer.append( "," );
        printer.print( "Number Of Steps" );
        printer.println( "" );
        
        int counter = 0;
        for( int i = 0; i < hash.hashTable.length; i++ ){
            
            for( int j = 0; j < hash.hashTable[i].size();j++ ){
                
                printer.print( hash.hashTable[i].get(j).key );
                printer.append( "," );
                printer.print( hash.hashTable[i].get(j).numOfOccurrences );
                printer.append( "," );
                printer.print( hash.hashTable[i].get(j).numOfSteps );
                printer.println( "" );
                counter++;
            }
            
        }
        
        printer.print( "Word Total" );
        printer.append( "," );
        printer.print( "Num Occurrences" );
        printer.append( "," );
        printer.print( "Num Steps" );
        printer.append( "," );
        printer.print( "Average Steps Per Word" );
        
        printer.println();
        
        if( fileName == "LittleWomen" ) {
            printer.print( counter );
            printer.append( "," );
            printer.print( "=SUM(B2:B10871)" );
            printer.append( ",");
            printer.print( "=SUM(C2:C10871)" );
            printer.append( "," );
            printer.print( "=C10873/B10873" );
        } else {
            printer.print( counter );
            printer.append( "," );
            printer.print( "=SUM(B2:B126)" );
            printer.append( ",");
            printer.print( "=SUM(C2:C126)" );
            printer.append( "," );
            printer.print( "=C128/B128" );
        }
           
        printer.close();   
    } // End Main   
} // End CS340 Hash Table
