package graph;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologySort {

    /*
    방향 그래프의 모든 노드를 방향성에 거스르지 않도록 순서대로 나열하는 알고리즘.
     */
    static BufferedWriter bw;

    //노드의 개수 v와 간선의 개수 e
    static int v,e;

    //모든 노드에 대한 진입 차수는 0으로 초기화
    static int[]indegree;

    //각 노드에 연결된 간선 정보를 담기 위한 리스트
    static List<ArrayList<Integer>> graph=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[]splits=br.readLine().split(" ");
        v=Integer.parseInt(splits[0]);
        e=Integer.parseInt(splits[1]);

        indegree=new int[v+1];

        //그래프 초기화
        for(int i=0;i<=v;i++){
            graph.add(new ArrayList<>());
        }

        //방향 그래프의 모든 간선 정보를 입력 받기
        for(int i=0;i<e;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);

            graph.get(a).add(b);//정점 a에서 b로 이동 가능

            //진입 차수 1증가, 진입 차수란 어떤 노드로 들어오는 간선의 개수를 뜻한다.
            indegree[b]+=1;


        }


        topologySort();

        br.close();
        bw.close();
    }

    private static void topologySort() throws IOException {
        //알고리즘 수행 결과를 담을 리스트
        List<Integer>result=new ArrayList<>();

        //큐 라이브러리
        Queue<Integer> queue=new LinkedList<>();

        //처음 시작할 때는 진입 차수가 0인 노드를 큐에 삽입
        for(int i=1;i<=v;i++){
            if(indegree[i]==0){

                queue.offer(i);
            }
        }

        //큐가 빌 때까지 반복한다.
        while(!queue.isEmpty()){

            //큐에서 원소를 꺼낸다.
            int now=queue.poll();
            result.add(now);

            //해당 원소와 연결된 노드들의 진입 차수에서 1빼기

            for(int i=0;i<graph.get(now).size();i++){
                indegree[graph.get(now).get(i)]-=1;

                //새롭게 진입 차수가 0이 되는 노드를 큐에 넣는다.
                if(indegree[graph.get(now).get(i)]==0){
                    queue.offer(graph.get(now).get(i));
                }
            }


        }


        for(int i=0;i<result.size();i++){
            bw.write(result.get(i)+" ");
        }
        bw.write("\n");

    }
}
