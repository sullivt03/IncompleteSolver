/**
 * Simple driver class that reads in the cnf file for solving. Creates a solver instance
 * and is used to call solver.start() to start the solver.
 * 
 * @authors Thomas Sullivan && Hawiar Hussein
 * @version 04.30.15
 */

package incomplete_solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {

		
		String filename = "s28.cnf";
		Instance instance = null;
			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				List<String> data = new LinkedList<String>();				
				String line = br.readLine();
				while (line != null) {
					data.add(line);
					line = br.readLine();
				}				
				instance = Instance.makeInstance(data);
				br.close();
			} catch (IOException e) {
				System.out.println("IOException");
			}

		
		Random random = new Random(1);
		Solver solver = new Solver(instance, random);
		solver.start();
		

	}
}