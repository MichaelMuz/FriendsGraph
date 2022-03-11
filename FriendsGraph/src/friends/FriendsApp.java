package friends;

import java.io.*;
import java.util.*;

// Testing client for Friends
public class FriendsApp {

    public static void main (String[] args) {

//	if ( args.length < 1 ) {
//	    System.out.println("Expecting graph text file as input");
//	    return;
//	}

	String filename = "sampleGraph.txt";
	try {
	    Graph g = new Graph(new Scanner(new File(filename)));

	    
	    // Update p1 and p2 to refer to people on Graph g
	    // sam and sergei are from sample graph
	    /*
	    String p1 = "heather";
	    String p2 = "poo";
	    ArrayList<String> shortestChain = Friends.shortestChain(g, p1, p2);

	    // Testing Friends.shortestChain
	    System.out.println("Shortest chain from " + p1 + " to " + p2);
	    for ( String s : shortestChain ) {
		System.out.println(s);
	    }
	    */
	    // ADD test for Friends.cliques() here
	    /*
	    ArrayList<ArrayList<String>> clis = Friends.cliques(g, "poo");
	    if(clis == null) {
	    	System.out.print("null");
	    }
	    else {
	    	for(ArrayList<String> arr : clis) {
		    	System.out.println("next clique:");
		    	for(String s : arr) {
		    		System.out.print(s);
		    	}
		    	System.out.println();
		    }
	    }
	    */
	    
	    // ADD test for Friends.connectors() here
	    
	    ArrayList<String> f = Friends.connectors(g);
	    for(String s : f) {
	    	System.out.println(s);
	    }
	    
	    
	} catch (FileNotFoundException e) {

	    System.out.println(filename + " not found");
	}
    }
}
