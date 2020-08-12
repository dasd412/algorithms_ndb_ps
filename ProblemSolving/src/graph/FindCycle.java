package graph;

import java.io.*;

public class FindCycle {
    static BufferedWriter bw;
    /*
    무방향 그래프에만  적용 가능한 사이클 판별 알고리즘.

     */

    //노드이 개수 v와 간선의 개수 e
    static int v,e;

    //부모 테이블 초기화
    static int[]parent=new int[100001];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");

        v=Integer.parseInt(splits[0]);
        e=Integer.parseInt(splits[1]);

        for(int i=1;i<=v;i++){
            parent[i]=i;//부모 테이블 상에서 부모를 자기 자신으로 초기화
        }

        boolean cycle=false;//사이클 발생 여부를 따지는 플래그

        for(int i=0;i<e;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);
            //사이클이 발생하면 종료한다.
            if(findParent(a)==findParent(b)){
                cycle=true;
                break;
            }
            else{
                //사이클이 발생하지 않으면 합집합 연산 수행

                unionParent(a,b);
            }

        }

        if(cycle){
            bw.write("cycle happened");
        }
        else{
            bw.write("cycle not happened");
        }

        br.close();
        bw.close();
    }

    private static void unionParent(int a, int b) {
        a=parent[a];
        b=parent[b];
        if(a<b){

            parent[b]=a;
        }
        else{
            parent[a]=b;

        }

    }

    private static int findParent(int a) {
        if(parent[a]==a){
            return a;
        }
        return parent[a]=findParent(parent[a]);
    }
}
