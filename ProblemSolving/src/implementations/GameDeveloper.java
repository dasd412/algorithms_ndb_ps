package implementations;

import java.io.*;

public class GameDeveloper {//백준-로봇청소기 문제랑 똑같음... 다시 풀어 봤을 떄 못 풀었으므로 다시 풀어봐야 함!!

    static BufferedWriter bw;




    static final int NORTH=0;
    static final int EAST=1;
    static final int SOUTH=2;
    static final int WEST=3;




    static final int BACK=-2;
    static final int END=-3;


    static int n,m;

    public static void main(String[] args)throws  IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));




        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int x,y, dir;

        splits=br.readLine().split(" ");
        x=Integer.parseInt(splits[0]);
        y=Integer.parseInt(splits[1]);
        dir=Integer.parseInt(splits[2]);

        int[][]map=new int[n][m];
        boolean[][]visited=new boolean[n][m];//방문여부를 나타내는 데이터 배열

        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<m;j++){

                map[i][j]=Integer.parseInt(splits[j]);
                if(map[i][j]==1){//만약 벽이라면, 방문했다고 가정합니다.
                    visited[i][j]=true;
                }
            }
        }

        int count=turnOnRobot(map,visited,x,y,dir);

        bw.write(Integer.toString(count)+"\n");




        br.close();
        bw.close();

    }

    private static int turnOnRobot(int[][] map, boolean[][] visited, int x, int y, int dir) {
        boolean isTurnOn=true;

        int count=0;//청소한 방의 개수를 나타냅니다.

        if(visited[x][y]==false){//시작 지점이 벽이 아니면 먼저 청소한다.
            count++;
            visited[x][y]=true;
        }

        while(isTurnOn==true){//켜져 있는 동안 반복한다.


            //2.현재 위치, 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 한다.
            /*
            리턴 값 유형
            0,1,2,3:북,동,남,서

            -2:네 방향 모두 청소 되있거나 벽인 경우
            -3:후진도 할 수 없는 경우, 즉 작동 종료 조건인 경우
             */
            int direction=searchDirection(visited,map,x,y,dir);



            if(direction==BACK){//-2, 즉 후진인 경우

                if(dir==NORTH){//현재 바라보는 기준이 북쪽이었다면,

                    x=x+1;//남으로 후진

                }else if(dir==WEST){

                    y=y+1;

                }else if(dir==SOUTH){

                    x=x-1;

                }else{

                    y=y-1;
                }


            }else if(direction==END){//-3, 후진도 할 수 없는 경우에는
                isTurnOn=false;//반복을 종료한다.

            }else{//0,1,2,3, 동서남북 네 방향 중 아무데라도 갈 수 있다면,
                dir=direction;//방향을 갱신하고

                if(dir==NORTH){// 그 방향이 북쪽이라면

                    x=x-1;//북쪽으로 갑니다.


                }else if(dir==WEST){


                    y=y-1;


                }else if(dir==SOUTH){


                    x=x+1;

                }else{

                    y=y+1;

                }
                //1. 현재 위치를 청소한다.
                visited[x][y]=true;

                count++;

            }


        }


        return count;
    }

    private static int turnLeft(int dir) {//현재 방향에 대하여 왼쪽 방향으로 바라보는 기준을 회전하는 메소드

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


    private static int searchDirection(boolean[][]visited,int[][] map, int x, int y, int dir) {
        //2.에 해당하는 메서드

        int wallCount=0;//벽의 개수를 나타냅니다.
        int originDir=dir;

        for(int i=0;i<4;i++) {//4방향에 대해 탐색한다.

            dir=turnLeft(dir);
            if(dir==NORTH){
                if(x-1>0){//벽이 아닌 경우

                    if(visited[x-1][y]==false){//청소하지 않은 경우

                        return NORTH;//북쪽 방향으로 전진하면 됨.

                    }else{//청소된 곳은 벽이나 다름없다.
                        wallCount++;
                    }

                }else{//벽인 경우

                    wallCount++;
                }

            }else if(dir==WEST){

                if(y-1>0){

                    if(visited[x][y-1]==false){
                        return WEST;
                    }
                    else{
                        wallCount++;
                    }

                }else{
                    wallCount++;
                }

            }else if(dir==SOUTH){

                if(x+1<n){

                    if(visited[x+1][y]==false){
                        return SOUTH;
                    }else{
                        wallCount++;
                    }
                }
                else{
                    wallCount++;
                }

            }else{

                if(y+1<m){

                    if(visited[x][y+1]==false){
                        return EAST;
                    }
                    else{
                        wallCount++;
                    }
                }
                else{
                    wallCount++;
                }
            }
        }

        if(wallCount>=4){//4 방향 모두 청소 안한 곳이 아니라면,


            return goBack(x,y,originDir,map,visited);//후진해야 할지, 종료해야 할지 따진다.

        }

        return END;


    }

    private static int goBack(int x, int y,int originDir,int[][]map,boolean[][]visited) {
        /*
        주의할 점
        네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
        라는 조건에서 알 수 있듯이, 후진 할 때 벽만 아니라면 후진은 계속 할 수 있다.
         */
        if(originDir==NORTH){

            if(x+1<n&&map[x+1][y]!=1){//후진할 수 있는 경우
                return BACK;
            }else{
                return END;
            }

        }else if(originDir==WEST){

            if(y+1<m&&map[x][y+1]!=1){
                return BACK;
            }
            else{
                return END;
            }

        }else if(originDir==SOUTH){

            if (x - 1 > 0&&map[x-1][y]!=1) {

                return BACK;
            }
            else{
                return END;
            }
        }else{

            if(y-1>0&&map[x][y-1]!=1){
                return BACK;
            }
            else{
                return END;
            }
        }
    }


}
