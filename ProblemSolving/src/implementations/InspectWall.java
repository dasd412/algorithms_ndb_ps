package implementations;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class InspectWall {
    static BufferedWriter bw;


    static int answer=-1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int weak_length=Integer.parseInt(br.readLine());
        int[]weak=new int[weak_length];

        String[]splits=br.readLine().split(" ");

        for(int i=0;i<weak_length;i++){
            weak[i]=Integer.parseInt(splits[i]);
        }

        int dist_length=Integer.parseInt(br.readLine());
        int[]dist=new int[dist_length];
        splits=br.readLine().split(" ");
        for(int i=0;i<dist_length;i++){
            dist[i]=Integer.parseInt(splits[i]);
        }

        solution(n,weak,dist);

        bw.write(answer+"\n");

        br.close();
        bw.close();
    }

    static final int WEAK=-1;

    private static int solution(int n, int[] weak, int[] dist) {

        //1개부터 dist의 개수까지 뽑아보기

        List<Integer> results=new ArrayList<>();

        for(int r=1;r<=dist.length;r++){

            getCombination(dist,results,0,dist.length,r,weak);
        }





        return 0;
    }

    private static void getCombination(int[] dist, List<Integer> results, int index, int n, int r,int[]weak) {
        if(r==0||answer!=-1){//정답 변수가 -1이 아니면, 최솟값으로 갱신된 것이므로 다음 분기들은 볼 필요가 없다.
            System.out.println();

            for(int i=0;i<results.size();i++){
                System.out.print(results.get(i)+" ");
            }
            System.out.println();

            boolean canInspect=inspect(results,weak);

            if(canInspect){
                answer=r;//최솟값을 갱신한다.
            }

            return;
        }
        else{//n개에서 r개를 뽑는 조합 알고리즘
            for(int i=index;i<n;i++){
                results.add(dist[i]);
                getCombination(dist,results,i+1,n,r-1,weak);
                results.remove(results.size()-1);
            }
        }

    }

    private static boolean inspect(List<Integer> results, int[] weak) {
        boolean canInspect=false;

        for(int i=0;i<results.size();i++){
            int count_include=0;//포괄된 것 세는 변수
            for(int j=0;j<weak.length;j++){



                int selected_dist=results.get(i);

                //왼쪽으로 가본다.

                if(results.get(i)-weak[j]>=0){


                }
                else{

                }

                //오른쪽으로 가본다.




            }
            //만약, 포괄 개수가 취약지점 개수랑 같다면 메서드를 종료한다.
            if(count_include==weak.length){
                return true;
            }
        }



        return canInspect;
    }
}
