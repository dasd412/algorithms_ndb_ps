package shortest_path;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra_Efficient {

    /*
    힙 기반 우선순위큐를 사용해서
    시간 복잡도가 O(E * logV)이므로 효율적인 알고리즘이다. 여기서 E는 간선의 개수, V는 노드의 개수다.
     */
    static BufferedWriter bw;

    static class Node implements Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index, int distance){
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
            if(this.distance<other.distance){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    static final int INF=(int)1e9;

    static int n,m,start;

    static List<ArrayList<Node>> graph=new ArrayList<>();

    static int[]d=new int[100001];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        start=Integer.parseInt(br.readLine());

        //그래프를 초기화한다.

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<Node>());
        }

        //모든 간선 정보 입력받기

        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            graph.get(Integer.parseInt(splits[0])).add(new Node(Integer.parseInt(splits[1]),Integer.parseInt(splits[2])));
        }

        //최단 거리 테이블 무한 대로 초기화한다.
        Arrays.fill(d,INF);

        //출발점부터 시작해서 다익스트라 알고리즘을 실행한다.
        dijkstra(start);

        //모든 노드로 가기 위한 최단거리 출력
        for(int i=1;i<=n;i++){
            if(d[i]==INF){//도달 할 수 없는 노드인 경우
                System.out.println("Can't reach!");
            }
            else{
                System.out.println(i+"'s shortest path is ' "+d[i]);
            }
        }


        br.close();
        bw.close();
    }

    private static void dijkstra(int start) {

        PriorityQueue<Node>pq=new PriorityQueue<>();

        //시작 노드 최단 경로는 0으로 설정하고 큐에 넣는다.
        pq.offer(new Node(start,0));
        d[start]=0;

        while(!pq.isEmpty()){//큐에 원소가 담겨 있는 동안 반복한다.

            //최단 거리가 가장 짧은 노드 꺼내기
            Node node=pq.poll();
            int distance=node.getDistance();//현재 노드까지의 비용
            int now=node.getIndex();//현재 노드

            //현재 노드가 이미 처리된 적 있으면 무시한다.
            if(d[now]<distance)continue;

            for(int i=0;i<graph.get(now).size();i++){

                int cost=d[now]+graph.get(now).get(i).getDistance();//경유하는 경우의 비용

                if(cost<d[graph.get(now).get(i).getIndex()]){
                    //경유하는 것이 더 짧다면 갱신하고 큐에 담는다.
                    d[graph.get(now).get(i).getIndex()]=cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(),cost));

                }

            }


        }


    }
}
