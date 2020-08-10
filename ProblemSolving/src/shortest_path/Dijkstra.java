package shortest_path;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {

    /*
    정점의 개수가 5000개 이하일 경우에만 사용할 수 있음...

    5000개를 넘어갈 경우 우선 순위 큐를 이용한 더 효율적인 알고리즘을 써야함!

     */

    static BufferedWriter bw;

    static class Node{

        private int index;
        private int distance;

        public Node(int index, int distance){
            this.index=index;
            this.distance=distance;
        }

        public int getDistance() {
            return distance;
        }

        public int getIndex() {
            return index;
        }
    }

    static final int INF=(int)1e9;//무한대로 초기화할 때 씀

    static int n,m,start;//노드의 개수 n, 간선의 개수 m, 시작 노드번호 start

    //각 노드에 연결되있는 노드에 대한 정보를 담는 배열
    static List<ArrayList<Node>> graph=new ArrayList<ArrayList<Node>>();
//방문 체크용 배열
    static boolean[]visited=new boolean [100001];

    //최단 거리 테이블

    public static int[]distance=new int[100001];


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
        Arrays.fill(distance,INF);

        //출발점부터 시작해서 다익스트라 알고리즘을 실행한다.
        dijkstra(start);

        //모든 노드로 가기 위한 최단거리 출력
        for(int i=1;i<=n;i++){
            if(distance[i]==INF){//도달 할 수 없는 노드인 경우
                System.out.println("Can't reach!");
            }
            else{
                System.out.println(i+"'s shortest path is ' "+distance[i]);
            }
        }





        br.close();
        bw.close();
    }

    private static void dijkstra(int start) {
        //시작 노드 초기화
        distance[start]=0;
        visited[start]=true;
        for(int i=0;i<graph.get(start).size();i++){
            distance[graph.get(start).get(i).getIndex()]=graph.get(start).get(i).getDistance();

        }

        //시작 노드를 제외한 n-1개의 노드에 대해 반복한다.

        for(int i=0;i<n-1;i++){
            //현재 최단 거리가 가장 짧은 노드를 꺼내서 방문 처리한다.
            int smallest=getSmallestNode();
            visited[smallest]=true;

            //현재 노드와 연결된 다른 노드를 확인한다.

            for(int j=0;j<graph.get(smallest).size();j++){
                int cost=distance[smallest]+graph.get(smallest).get(j).getDistance();

                if(cost<distance[graph.get(smallest).get(j).getIndex()]){
                    //현재 노드를 경유해서 이동하는 것이 더 빠른 경우 최단 거리 값을 갱신한다.
                    distance[graph.get(smallest).get(j).getIndex()]=cost;
                }
            }
        }


    }

    private static int getSmallestNode() {

        int minValue=INF;
        int index=0;//가장 최단 거리가 짧은 노드의 인덱스

        for(int i=1;i<=n;i++){
            if(distance[i]<minValue&&!visited[i]){
                minValue=distance[i];
                index=i;
            }
        }
        return index;
    }

}
