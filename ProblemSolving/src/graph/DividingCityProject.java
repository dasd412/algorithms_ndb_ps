package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DividingCityProject {
    static BufferedWriter bw;

    static class Edge implements Comparable<Edge>{

        private int cost;
        private int a;
        private int b;

        public Edge(int a , int b, int cost){
            this.a=a;
            this.b=b;
            this.cost=cost;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge other) {
            if(this.cost<other.cost){
                return -1;
            }
            return 1;
        }
    }
    static int n,m;

    static List<Edge> edges=new ArrayList<>();

    static int[]parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        parent=new int[n+1];

        for(int i=1;i<=n;i++){
            parent[i]=i;
        }

        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);
            int cost=Integer.parseInt(splits[2]);
            edges.add(new Edge(a,b,cost));
        }

        //간선을 비용 순으로 오름 차순 정렬한다.
        Collections.sort(edges);

        //크루스칼 알고리즘 진행

        int result=0;

        for(int i=0;i<edges.size();i++){



            if(findParent(edges.get(i).getA())!=findParent(edges.get(i).getB())) {//서로소 집합인지 확인 == 사이클이 있는지 확인
                unionParent(edges.get(i).getA(), edges.get(i).getB());//사이클이 없으면 그룹으로 합친다.

                result += edges.get(i).cost;
                n--;
                if(n<=2)break;
            }

        }

        bw.write(Integer.toString(result)+"\n");




        br.close();
        bw.close();
    }







    private static int findParent(int a) {
        if(parent[a]==a) return a;
        return parent[a]=findParent(parent[a]);
    }

    private static void unionParent(int a, int b) {
        a=findParent(a);
        b=findParent(b);
        if(a<b)parent[b]=a;
        else parent[a]=b;
    }
}
