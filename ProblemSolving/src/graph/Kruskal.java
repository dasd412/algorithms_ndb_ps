package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    static BufferedWriter bw;
    /*
    최소 신장 트리를 찾아내는 알고리즘
     */

    //간선 클래스
    static class Edge implements Comparable<Edge>{

        private int distance;//비용
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        @Override
        public int compareTo(Edge other) {
            if(this.distance<other.distance){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    //정점의 개수 v와 간선의 개수 e
    static int v,e;

    //부모 테이블 초기화
    static int[]parent=new int[100001];

    //모든 간선을 담을 리스트
    static List<Edge> edges=new ArrayList<>();

    //최소 신장 트리의 최종 비용을 담을 변수
    static int result=0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");

        v=Integer.parseInt(splits[0]);
        e=Integer.parseInt(splits[1]);

        for(int i=1;i<=v;i++){
            parent[i]=i;//부모 테이블 상에서 부모를 자기 자신으로 초기화
        }

        for(int i=0;i<e;i++){
            //모든 간선에 대한 정보 입력받기
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);
            int cost=Integer.parseInt(splits[2]);

            edges.add(new Edge(cost,a,b));

        }

        //1.간선을 비용순으로 정렬한다. 시간 복잡도는 O(n log n)
        Collections.sort(edges);

        //2.간선을 하나씩 확인하며
        for(int i=0;i<edges.size();i++){
            int cost=edges.get(i).getDistance();
            int a=edges.get(i).getNodeA();
            int b=edges.get(i).getNodeB();

            //2.1. 사이클이 발생하지 않는 경우에만 집합 포함
            if(findParent(a)!=findParent(b)){
                unionParent(a,b);
                result+=cost;
            }

        }

        //총 시간 복잡도는 O(n log n)
        bw.write(Integer.toString(result)+"\n");


        br.close();
        bw.close();
    }

    private static void unionParent(int a, int b) {

        a=findParent(a);
        b=findParent(b);
        if(a<b)parent[b]=a;
        else parent[a]=b;

    }

    private static int findParent(int a) {
        if(parent[a]==a){
            return a;
        }
        return parent[a]=findParent(parent[a]);
    }
}
