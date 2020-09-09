package dynamicProgramming;

import java.io.*;

public class EditingDistance {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String a=br.readLine();
        String b=br.readLine();

        int minDistance=GetEditDistance(a,b);

        bw.write(minDistance+"\n");

        br.close();
        bw.close();
    }

    private static int GetEditDistance(String a, String b) {

        int n=a.length();
        int m=b.length();

        int[][]dp=new int[n+1][m+1];


        for (int i = 0; i <n+1 ; i++) {
            dp[i][0]=i;
        }

        for (int j = 0; j <m+1 ; j++) {
            dp[0][j]=j;
        }

        for (int i = 1; i <n+1 ; i++) {
            for (int j = 1; j <m+1 ; j++) {

                if(a.charAt(i-1)==b.charAt(j-1)){//a문자열의 문자랑 b문자열의 문자가 같다면, 왼쪽 대각선 값을 가져온다.

                    dp[i][j]=dp[i-1][j-1];

                }else{//두 문자가 다르면, 왼쪽 대각선, 왼쪽, 위를 확인하여, 가장 작은 값+1로 갱신한다.

                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;

                }
            }
        }

        for (int i = 0; i <n+1 ; i++) {
            for (int j = 0; j <m+1 ; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }




        return dp[n][m];
    }

}
