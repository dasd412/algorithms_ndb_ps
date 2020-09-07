package dynamicProgramming;

import java.io.*;

public class GoldMine {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase=Integer.parseInt(br.readLine());

        int n,m;

        for (int i = 0; i <testCase ; i++) {

            String[]strings=br.readLine().split(" ");
            n=Integer.parseInt(strings[0]);
            m=Integer.parseInt(strings[1]);

            int[][]mine=new int[n][m];

            strings=br.readLine().split(" ");

            int count=0;

            for (int j = 0; j <n ; j++) {

                for (int k = 0; k <m ; k++) {
                    mine[j][k]=Integer.parseInt(strings[count*m+k]);
                }
                count++;
            }


            int maxMine=getMaxMine(mine,n,m);

            bw.write(maxMine+"\n");

        }


        br.close();
        bw.close();
    }

    private static int getMaxMine(int[][] mine, int n, int m) {

        /*
        이 문제는 그리디 알고리즘으로는 풀 수 없다. 반례를 들자면, 다음과 같다.

        1 4 3 7
        2 1 3 1
        0 6 4 2

        그리디로 풀어보면, 현재에 가장 높은 숫자를 근시안적으로 고르는 것이므로
        2, 6, 4, 2 를 고르게 된다. 합치면 14이다.

        하지만, 위의 예는 더 큰 합을 구할 수 있다. 2,6,3,7을 고르게 되면 18이다.
        따라서, 이 문제는 그리디 알고리즘이 아닌 다이나믹 프로그래밍으로 풀어야 한다.

         */


        int[][]dp=new int[n][m];

        for (int i = 0; i <n ; i++) {//dp 테이블 초기화
            dp[i][0]=mine[i][0];
        }



        //j번째 열을 기준으로 0~n-1행을 갱신한다. 즉, 세로로 순회한다.

        for (int j = 1; j <m ; j++) {
            for (int i = 0; i <n ; i++) {



                int max=dp[i][j-1];//왼쪽
                if(i-1>=0)
                max=Math.max(max,dp[i-1][j-1]);//왼쪽 위
                if(i+1<n)
                max=Math.max(max,dp[i+1][j-1]);//왼쪽 아래

                /*
                dp테이블의 (i,j)의 값= 금광 배열 (i,j)의 값+이전에 최댓값으로 갱신해두었던 세 가지 값들 중에서
                제일 큰값
                 */
                dp[i][j]=max+mine[i][j];


            }

        }

        int max=0;
        for (int i = 0; i <n ; i++) {

                max=Math.max(max,dp[i][m-1]);

        }


        return max;

    }

}
