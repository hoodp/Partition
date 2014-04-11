import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partition {

	/** 2d boolean array */
	private boolean[][] partition;

	/** max number of rows for boolean array */
	private int maxRows;

	/** max number of cols in boolean array */
	private int maxCols;

	/** sum of the set */
	private int sum;

	/** string that keeps track of original input */
	private static String input = "{ ";

	public static void main(String[] args) {

		try {
			// scanner for reading input
			Scanner scan = new Scanner(System.in);

			System.out.print("Size: ");

			// get size of the array
			int size = scan.nextInt();

			if (size > 31 || size < 2) {
				scan.close();
				throw new IndexOutOfBoundsException("Size" +
						" must be < 31 and > 2.");
			}

			// new array based on first input
			int[] values = new int[size];

			// loop fills every value in the array
			for (int i = 0; i < values.length; i++) {
				String line = "Value[" + i +  "]: ";
				System.out.print(line);
				int inValue = scan.nextInt();
				values[i] = inValue;
				input += inValue + " ";
			}

			input += "}";
			// close the scanner
			scan.close();

			new Partition(values);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Error: " + e);
		}
	}

	public Partition(int[] set) {

		// calculate the sum of the array
		sum = setSum(set);

		// max number of rows for 2d boolean array
		maxRows = sum / 2 + 1;

		// max number of columns for 2d boolean array
		maxCols = set.length + 1;

		// if sum is even fill the boolean array
		if (validSum(sum)) {

			// fill the partition boolean
			partition = fillTable(set);

			// check if partition is valid
			if (validPartition()) {

				// partition is valid - display result
				output(set);

				displayResults();
			}
			else {

				// cannot be partitioned
				output();
			}
		}

		// sum of the array is not even
		else {
			output();
		}
	}

	/** method fills the 2d boolean array */
	private boolean[][] fillTable(int[] set) {

		// boolean array of possible values
		boolean part[][] = new boolean[maxRows][maxCols];

		// set the first row to true
		for (int i = 0; i < maxCols; i++) {

			// first row
			part[0][i] = true;
		}

		// fill rest of the array
		for (int i = 1; i < maxRows; i++) {
			for (int j = 1; j < maxCols; j++) {

				// equal to previous column
				part[i][j] = part[i][j - 1];

				// if part[i][j] is true, set rest of row true
				if (part[i][j]) {
					part =  fillRow(part, i, j, maxCols);

					// break to next row
					break;
				}

				// check if i is greater than previous number in array
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

		return part;
	}

	/** method checks if the last row in the last column is true */
	private boolean validPartition() {
		return partition[maxRows - 1][maxCols - 1];
	}

	/** method fills rest of row to true */
	private boolean [][] fillRow(boolean[][] part, int row, int col,
			int maxCol) {
		for (int i = col; i < maxCol; i++) {
			part[row][i] = true;
		}

		// return updated array
		return part;
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
		String message = "\nInput: " + input + " \n";
		message = "Output: False\nThere is no satisfying" +
				" partition.";
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

		String message = "\nInput: " + input + "\nOutput: True { ";

		// sort the values in the array
		Arrays.sort(values);

		ArrayList<Integer> setOne = new ArrayList<Integer>();
		ArrayList<Integer> setTwo = new ArrayList<Integer>();

		// add numbers in descending order
		for (int i = values.length - 1; i >= 0; i--) {

			// add to first set if array is equal or less than second
			if (calcSum(setOne) <= calcSum(setTwo)) {
				setOne.add(values[i]);
			}

			// first array's sum is greater than seconds
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

	/** method displays the boolean array */
	private void displayResults() {
		String display = "";

		int rowLength = partition.length;
		int colLength = partition[0].length;

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				display += partition[i][j] + "\t";
			}
			display += "\n";
		}
		System.out.println(display);
	}
}
