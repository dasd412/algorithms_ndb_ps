package dfs_bfs;

import java.util.List;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class MovingPopulation {

    static BufferedWriter bw;

    static int n,min,max;

    static int[]dx={1,0,-1,0};
    static int[]dy={0,1,0,-1};



    static class Dimension{

        int x,y,value;

        public Dimension(int x, int y,int value){
            this.x=x;
            this.y=y;
            this.value=value;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));



        String[]splits=br.readLine().split(" ");

        n=Integer.parseInt(splits[0]);
        min=Integer.parseInt(splits[1]);
        max=Integer.parseInt(splits[2]);

        int[][]map=new int[n][n];

        for (int i = 0; i <n ; i++) {
            splits=br.readLine().split(" ");
            for (int j = 0; j <n ; j++) {
                map[i][j]=Integer.parseInt(splits[j]);
            }
        }




        int day=0;//<-출력결과. 인구 이동이 "며칠 동안 일어나는지"
        while(true){


            int changedCount=0;

            boolean[][]visited=new boolean[n][n];


            for (int i = 0; i <n ; i++) {
                for (int j = 0; j <n ; j++) {



                    if(!visited[i][j]){
                        boolean hasChanged=BFS(i,j,map,visited);

                        if(hasChanged){
                            changedCount++;
                        }
                    }







                }

            }

            if(changedCount==0){//바뀐 게 없다면,
                break;
            }
            day++;

        }





        bw.write(day+"\n");

        br.close();
        bw.close();
    }

    private static boolean BFS(int i, int j, int[][] map,boolean[][]visited) {





        Queue<Dimension> queue=new LinkedList<>();
        queue.offer(new Dimension(i,j,map[i][j]));
        visited[i][j]=true;

        List<Dimension> union=new LinkedList<>();
        union.add(new Dimension(i,j,map[i][j]));


        while(!queue.isEmpty()){

            Dimension poll=queue.poll();



            for(int a=0;a<4;a++){
                int x=poll.x+dx[a];
                int y=poll.y+dy[a];

                if(x<0||y<0||x>=n||y>=n){//유효 인덱스가 아닌 것은 스킵한다.
                    continue;
                }

                //뽑아낸 곳과 인접한 곳의 차이(절댓값)를 구한다.
                int diff=Math.abs(map[poll.x][poll.y]-map[x][y]);


                //인접한 곳이 방문한 게 아니라면, 차이가 l이상 r이하라면 연합에 추가한다.
                if(!visited[x][y]&&diff>=min&&diff<=max){
                   visited[x][y]=true;

                    Dimension target=new Dimension(x,y,map[x][y]);
                    queue.add(target);
                    union.add(target);
                }

            }//for
        }//while

        int count=union.size();






        int sum=0;

        for (int k = 0; k <union.size() ; k++) {
            sum+=union.get(k).value;
        }

        sum/=count;

        for (int k = 0; k <union.size() ; k++) {


            map[union.get(k).x][union.get(k).y]=sum;
        }

        if(count>1){
//            System.out.println(i+" "+j);
//            for (int k = 0; k <n ; k++) {
//                for (int l = 0; l <n ; l++) {
//                    System.out.print(map[k][l]+" ");
//                }
//                System.out.println();
//            }


            return true;
        }
        else{

            return false;
        }





    }
}
