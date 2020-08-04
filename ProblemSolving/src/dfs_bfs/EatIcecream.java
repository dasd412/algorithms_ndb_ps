package dfs_bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class EatIcecream {
    static BufferedWriter bw;

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    static int[]dx={-1,0,1,0};
    static int[]dy={0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n,m;

        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int[][]map=new int[n][m];

        boolean[][]visit=new boolean[n][m];

        for(int i=0;i<n;i++){
            splits=br.readLine().split("");
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(splits[j]);

                if(map[i][j]==1){
                    visit[i][j]=true;
                }
            }
        }

        int iceCreams=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                if(visit[i][j]==false&&map[i][j]==0){//방문한 적 없는 구멍 부분에 대해
                    getIceCream(i,j,n,m,map,visit);//너비 우선 탐색을 진행한다.
                    iceCreams++;
                }

            }
        }

        bw.write(Integer.toString(iceCreams)+"\n");



        br.close();
        bw.close();
    }

    private static void getIceCream(int i, int j, int n, int m, int[][] map, boolean[][] visit) {//너비 우선 탐색 메소드


        Queue<Location> queue=new LinkedList<>();
        //첫 방문 장소에 대한 처리
        queue.add(new Location(i,j));
        visit[i][j]=true;

        while(!queue.isEmpty()){//큐에 원소가 남아있으면 반복한다.

            Location poll=queue.poll();//큐에서 빼낸 원소.

            for(int a=0;a<4;a++){//4방향에 대하여

               int x=poll.x+dx[a];
               int y=poll.y+dy[a];

               if(x<0||y<0||x>=n||y>=m){//유효하지 않은 좌표는 처리하지 않는다.
                   continue;
               }

               if(visit[x][y]==false&&map[x][y]==0){//방문한 적 없으면 방문 처리하고 큐에 담는다.
                   queue.add(new Location(x,y));
                   visit[x][y]=true;

               }

            }

        }

    }
}
