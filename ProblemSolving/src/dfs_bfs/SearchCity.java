package dfs_bfs;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SearchCity {

    static BufferedWriter bw;

    static class Dir{
        private int origin;


        public Dir(int origin){
            this.origin=origin;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m,k,x;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);
        k=Integer.parseInt(splits[2]);
        x=Integer.parseInt(splits[3]);

        List<ArrayList<Integer>> map=new ArrayList<>();

        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);
            map.get(a).add(b);

        }


        BFS(n,m,k,x,map);

        br.close();
        bw.close();
    }

    private static void BFS(int n, int m, int k, int x, List<ArrayList<Integer>> map) throws IOException{
        Queue<Dir> queue=new LinkedList<>();
        //시작 정점을 담는다.
        queue.add(new Dir(x));

        //최단 거리 배열을 만든다.
        int[]distances=new int[n+1];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[x]=0;

        while(!queue.isEmpty()){

            Dir poll=queue.poll();

            int origin=poll.origin;//시작 정점에 대하여
            for(int i=0;i<map.get(origin).size();i++){//목적지들을 탐색해본다.
               //시작에서 목적지까지 가는 경로가 있다면,

                int dest=map.get(origin).get(i);

                if(distances[dest]==Integer.MAX_VALUE){//방문한 적이 없다면,
                    //목적지 최단거리를 갱신한다.
                    distances[dest]=distances[origin]+1;
                    queue.add(new Dir(dest));//큐에 목적지 정점을 넣는다.
                }





            }

        }

        boolean has=false;

        for(int i=1;i<distances.length;i++){
            if(distances[i]==k){
                has=true;
                bw.write(i+"\n");
            }
        }

        if(!has){
            bw.write(-1+"\n");
        }


    }
}
