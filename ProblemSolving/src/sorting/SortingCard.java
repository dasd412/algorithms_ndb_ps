package sorting;

import java.io.*;
import java.util.PriorityQueue;


public class SortingCard {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        long[]cards=new long[n];
        PriorityQueue<Long> pq=new PriorityQueue<>();

        for (int i = 0; i <n ; i++) {
            cards[i]=Long.parseLong(br.readLine());
            pq.offer(cards[i]);
        }


        long sum=0;


        while(pq.size()>1){
            long first=pq.poll();
            long second=pq.poll();


            long surplus=first+second;
            sum+=surplus;

            pq.offer(surplus);
        }




        bw.write(sum+"\n");

        br.close();
        bw.close();
    }
}
