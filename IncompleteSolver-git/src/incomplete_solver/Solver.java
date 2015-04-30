/**
 * @authors Thomas Sullivan && Hawiar Hussein
 * @version 04.30.15
 */

package incomplete_solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import incomplete_solver.Instance;
import incomplete_solver.Solution;
import incomplete_solver.Util;

public class Solver {

	private Random rand;
	protected Instance instance;
	
	private long num;
	private long[] list;
	
	private long time_limit = 600000;
	
	
	public Solver(Instance ins, Random r) {
		instance = ins;
		rand = r;
						
		int numValiable = instance.getNumValiables();
		
		num = (long)(numValiable * 0.1);
		list = new long[numValiable];
		for (int i=0; i<numValiable; i++) {
			list[i] = Long.MIN_VALUE/2;
		}
	}

	
	public String start() {
		long sTime = System.currentTimeMillis();
		
		long numIteration = 0;
		
		boolean[] current = Util.getRandomAssignment(instance.getNumValiables(), rand);
		long currentUV = instance.getUnsatisfiedValue(current);
		Solution incumbent = new Solution(Arrays.copyOf(current, current.length), currentUV);
		System.out.println("o "+currentUV);
		
		while(System.currentTimeMillis() - sTime < time_limit) {
			long bestDiff = Long.MAX_VALUE;
			List<Integer> bestIndices = new ArrayList<Integer>();
			for (int index=0; index<instance.getNumValiables(); index++) {
				if (numIteration - list[index] > num) {
					long diff = instance.getFlipDifference(current, index);
					if (bestDiff > diff) {
						bestDiff = diff;
						bestIndices.clear();
						bestIndices.add(index);
					} else if (bestDiff == diff) {
						bestIndices.add(index);
					}
				}
			}
			int flipIndex = bestIndices.get(rand.nextInt(bestIndices.size()));
			current[flipIndex] = !current[flipIndex];
			currentUV += bestDiff;

			if (currentUV < incumbent.unsatisfiedValue) {
				incumbent = new Solution(Arrays.copyOf(current, current.length), currentUV);
				System.out.println("o "+currentUV);
			}
			
			list[flipIndex] = numIteration;
			numIteration += 1;
			if(currentUV == 0){
				Util.printValues(incumbent.assignment);
				long endTime = System.currentTimeMillis();
				System.out.println(endTime - sTime + " ms");
				System.exit(0);
			}
		}
		System.out.println("Solution not found.");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - sTime + " ms");
		System.exit(0);
		return "Solution not found";
		
	}

}
