package dynamicProgramming;

import java.io.*;

public class AntWarrior {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]arr=new int[n];

        String[]splits=br.readLine().split(" ");

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(splits[i]);
        }

        int max=getMax(arr);

        bw.write(Integer.toString(max)+"\n");

        br.close();
        bw.close();
    }

    private static int getMax(int[] arr) {

        int[]dp=new int[arr.length];


        for(int i=0;i<arr.length;i++){

            dp[i]=arr[i];
        }

        for(int i=0;i<dp.length;i++){

            for(int j=i+2;j<dp.length;j++){//한칸 넘겨서부터 선택가능.
                dp[j]=Math.max(dp[j],dp[i]+arr[j]);
            }


        }


        int max=0;

        for(int i=0;i<dp.length;i++){
            max=Math.max(max,dp[i]);
        }

        return max;
    }

}
