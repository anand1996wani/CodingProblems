/*

https://www.hackerrank.com/challenges/unbounded-knapsack/problem

Given an array of integers and a target sum, determine the sum nearest to but not exceeding the target that can be created. To create the sum, use any element of your array zero or more times.

For example, if  and your target sum is , you might select  or . In this case, you can arrive at exactly the target.

Function Description

Complete the unboundedKnapsack function in the editor below. It must return an integer that represents the sum nearest to without exceeding the target value.

unboundedKnapsack has the following parameter(s):

k: an integer
arr: an array of integers
Input Format

The first line contains an integer , the number of test cases.

Each of the next  pairs of lines are as follows:
- The first line contains two integers  and , the length of  and the target sum.
- The second line contains  space separated integers .

Constraints



Output Format

Print the maximum sum for each test case which is as near as possible, but not exceeding, to the target sum on a separate line.

Sample Input

2
3 12
1 6 9
5 9
3 4 4 4 8
Sample Output

12
9
Explanation

In the first test case, one can pick {6, 6}. In the second, we can pick {3,3,3}.

*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the unboundedKnapsack function below.
    static int unboundedKnapsack(int k, int[] arr) {
        int N = arr.length;
        boolean DP[][] = new boolean[N + 1][k + 1];
        for(int i = 0;i < N +1;i++){
            DP[i][0] = true;
        }
        for(int j = 1;j < k + 1;j++){
            DP[0][j] = false;
        }
        for(int i = 1; i < N + 1;i++){
            for(int j = 1;j < k + 1;j++){
                if(arr[i - 1] <= j){
                    DP[i][j] = DP[i][j - arr[i - 1]] | DP[i - 1][j];
                }else{
                    DP[i][j] = DP[i - 1][j];
                }
            }
        }
        int ret = 0;
        for(int i = k;i >= 0;i--){
            if(DP[N][i]){
                ret = i;
                break;
            }
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for(int c = 0;c < t;c++){
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int result = unboundedKnapsack(k, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
