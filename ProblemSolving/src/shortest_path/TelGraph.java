package shortest_path;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TelGraph {
    /*
    정점의 개수가 많으므로 플로이드 워셜은 쓸 수 없다.
    따라서 다익스트라_우선 순위 큐를 사용한다.
     */

    static BufferedWriter bw;
    static class Node implements Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index,int distance){
            this.index=index;
            this.distance=distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }


        @Override
        public int compareTo(Node other) {
            if(this.distance<other.distance){//최소 힙에 담을 것이므로, 거리가 작을수록 우선순위가 높아진다.
                return -1;
            }
            return 1;
        }
    }

    static int n,m,c;
    static int[]d;
    static final int INF=(int)1e9;

    static List<ArrayList<Node>> graph=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);
        c=Integer.parseInt(splits[2]);

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        d=new int[n+1];

        Arrays.fill(d,INF);

        for(int i=0;i<m;i++){

            splits=br.readLine().split(" ");
            int x=Integer.parseInt(splits[0]);
            int y=Integer.parseInt(splits[1]);
            int z=Integer.parseInt(splits[2]);

            //단 방향 그래프이므로
            graph.get(x).add(new Node(y,z));

        }

        dijkstra(c);//c를 출발점으로 하여 다익스트라 수행



        br.close();
        bw.close();
    }

    private static void dijkstra(int start) throws IOException{


        d[start]=0;
        PriorityQueue<Node>pq=new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){

            Node poll=pq.poll();
            int now=poll.getIndex();
            int dist=poll.getDistance();

            if(d[now]<dist){//더 적은 거리로 갱신된 적 있으면 넘어간다.
                continue;
            }

            for(int i=0;i<graph.get(now).size();i++){
                int cost=d[now]+graph.get(now).get(i).getDistance();

                if(cost<d[graph.get(now).get(i).getIndex()]){//더 적은 거리라면 갱신한다.
                    d[graph.get(now).get(i).getIndex()]=cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(),cost));
                }

            }

        }


        int cityCount=0;
        int hour=0;

        for(int i=0;i<d.length;i++){
            if(i!=c&&d[i]!=INF){
             cityCount++;

             hour=Math.max(hour,d[i]);

            }
        }

        bw.write(cityCount+" "+hour+"\n");


    }

}
