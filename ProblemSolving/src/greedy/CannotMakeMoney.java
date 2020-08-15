package greedy;

import java.io.*;
import java.util.Arrays;

public class CannotMakeMoney {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int[]money=new int[n];
        String[]splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            money[i]=Integer.parseInt(splits[i]);
        }

        int minMoney=getMinMoney(money,n);

        bw.write(Integer.toString(minMoney)+"\n");

        br.close();
        bw.close();
    }

    private static int getMinMoney(int[] money, int n) {

        Arrays.sort(money);
        if(money[0]>1){//최소 단위가 1보다 크면, 1원 동전은 절대 만들 수 없다.
            return 1;
        }
        else{

            /*

            문제 이해가 안되서 스킵한다..
             */

            return -1;

        }


    }
}
