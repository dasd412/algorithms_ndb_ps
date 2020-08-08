package dynamicProgramming;

import java.io.*;

public class MakingOne {

    static BufferedWriter bw;

    static int[]dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n=Integer.parseInt(br.readLine());

        dp=new int[n+1];//+1하는 게 귀찮아서 미리 배열크기를 +1해줌.

        for(int i=0;i<n;i++){//n번쨰 인덱스까지
            dp[i]=50000;//정수 x보다 큰 값으로 초기화
        }

        int minCount=makingOne(n);


        bw.write(Integer.toString(minCount)+"\n");




        br.close();
        bw.close();
    }

    private static int makingOne(int n) {



        for(int i=n;i>1;i--){//n부터 시작해서 1이 될떄까지
            if(i%5==0){

                dp[i/5]=Math.min(dp[i/5],dp[i]+1);

            }
            if(i%3==0){

                dp[i/3]=Math.min(dp[i/3],dp[i]+1);
            }
            if(i%2==0){

                dp[i/2]=Math.min(dp[i/2],dp[i]+1);
            }

            dp[i-1]=Math.min(dp[i-1],dp[i]+1);

        }



        return dp[1];
    }
}
