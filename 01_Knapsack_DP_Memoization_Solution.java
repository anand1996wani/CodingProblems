/*

Classic 0/1 Knapsack

This is the classic & famous knapsack problem : You are a thief carrying a single knapsack with limited (1 <= S <= 2000) capacity. The museum you stole had (1 <= N <= 2000) artifact that you could steal. Unfortunately you might not be able to steal all the artifact because of your limited knapsack capacity.

You have to cherry pick the artifact in order to maximize the total value (<= 2000000) of the artifacts you stole.

Input Format

On the first line you are given T as the test cases available (1 <= T <= 20). The next T testcases will started with two integer S and N. N lines follow with two integers on each line describing each artifact available at the museum. The first number is the weight of the artifact and the next is the value of the artifact.

Output Format

You should output a single integer on one line : the total maximum value from the best choice of artifacts you stole.

Sample Input

1
4 5
1 8
2 4
3 0
2 5
2 3

Sample Output

13


Memoization DP Solution

*/

import java.io.*;
import java.util.*;

public class Solution {

    static int dp[][];
    
    public Solution(){
        dp = new int[2001][2001];
        for(int i = 0;i < 2001;i++){
            for(int j = 1;j < 2001;j++){
                dp[i][j] = -1;
            }
        }
    }
    
    int MemoizeKnapsack(int weightArray[], int valueArray[], int S, int N){
        if(S == 0 || N == 0){
            return 0;
        }else {
            if(weightArray[N - 1] <= S){
                if(this.dp[N][S] != -1){
                    return this.dp[N][S];
                } else{
                    dp[N][S] = Math.max(valueArray[N - 1] + this.MemoizeKnapsack(weightArray,valueArray, S - weightArray[N - 1],N - 1), this.MemoizeKnapsack(weightArray,valueArray, S, N - 1));
                    return dp[N][S];
                }
            } else{
                if(this.dp[N][S] != -1){
                    return this.dp[N][S];
                }else{
                    dp[N][S] = this.MemoizeKnapsack(weightArray,valueArray,S,N - 1);
                    return dp[N][S];
                }    
            }
        }
    }
    
    int RecursiveKnapsack(int weightArray[], int valueArray[], int S, int N){
        if(S == 0 || N == 0){
            return 0;
        }else{
            if(weightArray[N - 1] <= S){
                return Math.max(valueArray[N - 1] + this.RecursiveKnapsack(weightArray,valueArray,S - weightArray[N - 1],N - 1), this.RecursiveKnapsack(weightArray,valueArray,S,N - 1));
            }else{
                return this.RecursiveKnapsack(weightArray,valueArray,S,N - 1);
            }
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int c = 0;c < T;c++){
            int S = scanner.nextInt();
            int N = scanner.nextInt();
            int weightArray[] = new int[N];
            int valueArray[] = new int[N];
            for(int i = 0; i < N;i++){
                weightArray[i] = scanner.nextInt();
                valueArray[i] = scanner.nextInt();
            }
            Solution solution = new Solution();
            //int maxProfit = solution.RecursiveKnapsack(weightArray,valueArray,S,N);
            int maxProfit = solution.MemoizeKnapsack(weightArray,valueArray,S,N);
            System.out.println(maxProfit);
        }
    }
}
