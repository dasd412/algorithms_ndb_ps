package graph;

import java.io.*;
import java.util.*;

public class DarkRoad {

    static BufferedWriter bw;

    static class Edge implements Comparable<Edge>{

        int x;
        int y;
        int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost-other.cost;
        }
    }

    static int[]parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n,m;

        String[]strings=br.readLine().split(" ");
        n=Integer.parseInt(strings[0]);
        m=Integer.parseInt(strings[1]);

        parent=new int[n];
        for (int i = 0; i <n ; i++) {
            parent[i]=i;//부모 배열은 자기 자신으로 초기화.
        }

        List<Edge>edges=new ArrayList<>();

        int totalCost=0;

        for (int i = 0; i <m ; i++) {
            strings=br.readLine().split(" ");
            int x=Integer.parseInt(strings[0]);
            int y=Integer.parseInt(strings[1]);
            int z=Integer.parseInt(strings[2]);
            totalCost+=z;


            edges.add(new Edge(x,y,z));

        }

        Collections.sort(edges);//간선에 대해 비용 순으로 오름 차순 정렬한다.

        int realCost=0;

        for (int i = 0; i <m ; i++) {//간선들에 대해 반복한다.

            //간선 하나에 연결된 두 노드에 대하여
            int x=edges.get(i).x;
            int y=edges.get(i).y;

            if(findParent(x)!=findParent(y)){// 두 노드의 부모 노드가 다르면, 즉 서로소 집합이면
                unionParent(x,y);//두 노드의 부모를 같게 하여 같은 집합에 속하게 한다.
                realCost+=edges.get(i).cost;

            }



        }

        bw.write((totalCost-realCost)+"\n");


        br.close();
        bw.close();
    }

    private static void unionParent(int x, int y) {
        x=findParent(x);
        y=findParent(y);
        if(x<y){
            parent[y]=x;
        }
        else{
            parent[x]=y;
        }

    }

    private static int findParent(int i) {
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParent(parent[i]);
    }

}
