/*

Dijkstra: Shortest Reach 2

https://www.hackerrank.com/challenges/dijkstrashortreach/problem

Given an undirected graph and a starting node, determine the lengths of the shortest paths from the starting node to all other nodes in the graph. If a node is unreachable, its distance is -1. Nodes will be numbered consecutively from  to , and edges will have varying distances or lengths.

For example, consider the following graph of 5 nodes:


*/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public Node(){
        
    }
    
    public Node(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    
    @Override
    public int compare(Node node1, Node node2){
        if (node1.cost < node2.cost){
            return -1;
        }
        if (node1.cost > node2.cost){
            return 1;
        }
        return 0;
    }
    
}

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
    
    static int[] shortestReachMinHeap(int n, int [][] edges, int s){
        ArrayList<List<Node>> listOfMaps = new ArrayList<List<Node>>(n);
        for(int i = 0;i < n;i++){
            listOfMaps.add(i, new LinkedList<Node>());
        }
        //List<Map<Integer, Integer>> listOfMaps = new ArrayList<HashMap<Integer, Integer>>(n);
        for(int j = 0;j < edges.length;j++){
            listOfMaps.get(edges[j][0] - 1).add(new Node(edges[j][1] - 1, edges[j][2]));
            listOfMaps.get(edges[j][1] - 1).add(new Node(edges[j][0] - 1, edges[j][2]));
        }
        Set visited = new HashSet<Integer>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>(n, new Node());
        int dist[] = new int[n];
        for(int i = 0;i < n;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s - 1] = 0;
        pq.add(new Node(s - 1, 0));
        while(pq.size() > 0){
            int u = pq.remove().node;
            //System.out.println(u);
            visited.add(u);
            
            for(Node currNode : listOfMaps.get(u)){
                if(!visited.contains(currNode.node)){
                    if(dist[currNode.node] > (dist[u] + currNode.cost)){
                        dist[currNode.node] = dist[u] + currNode.cost;
                        pq.add(new Node(currNode.node, dist[currNode.node]));
                    }
                }
            }
            
        }
        for(int i = 0;i < n;i++){
            if(dist[i] == Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;              
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

            int[] result = shortestReachMinHeap(n, edges, s);

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
