package graph;

import java.io.*;
import java.util.*;

public class TravelPlan {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]strings=br.readLine().split(" ");
        n=Integer.parseInt(strings[0]);
        m=Integer.parseInt(strings[1]);

        int[][]map=new int[n][n];

        for (int i = 0; i <n ; i++) {
            strings=br.readLine().split(" ");
            for (int j = 0; j <n ; j++) {
                map[i][j]=Integer.parseInt(strings[j]);
            }
        }

        int[]plan=new int[m];

        strings=br.readLine().split(" ");

        for (int i = 0; i <m ; i++) {
            plan[i]=Integer.parseInt(strings[i]);
        }

        boolean canTravel=canTravel(map,plan,n,m);

        if(canTravel){
            bw.write("YES\n");
        }
        else{
            bw.write("NO\n");
        }


        br.close();
        bw.close();
    }

    private static boolean canTravel(int[][] map, int[] plan, int n, int m) {

        /*
       여행 계획 장소들이 같은 집합에 존재한다면, 여행을 할 수 있다.
       그러나 다른 집합, 즉 서로소이면 여행을 할 수가 없다.

         */

        List<Integer>list=new ArrayList<>();

        for (int i = 0; i <m ; i++) {
            if(!list.contains(plan[i])){
                list.add(plan[i]);
            }
        }




        int[]parent=new int[n+1];

        for (int i = 1; i <=n ; i++) {
            parent[i]=i;//부모 배열은 자기 자신을 가리키도록 초기화한다.
        }

        for (int i = 0; i <list.size()-1 ; i++) {//union 연산을 수행한다.

            int a=list.get(i);
            int b=list.get(i+1);

            unionParent(a,b,parent);


        }



        int vertex=list.get(0);
        int vertexParent=parent[vertex-1];

        boolean isSameGroup=true;

        for (int i = 1; i <list.size() ; i++) {

            if(vertexParent==parent[list.get(i)-1]){

                isSameGroup=false;
                break;
            }


        }



        return isSameGroup;
    }

    private static void unionParent(int a, int b, int[] parent) {
        a=findParent(a,parent);
        b=findParent(b,parent);
        
        if(a<b){
            parent[b]=a;
        }
        else{
            parent[a]=b;
        }


    }

    private static int findParent(int i, int[] parent) {
       if(i==parent[i]){
           return i;//루트 노드를 찾을 때까지 재귀 호출
       }
       return parent[i]=findParent(parent[i],parent);

    }

}
