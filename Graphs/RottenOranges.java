package Graphs;
import java.util.*;
public class RottenOranges {
    static class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
    public static void main(String[] args) {
       int grid[][]={{2,1,1},{1,1,0},{0,1,1}};
       Queue<Pair> q=new LinkedList<>();
       boolean vis[][]=new boolean[grid.length][grid[0].length];
       for(int i=0;i<grid.length;i++){
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]==2 && !vis[i][j]){
             vis[i][j]=true;
             q.add(new Pair(i, j));
            }
        }
       }
       bfs(grid, vis,q);
    }
    public static void bfs(int[][] grid,boolean[][] vis,Queue<Pair> q){
        int m=grid.length;
        int n=grid[0].length;
        int count=0;
       while(!q.isEmpty()){
        int size=q.size();
        for(int i=0;i<size;i++){
            Pair curr=q.poll();
            check(curr.i,curr.j,m,n,grid,q,vis);
        }
        if(!q.isEmpty())count++;
       }
        System.out.print(count);
    }
    private static void check(int i, int j, int m, int n, int[][] grid, Queue<Pair> q, boolean[][] vis) {
       if(i-1>=0 && grid[i-1][j]==1 && !vis[i-1][j]){
        q.add(new Pair(i-1, j));
        vis[i-1][j]=true;
        grid[i-1][j]=2;
       }
       if(i+1<m && grid[i+1][j]==1 && !vis[i+1][j]){
        q.add(new Pair(i+1, j));
        vis[i+1][j]=true;
        grid[i+1][j]=2;
       }
       if(j-1>=0 && grid[i][j-1]==1 && !vis[i][j-1]){
        q.add(new Pair(i, j-1));
        vis[i][j-1]=true;
        grid[i][j-1]=2;
       }
       if(j+1<n && grid[i][j+1]==1 && !vis[i][j+1]){
        q.add(new Pair(i, j+1));
        vis[i][j+1]=true;
        grid[i][j+1]=2;
       }
    }
}
// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//         int originalColor = image[sr][sc];
//         int m=image.length;
//         int n=image[0].length;
//         boolean vis[][]=new boolean[m][n];
//         bfs(sr,sc,image,vis,color,originalColor);
//         return image;
//     }
//     class Pair{
//         int i;
//         int j;
//         Pair(int i,int j){
//             this.i=i;
//             this.j=j;
//         }
//     }
//     public void bfs(int sr,int sc,int[][] image,boolean vis[][],int color,int originalColor){
//         Queue<Pair> q=new LinkedList<>();
//         q.add(new Pair(sr,sc));
//         while(!q.isEmpty()){
//             Pair curr=q.remove();
//            if(vis[curr.i][curr.j])continue;
//             vis[curr.i][curr.j]=true;
//             image[curr.i][curr.j]=color;
//             check(curr.i,curr.j,q,vis,image,originalColor);
//         }
//     }
//     public void check(int sr,int sc,Queue<Pair> q,boolean vis[][],int[][] image,int originalColor){
//         int m=image.length;
//         int n=image[0].length;
//         if(sr-1>=0 && vis[sr-1][sc]==false && image[sr-1][sc]==originalColor){
//             q.add(new Pair(sr-1,sc));
//         }
//         if(sr+1<m && vis[sr+1][sc]==false && image[sr+1][sc]==originalColor){
//             q.add(new Pair(sr+1,sc));
//         }
//         if(sc+1<n && vis[sr][sc+1]==false && image[sr][sc+1]==originalColor){
//             q.add(new Pair(sr,sc+1));
//         }
//         if(sc-1>=0 && vis[sr][sc-1]==false && image[sr][sc-1]==originalColor){
//             q.add(new Pair(sr,sc-1));
//         }
//     }
// }