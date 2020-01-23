package com.kahol;
import java.util.*;
import java.lang.*;

public class Main {

    static class Coord{
        int x;
        int y;
        int dist;
        Coord(int x,int y,int d){
            this.x = x;
            this.y = y;
            this.dist = d;
        }
    }
    static int sr,sc,dr,dc;
    static int n=6;
    public static void drawBoard(char[][] maze){
        System.out.println("________");
        for(int i=0;i<maze.length;i++){
            System.out.print("|");
            for(int j=0;j<maze[i].length;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("________");
    }
    public static void moveRight(char[][] maze){
        if(sc<n-1 && maze[sr][sc+1]!='O' && maze[sr][sc+1]!='|'){
            maze[sr][sc] ='+';
            maze[sr][sc+1] = 'S';
            sc = sc+1;
        }
    }
    public static void moveLeft(char[][] maze){
        if(sc>0 && maze[sr][sc-1]!='O' && maze[sr][sc-1]!='|'){
            maze[sr][sc] ='+';
            maze[sr][sc-1] = 'S';
            sc = sc-1;
        }
        else System.out.println("Invalid move!!!");
    }
    public static void moveUp(char[][] maze){
        if(sr>0 && maze[sr-1][sc]!='O' && maze[sr-1][sc]!='|'){
            maze[sr][sc] ='+';
            maze[sr-1][sc] = 'S';
            sr = sr-1;
        }
        else System.out.println("Invalid move!!!");
    }
    public static void moveDown(char[][] maze){
        if(sr<n-1 && maze[sr+1][sc]!='O' && maze[sr+1][sc]!='|'){
            maze[sr][sc] ='+';
            maze[sr+1][sc] = 'S';
            sr = sr+1;
        }
        else System.out.println("Invalid move!!!");
    }
    public static int bfsShortest(char[][] maze){
        Coord c =new Coord(sr,sc,0);    // initial coordinates of source
        Queue<Coord> q = new LinkedList<Coord>();
        q.add(c);
        boolean vis[][] = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(maze[i][j]=='+')
                    vis[i][j] = false;
                else vis[i][j] = true;
            }
        }
        while(!q.isEmpty()){
            // move right
            Coord p = q.peek();
            q.poll();
            // destination found
            if(maze[p.x][p.y] == 'D'){
                return p.dist;
            }
            if(p.x<n-1 && !vis[p.x][p.y]){   // right
                maze[p.x][p.y] ='+';
                maze[p.x][p.y+1] = 'S';
                vis[p.x][p.y] = true;
                q.add(new Coord(p.x,p.y+1,p.dist+1));
            }
            if(p.x>0 && !vis[p.x][p.y]){     // left
                maze[p.x][p.y] ='+';
                maze[p.x][p.y-1] = 'S';
                vis[p.x][p.y] = true;
                q.add(new Coord(p.x,p.y-1,p.dist+1));
            }
            if(p.x>0 && !vis[p.x][p.y]){     // up
                maze[p.x][p.y] ='+';
                maze[p.x-1][p.y] = 'S';
                vis[p.x][p.y] = true;
                q.add(new Coord(p.x-1,p.y,p.dist+1));
            }
            if(p.x<n-1 && !vis[p.x][p.y]){       // down
                maze[p.x][p.y] ='+';
                maze[p.x+1][p.y] = 'S';
                vis[p.x][p.y] = true;
                q.add(new Coord(p.x+1,p.y,p.dist+1));
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] maze = {{'+','O','+','+','O','+'},
                            {'+','O','O','+','O','+'},
                            {'+','+','+','+','+','+'},
                            {'O','+','+','O','+','+'},
                            {'+','+','O','O','+','O'},
                            {'+','+','+','+','+','+'},
                           };
        // source and dest coordinates
        sr = scan.nextInt();
        sc = scan.nextInt();
        dr = scan.nextInt();
        dc = scan.nextInt();
        maze[sr][sc] = 'S';
        maze[dr][dc] = 'D';
        drawBoard(maze);
       // System.out.println(bfsShortest((maze)));
        char move=' ';
        while(sr!=dr || sc!=dc) {
           // System.out.println("************************************************************************");
            System.out.print("Press move(a,w,s,d) :");     // a,w,s,d
            move = scan.next().charAt(0);
            switch(move){
                case 'a':
                    moveLeft(maze);
                    break;
                case 'w':
                    moveUp(maze);
                    break;
                case 's':
                    moveDown(maze);
                    break;
                case 'd':
                    moveRight(maze);
                    break;
                case 'q':
                    break;
            }
            drawBoard(maze);
        }
        if(move=='q') System.out.println("QUIT GAME");
        else{
            System.out.println("cONGRACHULATIONS! MAZE COMPLETE.");
        }
    }
}
