package graph;

import java.io.*;

public class Gate {

    static BufferedWriter bw;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g=Integer.parseInt(br.readLine());
        int p=Integer.parseInt(br.readLine());

        int[]parent=new int[g+1];

        for (int i = 0; i <=g ; i++) {
            parent[i]=i;//부모 배열 초기화
        }

        int max=0;

        for (int i = 0; i <p ; i++) {
            int gate=Integer.parseInt(br.readLine());
            int root=findParent(gate,parent);//해당 게이트의 루트 노드 확인
            if(root==0){
                break;//루트 노드가 0이면 더 이상 도킹할 수 없으므로 끝낸다.
            }
            unionParent(root,root-1,parent);//해당 게이트 루트 노드와 그 왼쪽 노드를 합연산한다.

            max++;




        }

        bw.write(max+"\n");



        br.close();
        bw.close();
    }


    private static void unionParent(int a, int b,int[]parent) {

        a=findParent(a,parent);
        b=findParent(b,parent);

        if(a<b){
            parent[b]=a;
        }
        else{
            parent[a]=b;
        }

    }

    private static int findParent(int gate, int[] parent) {
        if(parent[gate]==gate){
            return  gate;
        }
       return findParent(parent[gate],parent);

    }


}
