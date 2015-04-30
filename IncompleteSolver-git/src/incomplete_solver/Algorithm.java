package incomplete_solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import incomplete_solver.Instance;
import incomplete_solver.Solution;
import incomplete_solver.Util;

public class Algorithm {

	private Random rand;
	protected Instance instance;
	
	private long tabuTenure;
	private long[] tabuList;
	
	private long time_limit = 5000;
	
	
	public Algorithm(Instance ins, Random r) {
		instance = ins;
		rand = r;
						
		int numValiable = instance.getNumValiables();
		
		tabuTenure = (long)(numValiable * 0.1);
		tabuList = new long[numValiable];
		for (int i=0; i<numValiable; i++) {
			tabuList[i] = Long.MIN_VALUE/2;
		}
	}
	
	public void setTabuTenureRatio(double r) {
		tabuTenure = (long)(instance.getNumValiables() * r);
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
				if (numIteration - tabuList[index] > tabuTenure) {
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
			
			tabuList[flipIndex] = numIteration;
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
