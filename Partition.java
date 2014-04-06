import java.util.Scanner;

public class Partition {
    public static void main(String[] args) {
	
	try {

	    // new scanner
	    Scanner scan = new Scanner(System.in);
	    
	    // length of the multiset
	    int size = Integer.parseInt(scan.nextLine());

	    // new multiset
	    int[] values = new int[size];
	    
	    // fill the multiset with values
	    for(int i = 0; i < values.length; i++) {

		// get number from user
		int number = Integer.parseInt(scan.nextLine());
		
		// insert value into the multiset
		values[i] = number;
	    }
	    

	    output();
	    System.out.println("\n\n");
	    output(values, values);
	    // close the scanner
	    scan.close();
	}

	// something went wrong
	catch (Exception e) {
	    System.out.println("Error: " + e);
	}    
    }

    public static void output() {
	System.out.println("There is no satisfying partition");
    }

    public static void output(int[] first, int[] second) {
	String output = "{ ";

	for(int i : first) {
	    output += i + " ";
        }

	output += "} { ";

	for(int i : second) {
	    output += i + " ";
	}

	output += "}";

	System.out.println(output);
    }
}