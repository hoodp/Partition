import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**********************************************************************
 * The following class decides if a given set can be partitioned into
 * two equal sets.
 * @author Paul Hood
 * @version 4/16/2014
 *********************************************************************/
public class Partition {

	/******************************************************************
	 * Method get size of the array and values from the user
	 * @param args
	 *****************************************************************/
	public static void main(String[] args) {

		// new scanner for reading input
		Scanner in = new Scanner(System.in);

		// list that stores new integers
		List<Integer> set = new ArrayList<Integer>();
		
		int size = in.nextInt();

		// fill the array with values
		for (int i = 0; i < size; i++) {
			set.add(in.nextInt());
		}	

		// closes the scanner
		in.close();

		new Partition(set);
	}

	/******************************************************************
	 * Constructor takes a set of integers and decides if the set can
	 * be partitioned or not.
	 * @param set Set of values 
	 *****************************************************************/
	public Partition(List<Integer> set) {

		// calculate the sum of the set
		int sum = calcSum(set);

		// if sum is not odd, create a 2d array
		if (sum % 2 == 0) {
			boolean[][] partition = fillTable(set, sum);

			// set can be partitioned
			if (validPartition(partition)) {

				// set that is the same as the first set
				List<Integer> partOne = new ArrayList<Integer>(set);
				
				// set for saving adding values to new partition
				List<Integer> partTwo = new ArrayList<Integer>();

				if (calcSum(partOne) != calcSum(partTwo)) {
					int rowIndex = sum / 2;
					
					// loop searches for first false in the last row
					for (int i = partition[0].length - 1; i > 0; i--) {
						
						// found first false
						if (!partition[rowIndex][i - 1]) {
							
							// subtract next value in array from row
							rowIndex -= set.get(i - 1);
							
							// remove the value from the first set
							partOne.remove(new Integer(set.get(i - 1)));
							
							// add the value to the second array
							partTwo.add(set.get(i - 1));
						}
					}
				}
				System.out.println("Input: " + setOutput(set));
				System.out.println("Yes: " 
						+ setOutput(partOne) + setOutput(partTwo));

			}
		
                  	// Set cannot be partitioned
		       	else {
		       	        System.out.println("Input: " + setOutput(set));
				System.out.println("There is no satisfying partition.");
		       	}	    
				    
		}

		// partition cannot be made because the sum is odd
		else {
		    System.out.println("Input: " + setOutput(set));
			System.out.println("There is no satisfying partition.");
		}
	}

	/******************************************************************
	 * Method returns the sum of an arraylist.
	 * @param set List of integers to calculate sum 
	 * @return Sum of the list
	 *****************************************************************/
	private int calcSum(List<Integer> set) {
		int sum = 0;
		for (Integer i : set) {
			sum += i;
		}
		return sum;
	}

	/******************************************************************
	 * Assuming the sum is even, this method calculates if each integer
	 * is a subset of part of the set. 
	 * @param set List of integers 
	 * @return 2d boolean array
	 *****************************************************************/
	private boolean[][] fillTable(List<Integer> set, int sum) {

		// number of columns for array
		int cols = set.size() + 1;

		// number of columns for array
		int rows = sum / 2 + 1;

		// 2d boolean array
		boolean[][] partition = new boolean[rows][cols];

		// set first row of array to true
		for (int i = 0; i < cols; i++) {
			partition[0][i] = true;
		}

		// loop through each row and column 
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {

				// set current position to previous column value
				partition[i][j] = partition[i][j - 1];

				/* check if row index is greater than or equal to
				 *  the previous value the set and that previous value
				 *  is not true */
				if (i >= set.get(j - 1) && !partition[i][j]) {
					partition[i][j] = 
							partition[i - set.get(j - 1)][j - 1];
				}

			}
		}

		return partition;
	}

	/******************************************************************
	 * Method checks the value of the last row and last column of the 
	 * array.
	 * @param part array to check for row and column  
	 * @return 2d boolean array
	 *****************************************************************/
	private boolean validPartition(boolean[][] part) {
		int rows = part.length - 1;
		int cols = part[0].length - 1;
		return part[rows][cols];
	}

	/******************************************************************
	 * Method created a string for displaying a set.
	 * @param Set to convert to string
	 * @return set of integers inside brackets
	 *****************************************************************/
	private String setOutput(List<Integer> set) {
		String output = "{ ";
		for (Integer i : set) {
			output += i + " ";
		}
		return output + "}";
	}

}