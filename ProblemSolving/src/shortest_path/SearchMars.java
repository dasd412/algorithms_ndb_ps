package shortest_path;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SearchMars {

    static BufferedWriter bw;
    static final int INF=(int)1e9;

    static  class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;

        public Node(int x,int y,int cost){
            this.x=x;
            this.y=y;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }

    static int[]dx={1,0,-1,0};
    static int[]dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int testCase=Integer.parseInt(br.readLine());

        for (int i = 0; i <testCase ; i++) {
            int n=Integer.parseInt(br.readLine());
            int[][]map=new int[n][n];


            for (int j = 0; j <n ; j++) {
                String[]strings=br.readLine().split(" ");
                for (int k = 0; k <n ; k++) {
                    map[j][k]=Integer.parseInt(strings[k]);
                }
            }

            int minCost=getMinCost(map,n);

            bw.write(minCost+"\n");

        }

        br.close();
        bw.close();
    }

    private static int getMinCost(int[][] map, int n) {



        int[][]d=new int[n][n];//각각의 노드 비용 배열

        for (int i = 0; i <n ; i++) {
            Arrays.fill(d[i],INF);
        }
        d[0][0]=map[0][0];



        PriorityQueue<Node> queue=new PriorityQueue<>();
        queue.offer(new Node(0,0,map[0][0]));

        while(!queue.isEmpty()){

            Node poll=queue.poll();

            if(d[poll.x][poll.y]<poll.cost){//만약 처리된 적 있으면 무시한다.
                continue;
            }


            for (int i = 0; i <4 ; i++) {
                int x=poll.x+dx[i];
                int y=poll.y+dy[i];


                if(x<0||y<0||x>=n||y>=n){
                    continue;
                }
                int adjCost=poll.cost+map[x][y];//인접한 곳으로 가는 총 비용=뽑아낸 노드의 비용+인접한 곳의 비용

                if(adjCost<d[x][y]){//만약,  비용이 더 적다면, 갱신하고 우선 순위 큐에 담는다.
                    d[x][y]=adjCost;
                    queue.offer(new Node(x,y,adjCost));
                }


            }

        }



        return d[n-1][n-1];

    }

}
