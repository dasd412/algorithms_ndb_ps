package graph;

import java.io.*;
import java.util.*;

public class PlanetTunnel {
    static BufferedWriter bw;


    static class Dimension implements  Comparable<Dimension>{

        int x,y;

        public Dimension(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Dimension o) {
            int result=this.x-o.x;
            if(result==0){
               return this.y-o.y;
            }
            else{
                return  result;
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        int origin;
        int destination;
        int cost;

        public Edge(int origin, int destination, int cost) {
            this.origin = origin;
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "origin=" + origin +
                    ", destination=" + destination +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost-other.cost;
        }
    }

    static  int[]parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        //각 차원에 대한 정보 리스트
        List<Dimension>xs=new ArrayList<>();
        List<Dimension>ys=new ArrayList<>();
        List<Dimension>zs=new ArrayList<>();
         
        
        for (int i = 0; i <n ; i++) {

            String[]strings=br.readLine().split(" ");
            int x=Integer.parseInt(strings[0]);
            int y=Integer.parseInt(strings[1]);
            int z=Integer.parseInt(strings[2]);
            xs.add(new Dimension(x,i));
            ys.add(new Dimension(y,i));
            zs.add(new Dimension(z,i));


        }



        parent=new int[n];

        for (int i = 0; i <n ; i++) {
            parent[i]=i;//부모 배열 초기화
        }


        //각 차원을 정렬한다.

        Collections.sort(xs);
        Collections.sort(ys);
        Collections.sort(zs);




        List<Edge>edges=new ArrayList<>();

        //인접한 행성들에 대해 간선 정보를 추출한다.

        for (int i = 0; i <n-1 ; i++) {
            edges.add(new Edge(xs.get(i).y,xs.get(i+1).y,xs.get(i+1).x-xs.get(i).x));

            edges.add(new Edge(ys.get(i).y,ys.get(i+1).y,ys.get(i+1).x-ys.get(i).x));

            edges.add(new Edge(zs.get(i).y,zs.get(i+1).y,zs.get(i+1).x-zs.get(i).x));

        }


        Collections.sort(edges);//모든 간선을 오름 차순 정렬한다.

        int answer=0;

        for (int i = 0; i <edges.size() ; i++) {//모든 간선에 대해 반복한다.

            int origin=edges.get(i).origin;
            int dest=edges.get(i).destination;
            int cost=edges.get(i).cost;

            if(findParent(origin)!=findParent(dest)){//사이클이 발생하지 않는다면
                unionParent(origin,dest);//같은 집합으로 합친다.


                answer+=cost;
            }




        }


        bw.write(answer+"\n");


        br.close();
        bw.close();
    }

    private static int findParent(int i) {
        if(i==parent[i]){
            return i;
        }
        return parent[i]=findParent(parent[i]);
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

}
