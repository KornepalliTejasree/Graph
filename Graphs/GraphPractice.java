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
