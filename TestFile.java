import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/*
 * The following class creates a text file named 
 * run.txt for testing Partition.java
 * using input redirection.
 *@author Paul Hood
 *@version 04/16/2014
 */
public class TestFile {

	public static void main(String[] args) {
		Random rand = new Random();

		int size = rand.nextInt(31) + 1;
		PrintWriter out = null;
		try {

			// create new file
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(new File("run.txt"))));
			
			out.println(size);
			for (int i = 0; i < size; i++) {
			    out.println(rand.nextInt(50) + 1);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}


		// close PrintWriter
		out.close(); 
	}
}
