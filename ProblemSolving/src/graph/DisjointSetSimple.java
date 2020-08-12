package graph;

import java.io.*;

public class DisjointSetSimple {
    /*
    간단한 서로소 집합 알고리즘 코드
     */

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //부모 테이블 초기화
        int[] parent=new int[100001];
        int v,e;//v는 노드의 개수, 간선의 개수 e(union 연산)

        String[]splits=br.readLine().split(" ");
        v=Integer.parseInt(splits[0]);
        e=Integer.parseInt(splits[1]);

        for(int i=1;i<=v;i++){
            parent[i]=i;//부모 테이블 상에서 부모를 자기 자신으로 초기화해놓는다.
        }

        //Union 연산을 각각 수행한다.

        for(int i=0;i<e;i++){
            splits=br.readLine().split(" ");
            int a=Integer.parseInt(splits[0]);
            int b=Integer.parseInt(splits[1]);
            unionParent(a,b,parent);

        }

        //각 원소가 속한 집합 출력
        bw.write("각 원소가 속한 집합: ");
        for(int i=1;i<=v;i++){
            bw.write(findParent(i,parent)+" ");
        }
        bw.write("\n");

        bw.write("부모 테이블: ");

        for(int i=1;i<=v;i++){
            bw.write(parent[i]+ " ");
        }
        bw.write("\n");



        
        br.close();
        bw.close();
    }

    private static int findParent(int i, int[] parent) {
        //루트 노드가 아닌 경우, 루트 노드를 찾을 때까지 재귀 호출
        if(i==parent[i])return i;

        return findParent(parent[i],parent);
    }

    private static void unionParent(int a, int b, int[] parent) {
        a=findParent(a,parent);
        b=findParent(b,parent);
        if(a<b)parent[b]=a;
        else parent[a]=b;

    }
}
