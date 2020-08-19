package implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SnakeGame {
    static BufferedWriter bw;

    static final int APPLE=100001;

    static final int BODY=7;


    static final int RIGHT=1;
    static final int DOWN=2;
    static final int LEFT=3;
    static final int UP=4;

    //머리정보를 담는 배열. 각각 x,y 좌표를 뜻한다.
    static int[]head={0,0};
    
    //꼬리의 궤적 정보를 담는 연결 리스트.
    static List<Tale>tales=new LinkedList<>();

    static final int X=0;
    static final int Y=1;

    static int direction=RIGHT;//방향 설정, 첫 방향은 오른쪽

    static class Tale{
        int x;
        int y;
        public Tale(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    static class Direction{
        private int sec;
        private String dir;

        public Direction(int sec, String dir){
            this.sec=sec;
            this.dir=dir;
        }

        public String getDir() {
            return dir;
        }

        public int getSec() {
            return sec;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int k=Integer.parseInt(br.readLine());

        int[][]map=new int[n][n];

        String[]splits;
        for(int i=0;i<k;i++){
            splits=br.readLine().split(" ");
            map[Integer.parseInt(splits[0])-1][Integer.parseInt(splits[1])-1]=APPLE;
        }

        int l=Integer.parseInt(br.readLine());

        List<Direction> dirs=new ArrayList<>();

        for(int i=0;i<l;i++){
            splits=br.readLine().split(" ");
            dirs.add(new Direction(Integer.parseInt(splits[0]),splits[1]));
        }

        int gameOverSec=getGameOverSec(map,dirs);

        bw.write(gameOverSec+"\n");



        br.close();
        bw.close();
    }

    private static int getGameOverSec(int[][] map, List<Direction> dirs) {
        int sec=1;//게임 오버까지 걸리는 시간




        while(canGo(map,dirs,sec)){//해당 방향으로 현재 초에서 갈 수 있는 동안 반복한다.
            //System.out.println("sec"+sec);

            for(int k=0;k<tales.size();k++){
                map[tales.get(k).x][tales.get(k).y]=BODY;
            }
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map.length;j++){



                    /*
                    if(map[i][j]==APPLE){
                        System.out.print(5+" ");
                    }
                    else if(head[X]==i&&head[Y]==j){
                        System.out.print(1+" ");
                    }
                    else if(map[i][j]==BODY){
                        System.out.print(2+" ");
                    }
                    else{
                        System.out.print(0+" ");
                    }
                     */

                }
                //System.out.println();
            }
           // System.out.println();

            sec++;//시간은 흘러간다.
        }


        return sec;
    }

    private static boolean canGo(int[][] map, List<Direction> dirs, int sec) {

        //방향 전환이 가능하다면, 방향을 전환한다.

        if(dirs.size()>0&&sec==dirs.get(0).getSec()+1){//만약, 현재 초에서 방향 전환해야 한다면
            String dir=dirs.get(0).getDir();

            rotate(dir);
            dirs.remove(0);//첫 번째꺼 방향 전환 정보 삭제

        }



        tales.add(new Tale(head[X],head[Y]));


        //현재 방향에서 머리가 1칸 전진한다.
        if(direction==RIGHT){
            head[Y]++;

        }else if(direction==DOWN){
            head[X]++;

        }else if(direction==LEFT){

            head[Y]--;
        }else{

            head[X]--;
        }

        if(head[X]<0||head[Y]<0||head[X]>=map.length||head[Y]>=map.length){//벽 밖으로 머리가 간다면,
            return false;
        }
        if(map[head[X]][head[Y]]==BODY){//머리가 꼬리에 부딪혔다면,
            return false;
        }

        if(map[head[X]][head[Y]]==APPLE){//머리가 이동한 칸에 사과가 있다면,

            map[head[X]][head[Y]]=0;//먹어치운다.
        }
        else{//머리가 이동한 칸에 사과가 없다면,


            map[tales.get(0).x][tales.get(0).y]=0;
            tales.remove(0);//가장 마지막 궤적을 지운다.

            //꼬리 정보 갱신


        }

        boolean twisted=false;
        
        for(int i=0;i<tales.size();i++){
            if(map[head[X]][head[Y]]==BODY){
                break;
            }
        }


         if(twisted){//자기 몸에 부딪혔다면,
            return false;
        }else{//그 외의 경우에는 계속 진행 가능.
            return true;
        }



    }

    private static void rotate(String dir) {
        /*
        현재 방향에서 왼쪽이면 -1, 오른쪽이면 +1 되는 양상을 보여주고 있다.
        단, 0이나 3과 같이 범위를 넘어 설 수 있는 경우에는 %를 이용해야 할 것이다.

         */

        if(direction==RIGHT){

            if(dir.equals("L")){//현재 방향에서 왼쪽으로 꺾기

                direction=UP;
            }else{//현재 방향에서 오른쪽으러 꺾기

                direction=DOWN;
            }
        }else if(direction==DOWN){

            if(dir.equals("L")){
                direction=RIGHT;

            }else{
                direction=LEFT;

            }
        }else if(direction==LEFT){
            if(dir.equals("L")){

                direction=DOWN;
            }else{

                direction=UP;
            }

        }
        else{
            if(dir.equals("L")){

                direction=LEFT;
            }else{

                direction=RIGHT;
            }

        }

    }
}
