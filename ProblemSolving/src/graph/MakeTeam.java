package graph;

import java.io.*;

public class MakeTeam {
    static BufferedWriter bw;

    static int[]parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);


        parent=new int[n+1];

        for(int i=0;i<parent.length;i++){
            parent[i]=i;//부모 테이블 초기화. 부모를 자기 자신으로 해놓는다.
        }

        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            int command=Integer.parseInt(splits[0]);
            int a=Integer.parseInt(splits[1]);
            int b=Integer.parseInt(splits[2]);

            if(command==0){

                unionTeam(a,b);
            }
            else{

                if(findParent(a)==findParent(b)){
                    bw.write("YES\n");
                }
                else{
                    bw.write("NO\n");
                }

            }
        }


        br.close();
        bw.close();
    }

    private static int findParent(int a) {
        if(a==parent[a])return a;

        return parent[a]=findParent(parent[a]);
    }

    private static void unionTeam(int a, int b) {
        a=parent[a];
        b=parent[b];
        if(a<b)parent[b]=a;
        else parent[a]=b;
    }
}
