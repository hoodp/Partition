import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partition {

	/** 2d array of booleans */
	private boolean[][] partition;
	
	/** max number of rows for boolean array */
	private int maxRows;
	
	/** max number of cols in boolean array */
	private int maxCols;
	
	/** array of the set of numbers */
	private int[] set;
	
	/** sum of the set */
	private int sum;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt();
		int[] set = new int[size];

		for (int i = 0; i < set.length; i++) {
			set[i] = scan.nextInt();
		}

		scan.close();

		new Partition(set);
	}

	public Partition(int[] set) {

		// int array of values
		this.set = set;

		// calculate the sum of the array
		sum = setSum(set);
		
		maxRows = sum / 2 + 1;
		
		maxCols = set.length + 1;

		// if sum is even fill the boolean array
		if (validSum(sum)) {

			// fill the partition boolean
			partition = fillTable(set);

			// check if partition is valid
			if (validPartition()) {
				output(set);
			}
			else {
				output();
			}
		}
		else {
			output();
		}
	}

	/** method fills the 2d boolean array */
	private boolean[][] fillTable(int[] set) {

		// boolean array of possible values
		boolean part[][] = new boolean[maxRows][maxCols];

		/** set first row to true */
		for (int i = 0; i < maxCols; i++) {

			// first row
			part[0][i] = true;
		}

		for (int i = 1; i < maxRows; i++) {
			for (int j = 1; j < maxCols; j++) {
				part[i][j] = part[i][j - 1];

				// if part[i][j] is true, set rest of row true
				if (part[i][j]) {
					part =  fillRow(part, i, j, maxCols);
					break;
				}

				// check if i is greater arr[j-1]
				if (i >= set[j - 1]) {

					// index of i minus the previous value in the set
					int rowIndex = i - set[j - 1];

					// previous column index
					int colIndex = j - 1;

					// set to value of diagonal
					part[i][j] = part[rowIndex][colIndex];
				}
			}
		}

		display(part);
		return part;
	}

	/** method checks if the last row in the last column is true */
	private boolean validPartition() {
		return partition[maxRows - 1][maxCols - 1];
	}

	/** method fills rest of row to true */
	private boolean [][] fillRow(boolean[][] part, int row, int col, int maxCol) {
		for (int i = col; i < maxCol; i++) {
			part[row][i] = true;
		}
		return part;
	}

	private void display(boolean[][] part) {
		
		// string output
		String output = "";
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				output += part[i][j] + "\t";
			}
			output += "\n";
		}
		
		System.out.println(output);
	}

	/** method checks if array can be split into two arrays */
	private boolean validSum(int sum) {
		return sum % 2 == 0;
	}

	/** method calculates the sum of the array */
	private int setSum(int[] values) {
		int total = 0;

		// loop through each integer
		for (int i : values) {
			total += i;
		}

		return total;
	}

	/** method returns output of invalid set */
	private void output() {
		String message = "Output: False\nThere is no satisfying partition.";
		System.out.println(message);
	}
	

        /** method calculates sum of an integer arraylist */
	private int calcSum(ArrayList<Integer> values) {
		int total = 0;
		for (Integer i : values) {
			total += i;
		}
		return total;
	}

	/** method returns output of valid set */
	private void output(int[] values) {

		// value being searched for
		int searchValue = sum / 2;
		
		// maximum length of new set
		int maxLength = values.length / 2;
		
		String message = "Output: True\n{ ";
		
		Arrays.sort(values);
		
		ArrayList<Integer> setOne = new ArrayList<Integer>();
		ArrayList<Integer> setTwo = new ArrayList<Integer>();
		
		for (int i = values.length - 1; i >= 0; i--) {
			if (calcSum(setOne) <= calcSum(setTwo)) {
				setOne.add(values[i]);
			}
			else {
				setTwo.add(values[i]);
			}
		}
		
		for (Integer i : setOne) {
			message += i + " ";
		}
		
		message += "} { ";
		
		for (Integer i : setTwo) {
			message += i + " ";
		}
		
		message += "}";
		
		System.out.println(message);
	}

	/** method returns string of integer array */
	private String setString(int[] values) {
		String output = "{ " ;
		for (int i : values) {
			output += i + " ";
		}

		// return string with ending bracket
		return output + "} ";
	}
}
