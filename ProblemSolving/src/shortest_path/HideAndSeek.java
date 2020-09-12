package shortest_path;

import java.io.*;
import java.util.*;

public class HideAndSeek {


    static BufferedWriter bw;

    static final int INF=(int)1e9;

    static class Node implements Comparable<Node>{
        int index;
        int distance;

        public Node(int index,int distance) {

            this.index=index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance-o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]strings=br.readLine().split(" ");
        n=Integer.parseInt(strings[0]);
        m=Integer.parseInt(strings[1]);

        List<ArrayList<Node>>graph=new ArrayList<>();

        for (int i = 0; i <n ; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i <m ; i++) {

            strings=br.readLine().split(" ");
            int origin=Integer.parseInt(strings[0]);
            int dest=Integer.parseInt(strings[1]);

            //양방향 그래프
            graph.get(origin-1).add(new Node(dest-1,1));
            graph.get(dest-1).add(new Node(origin-1,1));

        }

        int[]d=new int[n];
        Arrays.fill(d,INF);
        d[0]=0;

        PriorityQueue<Node>queue=new PriorityQueue<>();

        queue.offer(new Node(0,0));

        while(!queue.isEmpty()){

            Node poll=queue.poll();
            int now=poll.index;
            int distance=poll.distance;

            if(d[now]<distance){
                continue;//처리된 적이 있으면 스킵한다.
            }

            for (int i = 0; i <graph.get(now).size() ; i++) {

                int cost=d[now]+graph.get(now).get(i).distance;

                if(cost<d[graph.get(now).get(i).index]){
                    d[graph.get(now).get(i).index]=cost;
                    queue.offer(new Node(graph.get(now).get(i).index,cost));
                }

            }

        }

        int maxDistance=0;
        int minIndex=0;

        for (int i = 1; i <n ; i++) {

            if(maxDistance<d[i]){
                maxDistance=d[i];
                minIndex=i;
            }

        }

        int sameCount=0;

        for (int i = 1; i <n ; i++) {
            if(maxDistance==d[i]){
                sameCount++;
            }
        }

        bw.write((minIndex+1)+" "+maxDistance+" "+sameCount+"\n");



        br.close();
        bw.close();
    }
}
