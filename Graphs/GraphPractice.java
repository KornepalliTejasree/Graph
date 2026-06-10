package Graphs;
import java.util.*;
public class GraphPractice {
    public  static class Edge{
        int src;
        int dest;
        Edge(int src,int dest){
            this.src=src;
            this.dest=dest;
        }
    }
    public static void buildGraph(ArrayList<ArrayList<Integer>> graph,int v,int arr[][]){
    for(int i=0;i<v;i++){
        graph.add(new ArrayList<>());
    }
    int j=0;
    for(int[] a:arr){
        for(int i:a){
            graph.get(j).add(i);
        }
        j++;
    }
    }
    public static void BFS(  ArrayList<ArrayList<Integer>> graph,boolean vis[],int curr){
    Queue<Integer> q=new LinkedList<>();
    q.add(curr);
    while(!q.isEmpty()){
       int current=q.poll();
       if(vis[current])continue;
        System.out.println(current+ " ");
        vis[current]=true;
       for(int i=0;i<graph.get(current).size();i++){
       int cur=graph.get(current).get(i);
       if(!vis[cur]){
        q.add(cur);
       }
       }
    }
    }
    public static void DFS(ArrayList<ArrayList<Integer>> graph,boolean vis[],int curr){
        vis[curr]=true;
        System.out.println(curr+" ");
        for(int i=0;i<graph.get(curr).size();i++){
            int dest=graph.get(curr).get(i);
            if(!vis[dest]){
                DFS(graph, vis, dest);
            }
        }
    }
    public static void AllPathsFromSourceToTarget(ArrayList<ArrayList<Integer>> graph,boolean vis[],int src,int target,String path){
        if(src==target){
        System.out.println(path);
        return;
        }
        vis[src]=true;
        for(int i=0;i<graph.get(src).size();i++){
            int dest=graph.get(src).get(i);
            if(!vis[dest]){
                AllPathsFromSourceToTarget(graph, vis, dest, target, path+dest);
            }
        }
        vis[src]=false;
    }
    public static boolean cycleDetection(ArrayList<ArrayList<Integer>> graph,boolean vis[],boolean stack[],int curr){
     vis[curr]=true;
     stack[curr]=true;
     for(int i=0;i<graph.get(curr).size();i++){
        int dest=graph.get(curr).get(i);
        if(!vis[dest]){
            if(cycleDetection(graph, vis, stack, dest)){
                return true;
            }
        }
        else if(stack[dest]){
            return true;
        }
     }
     stack[curr]=false;
     return false;
    }
    public static boolean undirectedCycleDetection(ArrayList<ArrayList<Integer>> graph,int parent,int curr,boolean vis[]){
        vis[curr]=true;
        for(int i=0;i<graph.get(curr).size();i++){
            int dest=graph.get(curr).get(i);
            if(!vis[dest]){
               if(undirectedCycleDetection(graph, curr, dest, vis)){
                return true;
               }
            }
            else if(vis[dest] && parent!=dest){
              return true;
            }
        }
        return false;
    }
    public static void topologicalSorting(ArrayList<ArrayList<Integer>> graph,boolean vis[],int curr,Stack<Integer> st){
        vis[curr]=true;
        for(int i=0;i<graph.get(curr).size();i++){
            int dest=graph.get(curr).get(i);
            if(!vis[dest]){
                topologicalSorting(graph, vis, dest, st);
            }
        }
        st.push(curr);
    }
    public static void main(String args[]){
        int v=5;
        ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
         int[][] arr = {
            {1, 2},
            {0, 3, 4},
            {0, 4},
            {1},
            {1, 2}
        };
        buildGraph(graph, v, arr);
        for(int i=0;i<v;i++){
            System.out.println(graph.get(i));
        }
        System.out.println("BFS");
        BFS(graph, new boolean[v], 0);
        System.out.println("DFS");
        DFS(graph, new boolean[v], 0);
        AllPathsFromSourceToTarget(graph, new boolean[v], 0, 4, "0");
        if(cycleDetection(graph, new boolean[v],new boolean[v], 0)){
            System.out.println("Cycle detected");
        }
        else{
            System.out.println("No cycle detected");
        }
        Stack<Integer> st=new Stack<>();
        topologicalSorting(graph, new boolean[v], 0, st);
        System.out.println("Topological sorting");
        while(!st.isEmpty()){
            System.out.println(st.pop());
        }
    }
}
class Solution {
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
        int n=isConnected.length;
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j)continue;
                else if(isConnected[i][j]==1)graph.get(i).add(j);
            }
        }
        int count=0;
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i]){
                count++;
                BFS(graph,vis,i);
            }
        }
        return count;
    }
    public static void BFS(ArrayList<ArrayList<Integer>> graph,boolean vis[],int curr){
        Queue<Integer> q=new LinkedList<>();
        q.add(curr);
        while(!q.isEmpty()){
            int value=q.remove();
            if(vis[value])continue;
            vis[value]=true;
            for(int i=0;i<graph.get(value).size();i++){
                int num=graph.get(value).get(i);
                if(!vis[num]){
                    BFS(graph,vis,num);
                }
            }
        }
    }
}
class Solutionn {
    static int count=0;
    public int orangesRotting(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean vis[][]=new boolean[m][n];
         count=0;
          Queue<Pair> q=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    vis[i][j]=true;
                   q.add(new Pair(i,j));
                }
            }
        }
        bfs(grid,vis,q);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    return -1;
                }
            }
        }
        return count;
    }
    class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
    public void bfs(int grid[][],boolean vis[][],Queue<Pair> q){
        while(!q.isEmpty()){
            int size=q.size();  
            for(int k=0;k<size;k++){
                Pair curr=q.remove();
                check(curr.i,curr.j,q,vis,grid);
            }
            if(!q.isEmpty()) count++;
        }
         
    }
    public void check(int sr,int sc,Queue<Pair> q,boolean vis[][],int[][] image){

    int m=image.length;
    int n=image[0].length;

    if(sr-1>=0 && !vis[sr-1][sc] && image[sr-1][sc]==1){
        vis[sr-1][sc]=true;
        image[sr-1][sc]=2;
        q.add(new Pair(sr-1,sc));
    }

    if(sr+1<m && !vis[sr+1][sc] && image[sr+1][sc]==1){
        vis[sr+1][sc]=true;
        image[sr+1][sc]=2;
        q.add(new Pair(sr+1,sc));
    }

    if(sc+1<n && !vis[sr][sc+1] && image[sr][sc+1]==1){
        vis[sr][sc+1]=true;
        image[sr][sc+1]=2;
        q.add(new Pair(sr,sc+1));
    }

    if(sc-1>=0 && !vis[sr][sc-1] && image[sr][sc-1]==1){
        vis[sr][sc-1]=true;
        image[sr][sc-1]=2;
        q.add(new Pair(sr,sc-1));
    }
}
}
class Solution1 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor=image[sr][sc];
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(sr,sc));
        boolean vis[][]=new boolean[image.length][image[0].length];
        vis[sr][sc]=true;
        image[sr][sc]=color;
        bfs(image,q,vis,originalColor,color);
        return image;
    }
    class Pair{
      int i;
      int j;
      Pair(int i,int j){
        this.i=i;
        this.j=j;
      }
    }
    public void bfs(int[][] image,Queue<Pair> q,boolean vis[][],int ogColor,int color){
        while(!q.isEmpty()){
            Pair curr=q.poll();
            check(curr.i,curr.j,image,q,vis,ogColor,color);
        }
    }
    public void check(int i,int j,int[][] grid,Queue<Pair> q,boolean vis[][],int ogColor,int color){
        int m=grid.length;
        int n=grid[0].length;
        if(i-1>=0 && grid[i-1][j]==ogColor && !vis[i-1][j]){
        q.add(new Pair(i-1, j));
        vis[i-1][j]=true;
        grid[i-1][j]=color;
       }
       if(i+1<m && grid[i+1][j]==ogColor && !vis[i+1][j]){
        q.add(new Pair(i+1, j));
        vis[i+1][j]=true;
        grid[i+1][j]=color;
       }
       if(j-1>=0 && grid[i][j-1]==ogColor && !vis[i][j-1]){
        q.add(new Pair(i, j-1));
        vis[i][j-1]=true;
        grid[i][j-1]=color;
       }
       if(j+1<n && grid[i][j+1]==ogColor && !vis[i][j+1]){
        q.add(new Pair(i, j+1));
        vis[i][j+1]=true;
        grid[i][j+1]=color;
       }
    }
    }
class Solutiion {
    public boolean isCycle(int V, List<Integer>[] adj) {
        return cycle(adj,new boolean[V],0,-1);
    }
    public boolean cycle(List<Integer>[] adj,boolean vis[],int curr,int parent){
        vis[curr]=true;
        for(int i=0;i<adj[curr].size();i++){
            int dest=adj[curr].get(i);
            if(vis[dest] && parent!=dest){
                return true;
            }
            if(!vis[dest]){
                if(cycle(adj,vis,dest,curr)){
                    return true;
                }
            }
        }
        return false;
    }
}
class Soolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] arr:prerequisites){
            int i=arr[1];
            int j=arr[0];
            graph.get(i).add(j);
        }
    boolean vis[] = new boolean[numCourses];
    boolean stack[] = new boolean[numCourses];

        for(int i = 0; i < numCourses; i++){
            if(!vis[i]){
                if(cycleDirected(graph, vis, i, stack)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean cycleDirected(ArrayList<ArrayList<Integer>> graph,boolean vis[],int curr,boolean stack[]){
       vis[curr]=true;
       stack[curr]=true;
       for(int i=0;i<graph.get(curr).size();i++){
        int dest=graph.get(curr).get(i);
        if(!vis[dest]){
            if(cycleDirected(graph,vis,dest,stack)){
                return true;
            }
        }
        if(stack[dest]){
            return true;
        }
       }
       stack[curr]=false;
       return false;
    }
}