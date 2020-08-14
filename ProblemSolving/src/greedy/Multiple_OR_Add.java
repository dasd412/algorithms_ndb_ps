package greedy;

import java.io.*;

public class Multiple_OR_Add {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split("");

        int max=0;

        for(int i=0;i<splits.length;i++){
            int num=Integer.parseInt(splits[i]);


            if(num==0||num==1){//숫자가 0또는 1이면 더한다.
                max+=num;
            }
            else if(max==0||max==1){//만약 아직 결과 값이 0또는 1이라면 수를 더한다.
                max+=num;
            }
            else{//그 외의 경우는 곱해야 가장 큰 수가 나온다.
                max*=num;
            }


        }
        bw.write(Integer.toString(max)+"\n");

        br.close();
        bw.close();
    }
}
