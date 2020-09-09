package dynamicProgramming;

import java.io.*;

public class UglyNumber {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n=Integer.parseInt(br.readLine());

        int uglyNumber=getUglyNumber(n);

        bw.write(uglyNumber+"\n");

        br.close();
        bw.close();
    }

    private static int getUglyNumber(int n) {

        //dp 테이블 초기화
        int[]dp=new int[1000];
        dp[0]=1;

        int x2=dp[0]*2, x3=dp[0]*3, x5=dp[0]*5;

        int index2=0,index3=0,index5=0;


        for (int i = 1; i <n ; i++) {
            dp[i]=Math.min(x2,Math.min(x3,x5));

            if(dp[i]==x2){
                index2++;

                x2=dp[index2]*2;

            } if(dp[i]==x3){
                index3++;

                x3=dp[index3]*3;


            } if(dp[i]==x5){

                index5++;
                x5=dp[index5]*5;

            }

        }



        return dp[n-1];
    }


}
