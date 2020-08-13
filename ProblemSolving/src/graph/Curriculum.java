package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Curriculum {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]indegree=new int[n+1];
        int[]hours=new int[n+1];

        List<ArrayList<Integer>> graph=new ArrayList<>();

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=1;i<=n;i++){//강의 번호는 1부터 n까지로 되어 있다.
            String[] splits=br.readLine().split(" ");
            int hour=Integer.parseInt(splits[0]);
            int first=Integer.parseInt(splits[1]);


            hours[i]=hour;//i번째 강의의 강의 시간을 담아 놓는다.

            if(first!=-1){//i번쨰 과목을 듣기 위해 선수 과목이 존재 한다면 진입 차수 증가.
                indegree[i]++;
                graph.get(first).add(i);//선수과목에서 i번째 강의 번호로 이동하는 경로가 있다.
            }

        }

        topologySort(indegree,hours,graph);//위상 정렬



        br.close();
        bw.close();
    }

    private static void topologySort(int[] indegree, int[] hours, List<ArrayList<Integer>> graph)  throws IOException{



        Queue<Integer>queue=new LinkedList<>();

        int[]list=new int[hours.length];
        for(int i=0;i<hours.length;i++){
            list[i]=hours[i];
        }

        //진입 차수가 0인 강의 번호를 담아넣는다.
        for(int i=1;i<indegree.length;i++){
            if(indegree[i]==0 ){
                queue.offer(i);
            }
        }

        //큐가 빌 때까지 반복한다.
        while(!queue.isEmpty()){

            int now=queue.poll();


            //뽑은 now에서 출발하는 간선들에 대하여
            for(int i=0;i<graph.get(now).size();i++){
                indegree[graph.get(now).get(i)]--;//진입 차수를 1 감소시킨다.
                list[graph.get(now).get(i)]=Math.max(list[graph.get(now).get(i)],list[now]+hours[graph.get(now).get(i)]);
                if(indegree[graph.get(now).get(i)]==0){//진입 차수가 0이면 큐에 담는다.
                    queue.offer(graph.get(now).get(i));
                }

            }

        }

        for(int i=1;i<list.length;i++){
            bw.write(list[i]+"\n");
        }




    }
}
