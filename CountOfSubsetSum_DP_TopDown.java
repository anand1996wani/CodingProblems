/*

Count Of Subset Sum

Count of subsets with sum equal to X
Last Updated: 05-11-2019
Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.

Examples:

Input: arr[] = {1, 2, 3, 3}, X = 6
Output: 3
All the possible subsets are {1, 2, 3},
{1, 2, 3} and {3, 3}

Input: arr[] = {1, 1, 1, 1}, X = 1
Output: 4


*/

/*package whatever //do not write package name here */

import java.io.*;

class GFG {
    
    int countSubsetSum(int array[],int S,int N){
        int DP[][] = new int[N + 1][S + 1];
        
        for(int i = 0;i < N + 1;i++){
            DP[i][0] = 1;
        }
        
        for(int j = 1; j < S + 1;j++){
            DP[0][j] = 0;    
        }
        
        for(int i = 1; i < N + 1;i++){
            for(int j = 1; j < S + 1;j++){
                if(array[i - 1] <= j){
                    DP[i][j] = DP[i - 1][j - array[i - 1]] + DP[i - 1][j];
                }else{
                    DP[i][j] = DP[i - 1][j];
                }
            }
        }
        
        return DP[N][S];
        
    }
    
	public static void main (String[] args) {
		System.out.println("GfG!");
		
		int array[]= new int[6];
		array[0] = 2;
		array[1] = 3;
		array[2] = 5;
		array[3] = 6;
		array[4] = 8;
		array[5] = 10;
		
		int sum = 10;
		
		GFG gfg = new GFG();
		int count = gfg.countSubsetSum(array,sum,6);
		System.out.println(count);
		
	}
}
