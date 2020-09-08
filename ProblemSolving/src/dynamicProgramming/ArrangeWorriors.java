package dynamicProgramming;

import java.io.*;

public class ArrangeWorriors {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        String[]strings=br.readLine().split(" ");
        int[]worriors=new int[n];

        for (int i = 0; i <n ; i++) {
            worriors[i]=Integer.parseInt(strings[i]);
        }

        int max=getMaxNumber(worriors,n);

        bw.write(max+"\n");

        br.close();
        bw.close();
    }

    private static int getMaxNumber(int[] worriors, int n) {

        //dp 테이블 초기화
        int[]dp=new int[n];

        for (int i = 0; i <n ; i++) {
            dp[i]=1;
        }

        for (int i = 0; i <n ; i++) {


            for (int j = 0; j <i ; j++) {
                if(worriors[i]<worriors[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }




        int max=0;


        for (int i = 0; i <n ; i++) {
            max=Math.max(max,dp[i]);
        }









        return n-max;
    }

}
