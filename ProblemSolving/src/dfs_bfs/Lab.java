package dfs_bfs;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Lab {
    static BufferedWriter bw;

    static class Dimension{
        int x;
        int y;

        public Dimension(int x, int y){
            this.x=x;
            this.y=y;
        }


        @Override
        public String toString() {
            return "Dimension{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static int secure=0;
    static int n,m;
    static int[]dx={1,0,-1,0};
    static int[]dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int[][]map=new int[n][m];


        List<Dimension>list=new ArrayList<>();

        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<m;j++){

                map[i][j]=Integer.parseInt(splits[j]);
                if(map[i][j]==0){//빈칸인 곳의 좌표를 담는다.
                    list.add(new Dimension(i,j));
                }
            }
        }


        List<Dimension> result=new ArrayList<>();

       combination(list,result,0,list.size(),3,map);//list의 크기에서 3개를 뽑는 조합 메소드



        /*
        솔루션
        1.조합 알고리즘을 이용해 3개의 벽을 뽑는다. (x,y)형식으로 뽑아놓는다.
        2.뽑아놓은 벽들을 설치한다.
        3.그 상태의 맵을 bfs 해본다. 바이러스 (==2)가 퍼지게 된다.
        4.안전 영역인 0의 개수를 세본다.
        5.분기마다 안전 영역 개수를 계산하고, 더 큰 값으로 갱신한다.

         */


        bw.write(secure+"\n");

        br.close();
        bw.close();
    }

    private static void combination(List<Dimension> list, List<Dimension> result, int index, int num, int r, int[][] map) {

        if(r==0){


            int[][]mockMap=new int[n][m];//임시 맵 만들기
            boolean[][]visited=new boolean[n][m];//임시 방문 체크 배열 만들기

            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    mockMap[i][j]=map[i][j];
                }
            }

            for(int i=0;i<result.size();i++){

                mockMap[result.get(i).x][result.get(i).y]=1;//뽑은 3개의 빈칸을 벽으로 만들어 본다.

            }

            //bfs를 해본다.
           for(int i=0;i<n;i++){
               for(int j=0;j<m;j++){


                   if(mockMap[i][j]==2&&visited[i][j]==false){//바이러스이고 방문한 적 없으면

                       BFS(i,j,mockMap,visited);

                   }

               }
           }

           int count=0;

           //안전 영역 크기를 구한다.
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){


                    if(mockMap[i][j]==0){//빈 칸인 곳을 센다.
                        count++;
                    }
                }
            }


            secure=Math.max(secure,count);//더 큰 값으로 갱신한다.

           return;
        }

        else{
            for(int i=index;i<num;i++){
                result.add(list.get(i));
                combination(list,result,i+1,num,r-1, map);
                result.remove(result.size()-1);
            }
        }

    }

    private static void BFS(int i, int j, int[][] map, boolean[][] visited) {
        Queue<Dimension>queue=new LinkedList<>();
        queue.offer(new Dimension(i,j));
        visited[i][j]=true;

        while(!queue.isEmpty()){
            Dimension poll=queue.poll();


            for(int a=0;a<4;a++){
                int x=poll.x+dx[a];
                int y=poll.y+dy[a];

                if(x<0||y<0||x>=n||y>=m){
                    continue;
                }

                if(map[x][y]==0&&visited[x][y]==false){// 방문한 적 없는 빈 곳에 대하여
                    queue.offer(new Dimension(x,y));
                    visited[x][y]=true;
                    map[x][y]=2;

                }

            }


        }

    }
}
