/**
 * Util class is used to print out the solution is a certain way. Since the array in the solver is 
 * stored with true or false values, the printValues() method prints out the true vales as just the
 * variable and the false values with a negative in front in the final solution. The getRandomAssignment()
 * method generates a random starting point to try to solve the equation.
 * 
 * @authors Thomas Sullivan && Hawiar Hussein
 * @version 04.30.15
 */

package incomplete_solver;

import java.util.Random;

public class Util {
	
	public static boolean[] getRandomAssignment(int numVariable, Random rand) {
		boolean[] ret = new boolean[numVariable];
		for (int i=0; i<numVariable; i++) {
			ret[i] = rand.nextBoolean();
		}
		return ret;
	}
	
	public static void printValues(boolean[] values) {
		System.out.print("Solution: ");
		for (int i=0; i<values.length; i++) {
			int x = i+1;
			if (values[i] == false) {
				x = -x;
			}
			System.out.print(x+" ");
		}
		System.out.println();
	}
}
