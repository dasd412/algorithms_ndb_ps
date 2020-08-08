package dynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EfficientCurrency {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        List<Integer> coinUnit=new ArrayList<>();


        for(int i=0;i<n;i++){
            String str=br.readLine();

            if(!coinUnit.contains(Integer.parseInt(str)))
            coinUnit.add(Integer.parseInt(str));
        }

        int minCount=getMinCount(coinUnit,n,m);

        bw.write(Integer.toString(minCount)+"\n");

        
        br.close();
        bw.close();
    }

    private static int getMinCount(List<Integer> coinUnit, int n, int m) {
        int count=-1;

        int[]dp=new int[m+1];//1원부터 m원까지 본다.
        for(int i=1;i<=m;i++){
            dp[i]=1000001;//무한대로 초기화한다.
        }



        for(int i=0;i<dp.length;i++){

            for(int j=0;j<coinUnit.size();j++){//화폐 단위에 대하여
                if(i+coinUnit.get(j)<=m){//화폐 단위를 더해도 넘어 서지 않는 다면
                    dp[i+coinUnit.get(j)]=Math.min(dp[i+coinUnit.get(j)],dp[i]+1);
                }
            }

        }



        if(dp[m]==1000001){
            return -1;
        }
        else{
            return dp[m];
        }

    }
}
