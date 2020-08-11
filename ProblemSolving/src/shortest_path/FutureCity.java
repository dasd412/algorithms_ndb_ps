package shortest_path;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FutureCity {

    static BufferedWriter bw;
    static final int INF=(int)1e9;

    static class Node implements  Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index,int distance){
            this.index=index;
            this.distance=distance;
        }

        public int getDistance() {
            return distance;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Node other) {
            if(this.distance<other.distance){
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);


        List<ArrayList<Node>> graph=new ArrayList<>();

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }


        


        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);

            //양방향 그래프이므로
            graph.get(a).add(new Node(b,1));
            graph.get(b).add(new Node(a,1));
        }

        int x,k;
        splits=br.readLine().split(" ");
        x=Integer.parseInt(splits[0]);
        k=Integer.parseInt(splits[1]);

        /*

        반드시 k를 먼저 간후 x를 가야 하기 때문에 start->k->x 경로만 계산한다.
         */


        int startToK=getMinDistance(n,m,graph,1,k);

        if(startToK==INF){
            System.out.println(-1);
            return;
        }

        
        int kToX=getMinDistance(n,m,graph,k,x);

        if(kToX==INF){
            System.out.println(-1);
            return;
        }




        bw.write((startToK + kToX) +"\n");

        br.close();
        bw.close();
    }

    private static int getMinDistance(int n, int m, List<ArrayList<Node>> graph, int start, int end) {

        int[]d=new int[n+1];
        Arrays.fill(d,INF);//거리 배열 무한대로 초기화
        d[start]=0;


        PriorityQueue<Node>pq=new PriorityQueue<>();

        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){

            Node poll=pq.poll();
            int now=poll.getIndex();
            int dist=poll.getDistance();

            if(d[now]<dist){
                continue;//더 작은 거리로 갱신된 적 있으면 넘어간다.
            }

            for(int i=0;i<graph.get(now).size();i++){

                //경유할 떄의 비용
                int cost=d[now]+graph.get(now).get(i).getDistance();

                //경유하는 게 더 짧은 경우 갱신한다.
                if(cost<d[graph.get(now).get(i).getIndex()]){
                    d[graph.get(now).get(i).getIndex()]=cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(),cost));
                }


            }
            

        }



        return d[end];


    }
}
