package sorting;

import java.io.*;
import java.util.Arrays;

public class Antenna {

    static BufferedWriter bw;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]map=new int[n];

        String[]splits=br.readLine().split(" ");

        for (int i = 0; i <n ; i++) {

            map[i]=Integer.parseInt(splits[i]);
        }

       Arrays.sort(map);

        bw.write(map[(n-1)/2]+"\n");//중간값이어야만 거리의 총합이 최소가 된다...




        br.close();
        bw.close();
    }
}
