/*

Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum. 

https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

Example: 

Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output: True  
There is a subset (4, 5) with sum 9.

Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
Output: False
There is no subset that add up to 30.


*/

/*package whatever //do not write package name here */

import java.io.*;

class GFG {
	
	boolean isSubsetSum_DP_TopDown(int array[], int S,int N){
	    
	    boolean DP[][] = new boolean[N + 1][S + 1];
	    for(int i = 0; i < N + 1;i++){
	        DP[i][0] = true;
	    }
	    for(int j = 1; j < S + 1;j++){
	        DP[0][j] = false;
	    }
	    for(int i = 1; i < N + 1;i++){
	        for(int j = 1;j < S + 1;j++){
	            if(array[i -1] < j){
	                DP[i][j] = DP[i - 1][j - array[i - 1]] || DP[i - 1][j];
	            }else if(array[i - 1] == j){
	                DP[i][j] = true;
	            }else{
	                DP[i][j] = DP[i - 1][j];
	            }
	        }    
	    }
	    return DP[N][S];
	}
	
	boolean isSubsetSum(int array[], int S, int N){
	    if(S == 0){
	        return true;
	    }
	    if (N == 0){
	        return false;
	    }
	    if (array[N - 1] < S){
	       return this.isSubsetSum(array, S - array[N - 1], N - 1) || this.isSubsetSum(array, S, N - 1); 
	    }else if (array[N - 1] == S){
	        return true;
	    }else{
	        return this.isSubsetSum(array,S,N - 1);
	    }
	}
	
	public static void main (String[] args) {
		
		int array[] = new int[6];
		array[0] = 3;
		array[1] = 34;
		array[2] = 4;
		array[3] = 12;
		array[4] = 5;
		array[5] = 2;
		int sum = 9;
		
		GFG gfg = new GFG();
		//boolean ret = gfg.isSubsetSumRecursive(array,sum,6);
		boolean ret = gfg.isSubsetSum_DP_TopDown(array,sum,6);
		if(ret){
		    System.out.println("True");
		}else{
		    System.out.println("False");
		}
		
	}
}
