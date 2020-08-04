package dfs_bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeMaze {

    static BufferedWriter bw;

    static int[]dx={1,0,-1,0};
    static int[]dy={0,1,0,-1};

    static class Dimension{
        int x;
        int y;
        int distance;//경로 상 최단 거리를 뜻함.

        public Dimension(int x, int y,int distance){
            this.x=x;
            this.y=y;
            this.distance=distance;
        }
    }

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

                if(map[i][j]==0){
                    visit[i][j]=true;
                }
            }
        }


        int count=BFS(0,0,n,m,map,visit);

        bw.write(Integer.toString(count)+"\n");

        br.close();
        bw.close();
    }

    private static int BFS(int x, int y, int n, int m, int[][] map, boolean[][] visit) {

        int count=1;

        Queue<Dimension> queue=new LinkedList<>();
        queue.add(new Dimension(x,y,1));
        visit[x][y]=true;

        while(!queue.isEmpty()){

            Dimension poll=queue.poll();

            if(poll.x==n-1&&poll.y==m-1){//도착 지점이면 멈춘다.
                count=poll.distance;
                break;
            }

            for(int i=0;i<4;i++){//4방향에 대하여

                //인접한 곳의 좌표를 구한다.
                int adjX=poll.x+dx[i];
                int adjY=poll.y+dy[i];
                int adjDistance=poll.distance+1;//인접 거리=거리+1

                //유효하지 않은 좌표는 거른다.
                if(adjX<0||adjY<0||adjX>=n||adjY>=m){
                    continue;
                }

                if(visit[adjX][adjY]==false&&map[adjX][adjY]==1){//방문한 적 없는 안전 지대라면,
                    queue.add(new Dimension(adjX,adjY,adjDistance));//큐에 인접 그래프를 담고
                    visit[adjX][adjY]=true;//방문했다고 표시한다.
                }


            }


        }



        return count;
    }
}
