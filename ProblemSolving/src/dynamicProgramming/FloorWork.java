package dynamicProgramming;

import java.io.*;

public class FloorWork {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int caseCount=getCaseCount(n);

        bw.write(Integer.toString(caseCount)+"\n");

        br.close();
        bw.close();
    }

    private static int getCaseCount(int n) {

        int[]dp=new int[n];
        dp[0]=1;
        dp[1]=3;

        for(int i=2;i<n;i++){
            dp[i]=(dp[i-1]+2*dp[i-2])%796796;
        }


        return dp[n-1];

    }

}
