package shortest_path;

import java.io.*;
import java.util.Arrays;

public class Floyd {
    static BufferedWriter bw;

    static final int INF=(int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());

        int[][]distance=new int[101][101];

        for (int i = 0; i <101 ; i++) {
            Arrays.fill(distance[i],INF);
        }

        for (int i = 1; i <=n ; i++) {
            distance[i][i]=0;
        }


        for (int i = 0; i <m ; i++) {
            String[]strings=br.readLine().split(" ");
            int origin=Integer.parseInt(strings[0]);
            int dest=Integer.parseInt(strings[1]);
            int cost=Integer.parseInt(strings[2]);

            if(distance[origin][dest]>cost){//시작 도시와 도착 도시를 연결하는 노선은 하나가 아니다... 더 작은 비용의 버스가 있을 경우 더 작은 것으로 갱신한다.
                distance[origin][dest]=cost;

            }


        }

        for (int k = 1; k <=n ; k++) {
            for (int i = 1; i <=n ; i++) {
                for (int j = 1; j <=n ; j++) {
                    distance[i][j]=Math.min(distance[i][j],distance[i][k]+distance[k][j]);
                }
            }

        }

        for (int i = 1 ;i <=n ; i++) {
            for (int j = 1; j <=n ; j++) {
                if(distance[i][j]==INF){
                    bw.write(0+" ");
                }else{
                    bw.write(distance[i][j]+" ");
                }
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

}
