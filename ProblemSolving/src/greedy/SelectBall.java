package greedy;

import java.io.*;
import java.util.Arrays;

public class SelectBall {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[]splits=br.readLine().split(" ");
        int n,m;
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int[]balls=new int[n];
        splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            balls[i]=Integer.parseInt(splits[i]);
        }

        Arrays.sort(balls);

        int caseCount=0;

        /*
         1 5 4 3 2 4 5 2에 대해 오름 차순 정렬해보자.

         그려면 1 2 2 3 4 4 5 5 다.
         각각  중복을 제외하고 오른쪽에서
         집을 수 있는 개수는 7 , 5, 5, 4 ,2, 2 , 0, 0이다.


         즉, 로직은 다음과 같다.
         선택한 공이 최댓값인 경우는 볼 필요가 없다. 따라서 무게의 종류가 단일이면 경우의 수도 0이다.

         공을 해당 인덱스 기준으로 오른쪽으로 탐색하여 중복인 공들을 센다.


         중복인 공들의 개수*(전체 공의 개수-마지막 중복 공의 인덱스)를 더해주면 된다.

         */



        for(int i=0;i<balls.length;){

            if(balls[i]==m){//선택한 공이 최댓값인 경우는 반복을 멈춘다.
                break;
            }


                int j=i;
                for(;j<balls.length;j++){
                    if(balls[i]!=balls[j]){//같은 무게의 공을 찾을 때까지 반복하다가 찾으면 반복 종료한다.
                        break;
                    }
                }

                caseCount+=(balls.length-j)*(j-i);



              i=j;



        }

        bw.write(Integer.toString(caseCount)+"\n");

        br.close();
        bw.close();
    }
}
