package Graphs;
import java.util.*;
public class Dijkstra {
    static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int src,int dest,int wt){
            this.src=src;
            this.dest=dest;
            this.wt=wt;
        }
    }
    public static void buildgraph(ArrayList<ArrayList<Edge>> graph){
            for(int i = 0; i < 5; i++){
        graph.add(new ArrayList<>());
    }

   graph.get(0).add(new Edge(0, 1, 4));
   graph.get(0).add(new Edge(0, 2, 1));

   graph.get(1).add(new Edge(1, 3, 1));

   graph.get(2).add(new Edge(2, 1, 2));
   graph.get(2).add(new Edge(2, 3, 5));

   graph.get(3).add(new Edge(3, 4, 3));

   graph.get(4).add(new Edge(4, 0, 7));
    }
    static class Pair implements Comparable<Pair>{
        int s;
        int w;
        Pair(int s,int w){
            this.s=s;
            this.w=w;
        }
        public int compareTo(Pair o){
            return this.w-o.w;
        }
    }
    public static void Dijkstra(ArrayList<ArrayList<Edge>> graph,boolean vis[],int dis[],int curr){
   
    PriorityQueue<Pair> q=new PriorityQueue<>();
    Arrays.fill(dis, Integer.MAX_VALUE);
    dis[0]=0;
    q.add(new Pair(curr, curr));
    while(!q.isEmpty()){
         Pair p=q.poll();
         if(!vis[p.s]){
            vis[p.s]=true;
         for(int i=0;i<graph.get(p.s).size();i++){
            Edge e=graph.get(p.s).get(i);
            int u=e.src;
            int v=e.dest;
            int w=e.wt;
            if(dis[u]+w<dis[v]){
                dis[v]=dis[u]+w;
                q.add(new Pair(v, dis[v]));
            }
         }
        }
    }
    System.out.println(Arrays.toString(dis));
    }
    public static void primsAlgorithm(ArrayList<ArrayList<Edge>> graph,boolean vis[],int src){
        PriorityQueue<Pair> q=new PriorityQueue<>();
        q.add(new Pair(src, 0));
        int cost=0;
        while(!q.isEmpty()){
            Pair p=q.poll();
            if(!vis[p.s]){
                vis[p.s]=true;
                cost+=p.w;
                for(int i=0;i<graph.get(p.s).size();i++){
                   Edge e=graph.get(p.s).get(i);
                   if(!vis[e.dest]){
                    q.add(new Pair(e.dest, e.wt));
                   }
                }
            }
        }
        System.out.println(cost);
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> graph=new ArrayList<>();
        int n=5;
        buildgraph(graph);
        Dijkstra(graph, new boolean[n], new int[n], 0);
        primsAlgorithm(graph, new boolean[n], 0);
    }
}
