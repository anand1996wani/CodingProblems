/*

Count the number of subset with a given difference





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
		
		int array[]= new int[4];
		array[0] = 1;
		array[1] = 1;
		array[2] = 2;
		array[3] = 3;
		
		int diff = 1;
		
		int sum = 0;
		for(int i = 0;i < array.length;i++){
		    sum = sum + array[i];
		}
		
		int c = sum + diff;
		
		if(c%2 == 0){
		    int S = c/2;
		    GFG gfg = new GFG();
		    int count = gfg.countSubsetSum(array,S,array.length);
		    System.out.println(count);    
		}else{
		    System.out.println(0);
		}
		
	}
}
