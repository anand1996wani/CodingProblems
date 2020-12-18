/*

MinimumSubsetSumDifference_DP_Topdown

Given an integer array arr of size N, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum and find the minimum difference

Example 1:

Input: N = 4, arr[] = {1, 6, 11, 5} 
Output: 1
Explanation: 
Subset1 = {1, 5, 6}, sum of Subset1 = 12 
Subset2 = {11}, sum of Subset2 = 11   
Example 2:
Input: N = 2, arr[] = {1, 4}
Output: 3
Explanation: 
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {4}, sum of Subset2 = 4

Your Task:  
You don't need to read input or print anything. Complete the function minDifference() which takes N and array arr as input parameters and returns the integer value

Expected Time Complexity: O(N*|sum of array elements|)
Expected Auxiliary Space: O(N*|sum of array elements|)

Constraints:
1 ≤ N*|sum of array elements| ≤ 106


https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/


*/

class Solution
{

	public int minDiffernce(int arr[], int N) 
	{ 
	    // Your code goes here
	    int sum = 0;
	    for(int i = 0;i < N;i++){
	        sum = sum + arr[i];
	    }
	    
	    int S = sum / 2;
	    
	    boolean DP[][] = new boolean[N + 1][S + 1];
	    
	    for(int i = 0; i < N + 1; i++){
	        DP[i][0] = true;
	    }
	    
	    for(int j = 1; j < S + 1;j++){
	        DP[0][j] = false;
	    }
	    
	    for(int i = 1; i < N + 1;i++){
	        for(int j = 1;j < S + 1;j++){
	            if(arr[i - 1] <= j){
	                DP[i][j] = DP[i - 1][j - arr[i - 1]] | DP[i - 1][j];
	            }else{
	                DP[i][j] = DP[i - 1][j];
	            }
	        }
	    }
	    
	    for(int i = S;i >= 0;i--){
	        if(DP[N][i]){
	            int firstN = i;
	            int secondN = sum - i;
	            return Math.abs(secondN - firstN);
	        }
	    }
	    
	    return 0;
	} 
}
