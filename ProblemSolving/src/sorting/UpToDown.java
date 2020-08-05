package sorting;

import java.io.*;

public class UpToDown {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]arr=new int[n];

        int[]counts=new int[100001];

        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        /*
        수의 범위가 1이상 10만 이하의 자연수이므로 범위가 제한적이며 자료형이 정수형이다.
        따라서 계수 정렬을 활용할 수 있다.


         */

        for(int i=0;i<n;i++){
            counts[arr[i]]++;
        }

        for(int i=counts.length-1;i>0;i--){

            while(counts[i]>0){
                bw.write(Integer.toString(i)+" ");
                counts[i]--;
            }

        }



        br.close();
        bw.close();
    }


}
