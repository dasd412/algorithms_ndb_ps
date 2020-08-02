package greedy;

import java.io.*;

public class UntilBeingOne {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,k;

        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        k=Integer.parseInt(splits[1]);

        int count=0;

        while(n!=1){//n이 1아닌 동안 반복한다.

            count++;//수행 횟수를 센다.
        if(n%k==0){//나누어 떨어지면, 반드시 k로 나눠줘야 수행 횟수를 줄일 수 있다.
            n=n/k;
        }
        else{//그 외에는 1빼야한다.
            n=n-1;
        }

        }

        bw.write(Integer.toString(count)+"\n");

        br.close();
        bw.close();
    }
}
