package implementations;

import java.io.*;

public class GameDeveloper_Re {

    static BufferedWriter bw;

    static final int NORTH=0;
    static final int WEST=3;
    static final int SOUTH=2;
    static final int EAST=1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;

        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int x,y, dir;

        splits=br.readLine().split(" ");
        x=Integer.parseInt(splits[0]);
        y=Integer.parseInt(splits[1]);
        dir=Integer.parseInt(splits[2]);

        int[][]map=new int[n][m];
        boolean[][]visited=new boolean[n][m];

        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<m;j++){

                map[i][j]=Integer.parseInt(splits[j]);
                if(map[i][j]==1){
                    visited[i][j]=true;
                }
            }
        }

        int visitCount=getVisitCount(x,y,dir,n,m,map,visited);


        bw.write(Integer.toString(visitCount)+"\n");


        br.close();
        bw.close();
    }

    private static int getVisitCount(int x, int y, int direction, int n, int m, int[][] map, boolean[][] visited) {
        int visitCount=0;

        //첫 칸은 반드시 육지이고 방문했다고 표시한다.
        visited[x][y]=true;
        visitCount++;

        boolean isNotEnd=true;//반복문 종료 플래그

        int dir=direction;

        while(isNotEnd){


            boolean isAnyDirection=false;//갈 수 있는 방향이 4군데 중 하나라도 존재하는 지 여부를 따짐.


            for(int i=0;i<4;i++){//4번 반복한다.

                dir=getDir(dir);//왼쪽으로 방향을 회전한다.


                if(canGo(x,y,dir,n,m,visited)){//회전한 방향 중 갈 수 있는 방향이 있다면, 그 쪽으로 가고, 방향 회전은 멈춘다.


                    if(dir==NORTH){
                        x=x-1;
                    }else if(dir==WEST){

                        y=y-1;

                    }else if(dir==SOUTH){

                        x=x+1;
                    }
                    else{

                        y=y+1;
                    }
                    //간 곳은 방문했다고 표시하고, 방문 횟수를 더해준다.

                    visited[x][y]=true;
                    visitCount++;

                    isAnyDirection=true;//갈 곳을 찾았다는 플래그

                    break;
                }

            }


            if(!isAnyDirection){//4방향 중 아무데도 갈 곳을 못 찾았다면,

                //현재 방향을 유지한채로<----문제의 핵심!!!

                    if(dir==NORTH){

                        if(x+1<n) {
                           if(map[x+1][y]==1){//바다면 멈춘다
                               isNotEnd=false;
                           }
                           else{//바다가 아니면 남쪽으로 후진한다.
                               x=x+1;
                           }


                        }


                    }else if(dir==WEST){

                        if(y+1<m){

                            if(map[x][y+1]==1){
                                isNotEnd=false;
                            }
                            else{
                                y=y+1;
                            }
                        }


                    }else if(dir==SOUTH){
                        if(x-1>=0){

                            if(map[x-1][y]==1){
                                isNotEnd=false;
                            }
                            else{
                                x=x-1;
                            }
                        }


                    }
                    else{

                        if(y-1>=0){

                            if(map[x][y-1]==1){
                                isNotEnd=false;
                            }
                            else{
                                y=y-1;
                            }
                        }


                    }


            }


        }

        return visitCount;
    }



    private static boolean canGo(int x, int y, int dir, int n, int m, boolean[][] visited) {
        //해당 방향으로 갈 수 있는지 판별하는 메소드
        if(dir==NORTH){//북쪽이고

            if(x-1>=0&&visited[x-1][y]==false){//북쪽 방향이 바다가 아니고 방문한 적 없는 육지라면, 갈 수 있다.
                return true;
            }
            else{
                return false;
            }

        }else if(dir==WEST){

            if(y-1>=0&&visited[x][y-1]==false){
                return true;
            }
            else{
                return false;
            }

        }else if(dir==SOUTH){

            if(x+1<n&&visited[x+1][y]==false){
                return true;
            }
            else{
                return false;
            }
        }
        else{

            if(y+1<m&&visited[x][y+1]==false){
                return true;
            }
            else{
                return false;
            }

        }
    }

    private static int getDir(int dir) {//왼쪽으로 방향을 회전하는 메소드
        if(dir==NORTH){
            return WEST;
        }
        else if(dir==WEST){
            return SOUTH;
        }
        else if(dir==SOUTH){
            return EAST;
        }
        else{
            return NORTH;
        }
    }
}
