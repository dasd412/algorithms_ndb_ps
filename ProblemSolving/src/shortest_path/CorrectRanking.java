package shortest_path;

import java.util.*;
import java.io.*;



public class CorrectRanking {
    static BufferedWriter bw;

    static int[]linkCount;

    static class Node implements Comparable<Node>{

        int index;

        int distance;

        public Node(int index,int distance){
            this.index=index;
            this.distance=distance;
        }


        @Override
        public int compareTo(Node other) {
            return this.distance-other.distance;
        }
    }

    static List<ArrayList<Node>> graph=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]strings=br.readLine().split(" ");
        int n,m;

        n=Integer.parseInt(strings[0]);
        m=Integer.parseInt(strings[1]);


        linkCount=new int[n];

        for (int i = 0; i <n ; i++) {
            graph.add(new ArrayList<Node>());
        }


        for (int i = 0; i <m ; i++) {
            strings=br.readLine().split(" ");
            int a=Integer.parseInt(strings[0]);
            int b=Integer.parseInt(strings[1]);

            graph.get(a-1).add(new Node(b-1,1));

        }

        int correct=getCorrectRanking(n,m);

        bw.write(correct+"\n");



        br.close();
        bw.close();
    }

    private static int getCorrectRanking(int n, int m) {

        int count=0;

        for (int i = 0; i <n ; i++) {//모든 정점에 대해 반복한다.


            goThrough(i,n,m);//각 정점부터 시작하여 갈 수 있는 곳을 모두 가본다.
        }

        System.out.println();
        for (int i = 0; i <linkCount.length ; i++) {
            System.out.print(linkCount[i]+" ");
            if(linkCount[i]==n-1){//만약 연결되어 있는 개수가 정점의 개수-1, 즉 자기 자신을 제외한 모든 정점에 대해 연결되어 있다면
                count++;//성적 순위를 정확하게 알 수 있는 것이다.
            }
        }
        System.out.println();

        return count;
    }

    private static void goThrough(int start,  int n, int m) {//시작점에서 출발하여 갈 수 있는 곳을 다 가보는 메소드
        int[]d=new int[n];
        Arrays.fill(d,(int)1e9);

        d[start]=0;

        PriorityQueue<Node>pq=new PriorityQueue<>();

        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            Node poll=pq.poll();

            int now=poll.index;
            int distance=poll.distance;

            if(d[now]<distance){
                continue;
            }

            for (int i = 0; i <graph.get(now).size() ; i++) {

                int cost=d[now]+graph.get(now).get(i).distance;

                if(cost<d[graph.get(now).get(i).index]){
                    d[graph.get(now).get(i).index]=cost;

                    pq.add(new Node(graph.get(now).get(i).index,cost));
                }

            }

        }

        for (int i = 0; i <n ; i++) {

            if(d[i]==(int)1e9||d[i]==0){
                System.out.print("V ");
            }
            else{//방문한 적 있는 것에 대해서 시작점과 도착점을 체크하여 서로 연결되어 있음을 기록한다.
                System.out.print(d[i]+" ");
                linkCount[start]++;//시작점을 방문했다고 체크
                linkCount[i]++;//도착점을 방문했다고 체크

            }


        }
        System.out.println();




    }


}
