package shortest_path;

import java.io.*;
import java.util.Arrays;

public class Floyd_Warshall {
    static BufferedWriter bw;

    static int[][]distance=new int[501][501];//거리 배열
    static int v,e;//정점의 개수 v, 간선의 개수 e
    static final int INF=(int)1e9;//무한대라고 가정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        v=Integer.parseInt(br.readLine());
        e=Integer.parseInt(br.readLine());

        for(int i=0;i<501;i++){//일단 무한대로 거리 배열 초기화
            Arrays.fill(distance[i],INF);
        }


        for(int i=1;i<=v;i++){
            for(int j=1;j<=v;j++){
                if(i==j){//자기 자신에게 가는 경우는 0으로 초기화합니다.
                    distance[i][j]=0;
                }
            }
        }

        String[]splits;

        for(int i=0;i<e;i++){
            splits=br.readLine().split(" ");
            int origin=Integer.parseInt(splits[0]);
            int dest=Integer.parseInt(splits[1]);
            int cost=Integer.parseInt(splits[2]);

            distance[origin][dest]=cost;//origin부터 dest로 가는 cost를 기입한다.
        }


        //플로이드 워셜 알고리즘 수행.
        for(int k=1;k<=v;k++){
            for(int a=1;a<=v;a++){
                for(int b=1;b<=v;b++){

                    //a ~> k ~> b처럼 특정 k 노드를 경유하는 거리와 현재 거리를 비교하여 최단 경로를 갱신한다.
                    distance[a][b]=Math.min(distance[a][b],distance[a][k]+distance[k][b]);
                }
            }
        }

        for(int i=1;i<=v;i++){

            for(int j=1;j<=v;j++){
                if(distance[i][j]==INF){
                    bw.write("I ");
                }
                else{//도달할 수 있는 경우
                    bw.write(Integer.toString(distance[i][j])+" ");
                }

            }
            bw.write("\n");
        }




        br.close();
        bw.close();
    }
}
