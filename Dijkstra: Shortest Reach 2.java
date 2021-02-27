/*

Dijkstra: Shortest Reach 2

https://www.hackerrank.com/challenges/dijkstrashortreach/problem

Given an undirected graph and a starting node, determine the lengths of the shortest paths from the starting node to all other nodes in the graph. If a node is unreachable, its distance is -1. Nodes will be numbered consecutively from  to , and edges will have varying distances or lengths.

For example, consider the following graph of 5 nodes:

Function Description

Complete the shortestReach function in the editor below. It should return an array of integers that represent the shortest distance to each node from the start node in ascending order of node number.

shortestReach has the following parameter(s):

n: the number of nodes in the graph
edges: a 2D array of integers where each  consists of three integers that represent the start and end nodes of an edge, followed by its length
s: the start node number
Input Format

The first line contains , the number of test cases.

Each test case is as follows:
- The first line contains two space-separated integers  and , the number of nodes and edges in the graph.
- Each of the next  lines contains three space-separated integers , , and , the beginning and ending nodes of an edge, and the length of the edge.
- The last line of each test case has an integer , denoting the starting position.

Constraints






If there are edges between the same pair of nodes with different weights, they are to be considered as is, like multiple edges.

Output Format

For each of the  test cases, print a single line consisting  space separated integers denoting the shortest distance to the  nodes from starting position  in increasing order of their labels, excluding .

For unreachable nodes, print .

Sample Input

1
4 4
1 2 24
1 4 20
3 1 3
4 3 12
1
Sample Output

24 3 15

*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the shortestReach function below.
    static int findMin(int distance[], boolean visited[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0;i < distance.length;i++){
            if(!visited[i]){
                if(distance[i] <= min){
                    min = distance[i];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }
    static int[] shortestReach(int n, int[][] edges, int s) {
        int graph[][] = new int[n][n];
        for(int i = 0;i < edges.length;i++){
            if(graph[edges[i][0] - 1][edges[i][1] - 1] == 0){
                graph[edges[i][0] - 1][edges[i][1] - 1] = edges[i][2];
                graph[edges[i][1] - 1][edges[i][0] - 1] = edges[i][2];
            }else{
                graph[edges[i][0] - 1][edges[i][1] - 1] = Math.min(graph[edges[i][0] - 1][edges[i][1] - 1], edges[i][2]);
                graph[edges[i][1] - 1][edges[i][0] - 1] = Math.min(graph[edges[i][0] - 1][edges[i][1] - 1], edges[i][2]);
            }
        }
        /*for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }*/
        
        int distance[] = new int[n];
        boolean visited[] = new boolean[n];
        for(int i = 0;i < n;i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;    
        }
        
        distance[s - 1] = 0;
        //int u = s - 1;
        
        for(int count = 0 ; count < n - 1 ; count++){
            int u = findMin(distance, visited);
            //System.out.print(u + " ");
            //if(u == -1)
                //break;
            visited[u] = true;
            for(int v = 0;v < n;v++){
                if(!visited[v] && graph[u][v] != 0 && (distance[u] != Integer.MAX_VALUE)){
                    if(distance[v]  > (distance[u] + graph[u][v])){
                        distance[v] = distance[u] + graph[u][v];
                    }
                }
            }
        }
        
        for(int i = 0;i < n;i++){
            //System.out.print(distance[i]);
            if(distance[i] == Integer.MAX_VALUE){
                distance[i] = -1;
            }
            //System.out.print(distance[i]);
        }
        return distance;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][3];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 3; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = shortestReach(n, edges, s);

            for (int i = 0; i < result.length; i++) {
                if(i != (s-1)){
                    bufferedWriter.write(String.valueOf(result[i]));

                    if (i != result.length - 1) {
                        bufferedWriter.write(" ");
                    }
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
