/*

https://leetcode.com/problems/course-schedule-ii/

There are a total of n courses you have to take labelled from 0 to n - 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.

Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.


*/

class Graph {
    int nodes;
    ArrayList<ArrayList<Integer>> graph;
    boolean visited[];
    boolean stackVisited[];
    Stack<Integer> myStack = new Stack<Integer>(); 
    
    public Graph(int n){
        this.nodes = n;
        this.visited = new boolean[n];
        this.stackVisited = new boolean[n];
        this.graph = new ArrayList<ArrayList<Integer>>(n);
        for(int i = 0;i < n;i++){
            graph.add(i, new ArrayList<Integer>());
            //visited[i] = false;
            //stackVisited[i] = false;
        }
    }
    
    public void addEdge(int a, int b){
        this.graph.get(a).add(b);
    }
    
    public boolean cycleCheck(){
        for(int i = 0;i < this.nodes;i++){
            if(this.dfs(i)){
                return true;
            }
        }
        return false;
    }
    
    public boolean dfs(int index){
        if(this.stackVisited[index]){
            return true;
        }
        
        if(this.visited[index]){
            return false;
        }
        
        this.visited[index] = true;
        this.stackVisited[index] = true;
        
        for(int v : this.graph.get(index)){
                if(this.dfs(v)){
                    return true;
                }
        }
        
        this.stackVisited[index] = false;
        return false;
        
    }
    
    public void myDFS(int index, boolean visited[]){
        visited[index] = true;
        for(int v : this.graph.get(index)){
            if(visited[v] == false)
                this.myDFS(v, visited);
        }
        this.myStack.push(index);
    }
}

class Solution {
    
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        
        for(int i = 0; i < prerequisites.length;i++){
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        
        int b[] = {};
        //Cycle Detectionx
        boolean test = graph.cycleCheck();
        if(test){
            return b;
        }else{
            boolean visited[] = new boolean[numCourses];
            
            for(int i = 0;i < numCourses;i++){
                visited[i] = false;     
            }
            
            for(int i = 0;i < numCourses;i++){
                if(visited[i] == false){
                    graph.myDFS(i, visited);
                }
            }
            int c[] = new int[graph.myStack.size()];
            int j = 0;
            while(graph.myStack.size() > 0){
                System.out.println(graph.myStack.peek());
                c[j++] = graph.myStack.pop();
            }
            return c;    
        }
    }
}
