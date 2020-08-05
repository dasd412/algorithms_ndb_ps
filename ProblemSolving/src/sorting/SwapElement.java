package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SwapElement {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");

        int n,k;
        n=Integer.parseInt(splits[0]);
        k=Integer.parseInt(splits[1]);


        int[]a=new int[n];
        splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            a[i]=Integer.parseInt(splits[i]);
        }

        splits=br.readLine().split(" ");
        int[]b=new int[n];
        for(int i=0;i<n;i++){
            b[i]=Integer.parseInt(splits[i]);
        }

        Arrays.sort(a);

        Arrays.sort(b);

        //Arrays.sort(b, Collections.reverseOrder());<-내림 차순 정렬, 단 int가 아닌 Integer형이여야 한다.


        for(int i=0;i<k;i++){//k번 비교한다.

            if(a[i]<b[n-1-i]){//a의 요소보다 b의 요소가 크면, a에 대입한다.
                a[i]=b[n-1-i];
            }
            else{//작거나 같으면, 정렬이 되있으므로 더 이상 반복할 필요가 없다.
                break;
            }


        }

        int sum=0;

        for(int i=0;i<n;i++){

            sum+=a[i];
        }

        bw.write(Integer.toString(sum)+"\n");


        br.close();
        bw.close();
    }
}
