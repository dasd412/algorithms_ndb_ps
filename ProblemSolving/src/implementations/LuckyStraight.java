package implementations;

import java.io.*;

public class LuckyStraight {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split("");


        int left=0;
        int right=0;

        for(int i=0;i<splits.length/2;i++){

            left+=Integer.parseInt(splits[i]);
        }

        for(int i=splits.length/2;i<splits.length;i++){

            right+=Integer.parseInt(splits[i]);
        }

        if(left==right){
            bw.write("LUCKY\n");
        }
        else{
            bw.write("READY\n");
        }


        br.close();
        bw.close();
    }
}
