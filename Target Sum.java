/*

Target Sum

https://leetcode.com/problems/target-sum/

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
 

Constraints:

The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

class Solution {
    
    public int CountSubsetSum(int[] array,int S, int N){
        
        int DP[][] = new int[N + 1][S + 1];
        for(int i = 0;i < N + 1;i++){
            DP[i][0] = 1;
        }
        for(int j = 1; j < S + 1;j++){
            DP[0][j] = 0;
        }
        
        for(int i = 1;i < N + 1;i++){
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
    
    public int findTargetSumWays(int[] nums, int diff) {
        int sum = 0;
        int N = nums.length;
        int zeroC = 0;
        for(int i = 0;i < N;i++){
            if(nums[i] == 0)
                zeroC = zeroC + 1;
            sum = sum + nums[i];    
        }
        int c = sum + diff;
        int newN = N - zeroC;
        int array[] = new int[newN];
        int j = 0;
        
        for(int i = 0;i < N;i++){
            if(nums[i] != 0)
                array[j++] = nums[i];   
        }
        //System.out.println(zeroC);
        if(c%2 == 0 && sum >= diff){
            int S = c/2;
            Solution solution = new Solution();
            int  p = solution.CountSubsetSum(array,S,array.length);
            System.out.println(p);
            if(zeroC == 0)
                return p;
            else
                return ((int)Math.pow(2,zeroC))*p;
        }else{
            //System.out.println(0);
            return 0;
        }
        
    }
}
