package dynamicProgramming;

import java.io.*;

public class TriangleInteger {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n=Integer.parseInt(br.readLine());

        int[][]arr=new int[n][n];

        String[]splits;

        for (int i = 0; i <n ; i++) {
            splits=br.readLine().split(" ");
            for (int j = 0; j <=i ; j++) {
                arr[i][j]=Integer.parseInt(splits[j]);
            }
        }


        int maxSum=getMaxSum(arr,n);

        bw.write(maxSum+"\n");


        br.close();
        bw.close();
    }

    private static int getMaxSum(int[][] arr, int n) {

        //dp 테이블 초기화
        int[][]dp=new int[n][n];
        dp[0][0]=arr[0][0];


        for (int i = 1; i <n ; i++) {//1행부터 시작한다.

            for (int j = 0; j <=i; j++) {

                if(j==0){//왼쪽 변인 경우,
                    dp[i][j]=arr[i][j]+dp[i-1][j];

                }else if(j==i){//오른쪽 변인 경우

                    dp[i][j]=arr[i][j]+dp[i-1][j-1];
                }
                else{//그 외인 경우

                    int max=Math.max(dp[i-1][j-1],dp[i-1][j]);

                    dp[i][j]=max+arr[i][j];
                    
                }

            }

        }

        int max=0;
        for (int i = 0; i <n ; i++) {
            max=Math.max(max,dp[n-1][i]);
        }

        return max;
    }


}
