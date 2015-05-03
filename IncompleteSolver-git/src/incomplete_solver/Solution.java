/**
 * The solution class is used to generate the solution to the file. Used in the solver class,
 * an instance of this class is used to keep track of the current (incumbent variable in solver
 * class) values that could potentially solve the equation.
 * 
 * @authors Thomas Sullivan && Hawiar Hussein
 * @version 04.30.15
 */

package incomplete_solver;

import java.util.Arrays;

public class Solution {//implements Comparable<Solution> {
	public boolean[] assignment;
	public long unsatisfiedValue;
	
	public Solution(boolean[] a, long uv) {
		assignment = a;
		unsatisfiedValue = uv;
	}
	
	public Solution(Solution src) {
		assignment = Arrays.copyOf(src.assignment, src.assignment.length);
		unsatisfiedValue = src.unsatisfiedValue;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(assignment);
	}
	
	@Override
	public boolean equals(Object s) {
		if (s instanceof Solution) {
			Solution sol = (Solution)s;
			return  unsatisfiedValue == sol.unsatisfiedValue && Arrays.equals(assignment, sol.assignment) ;
		}
		return false;
	}

	private int compare(boolean[] array1, boolean[] array2) {
		for (int i=0; i<array1.length; i++) {
			if (array1[i] == false && array2[i] == true) {
				return -1;
			} else if (array1[i] == true && array2[i] == false){
				return 1;
			}
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(assignment)+"\n"+unsatisfiedValue;
	}

}
