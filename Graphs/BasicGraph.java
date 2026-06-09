package Graphs;
import java.util.*;
public class BasicGraph {
    static class edge{
        int src;
        int dest;
        edge(int src,int dest){
            this.src=src;
            this.dest=dest;
        }
    }
    public static void buildgraph(ArrayList<edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<edge>();
        }
        graph[0].add(new edge(0, 2));
        graph[1].add(new edge(1, 2));
        graph[1].add(new edge(1, 3));
        graph[2].add(new edge(2, 0));
        graph[2].add(new edge(2, 1));
        graph[2].add(new edge(2, 3));
        graph[3].add(new edge(3,1));
        graph[3].add(new edge(3,2));
    }
    public static void bfs(ArrayList<edge> graph[],boolean vis[],int src){
        System.out.println("dfs");
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){
                System.out.print(curr+" ");
                vis[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    edge e=graph[curr].get(i);
                    if(!vis[e.dest]){
                        q.add(e.dest);
                    }
                }
            }
        }
    }
    public static void dfs(ArrayList<edge> graph[],boolean vis[],int curr){
        if(vis[curr]){
            return;
        }
        vis[curr]=true;
        System.out.print(curr+" ");
        for(int i=0;i<graph[curr].size();i++){
            edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                dfs(graph, vis, e.dest);
            }
        }
    }
    public static void AllPathsFromSrcToTarget(int src,int target,ArrayList<edge> graph[],boolean vis[],String path){
        if(src==target){
            System.out.println(path+target);
            return;
        }
        if(vis[src]){
           return;
        }
        vis[src]=true;
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            if(!vis[e.dest]){
                AllPathsFromSrcToTarget(e.dest, target, graph, vis, path+src);
            }
        }
        vis[src]=false;
    }
    public static boolean cycleDetectionDAG(ArrayList<edge> graph[],boolean vis[],boolean stack[],int curr){
        
        vis[curr]=true;
        stack[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            edge e=graph[curr].get(i);
            if(stack[curr]){
            return true;
            }
            if(!vis[e.dest]){
                if(cycleDetectionDAG(graph, vis, stack, e.dest)){
                    return true;
                }
            }
        }
        stack[curr]=false;
        return false;
    }
    public static void topologicalSorting(ArrayList<edge> graph[],boolean vis[],Stack<Integer> st,int curr){
       if(vis[curr]){
        return;
       }
       vis[curr]=true;
       for(int i=0;i<graph[curr].size();i++){
        edge e=graph[curr].get(i);
        if(!vis[e.dest]){
            topologicalSorting(graph, vis, st, e.dest);
        }
       }
       st.push(curr);
    }
    public static boolean cycleDetectionUndirected(ArrayList<edge> graph[],int parent,int curr,boolean vis[]){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            edge e=graph[curr].get(i);
            if(vis[e.dest] && parent!=e.dest){
                return true;
            }
            if (!vis[e.dest]) {
                if(cycleDetectionUndirected(graph, curr, e.dest, vis)){
                   return true;
                }
            }
        }
        return false;
    }
    static class Pair implements Comparable<Pair>{
        int src;
        int wt;
        Pair(int src,int wt){
            this.src=src;
            this.wt=wt;
        }
        
        @Override
        public int compareTo(Pair o) {
            return this.wt-o.wt;
        }
    }
    public static void main(String[] args) {
        int n=4;
       ArrayList<edge> graph[]=new ArrayList[n];
        buildgraph(graph);
        for(int i=0;i<graph[2].size();i++){
            edge e=graph[2].get(i);
            System.out.println(e.dest+" ");
        }
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(graph, vis, i);
            }
        }
       System.out.println();
       bfs(graph, new boolean[n], 0);
       AllPathsFromSrcToTarget(0, 3, graph, new boolean[n], "");
       if(cycleDetectionDAG(graph, new boolean[n], new boolean[n], 0)){
        System.out.println(true);
       }
       else{
        System.out.println(false);
       }
       Stack<Integer> st=new Stack<>();
       topologicalSorting(graph, new boolean[n], st, 0);
       while(!st.isEmpty()){
        System.out.print(st.pop()+" ");
       }
       
    }
}
class Solution {
    public void solve(char[][] board) {
        int m=board.length;
        int n=board[0].length;
        int j=0;
        int i=0;
        boolean vis[][]=new boolean[m][n];
        //same column and row changes
        for( i=0;i<m;i++){
            if(board[i][j]=='O' && !vis[i][j]){
                bfs(board,i,j,vis);
            }
        }
        //same row and column changes
        i=0;
        for(j=0;j<n;j++){
             if(board[i][j]=='O' && !vis[i][j]){
                bfs(board,i,j,vis);
            }
        }
        //last row and column changes
        i=m-1;
         for(j=0;j<n;j++){
             if(board[i][j]=='O' && !vis[i][j]){
                bfs(board,i,j,vis);
            }
        }
        //last column and row changes
        j=n-1;
        for( i=0;i<m;i++){
            if(board[i][j]=='O' && !vis[i][j]){
                bfs(board,i,j,vis);
            }
        }
        //change remaining O's to X
        for(i=0;i<m;i++){
            for(j=0;j<n;j++){
                if(!vis[i][j] && board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }
    class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
    public void bfs(char board[][],int i,int j,boolean vis[][]){
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(i,j));
        while(!q.isEmpty()){
            Pair curr=q.remove();
            if(vis[curr.i][curr.j])continue;
            vis[curr.i][curr.j]=true;
            check(curr.i,curr.j,board,vis,q);
        }
    }
    public void check(int i,int j,char[][] board,boolean vis[][],Queue<Pair> q){
        int m=board.length;
        int n=board[0].length;
        int[][] directions={{1,0},{0,1},{0,-1},{-1,0}};
        for(int[] direction:directions){
            int a=i+direction[0];
            int b=j+direction[1];
            if( a>=0 && b>=0 && a<m && b<n && !vis[a][b] && board[a][b]=='O' ){
                q.add(new Pair(a,b));
            }
        }
    }
}
