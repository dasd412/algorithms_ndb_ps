package dfs_bfs;

import java.util.*;
import java.io.*;

public class CompetitiveInfestation {
    static BufferedWriter bw;

    static int n,k;
    static int s,x,y;

    static int[][]map;
    static boolean[][]visited;

    static int[]dx={1,0,-1,0};
    static int[]dy={0,1,0,-1};

    static class Virus implements Comparable<Virus>{
        private final int x;
        private final int y;
        private final int type;
        private int second;

        public Virus(int x, int y, int type,int second) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.second=second;
        }


        @Override
        public String toString() {
            return "Virus{" +
                    "x=" + x +
                    ", y=" + y +
                    ", type=" + type +
                    ", second=" + second +
                    '}';
        }

        @Override
        public int compareTo(Virus other) {
            return this.type-other.type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[]strings=br.readLine().split(" ");
        n=Integer.parseInt(strings[0]);
        k=Integer.parseInt(strings[1]);

       map=new int[n][n];
       visited=new boolean[n][n];

       List<Virus> list=new ArrayList<>();

        for(int i=0;i<n;i++){
            strings=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(strings[j]);
                if(map[i][j]!=0){
                    visited[i][j]=true;
                    list.add(new Virus(i,j,map[i][j],0));
                }
            }
        }


        Collections.sort(list);//타입에 대하여 오름차순 정렬한다.




        strings=br.readLine().split(" ");
        s=Integer.parseInt(strings[0]);
        x=Integer.parseInt(strings[1]);
        y=Integer.parseInt(strings[2]);



       Queue<Virus>queue=new LinkedList<>();

        for (Virus virus : list) {
            queue.offer(virus);
        }



        while(!queue.isEmpty()){


                Virus poll = queue.poll();//큐에서 바이러스 구역 하나를 뽑는다.
            if(poll.second==s){
                break;
            }


                visited[poll.x][poll.y] = true;

                for (int dir = 0; dir < 4; dir++) {//4방향에 대해
                    int x = poll.x + dx[dir];
                    int y = poll.y + dy[dir];

                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        //유효하지 않은 범위는 넘어간다.
                        continue;
                    }

                    if (!visited[x][y]) {//방문한 적 없는 구역이면,
                        visited[x][y] = true;
                        map[x][y] = poll.type;
                        queue.add(new Virus(x, y, poll.type,poll.second+1));

                    }

                }//for






        }//while



        bw.write(map[x-1][y-1]+"\n");



        br.close();
        bw.close();
    }


}
