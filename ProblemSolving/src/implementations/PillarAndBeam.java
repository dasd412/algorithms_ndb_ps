package implementations;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

public class PillarAndBeam {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=5;

        int[][]build={{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};

        int[][]answer=solution(n,build);

        for(int i=0;i<answer.length;i++){
            for(int j=0;j<3;j++){
                System.out.print(answer[i][j]+" ");
            }
            System.out.println();
        }

        n=5;

        int[][] re_build={{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};

        int[][]re_answer=solution(n,re_build);

        for(int i=0;i<re_answer.length;i++){
            for(int j=0;j<3;j++){
                System.out.print(re_answer[i][j]+" ");
            }
            System.out.println();
        }


        br.close();
        bw.close();
    }

    static class Construction{
        private int x;
        private int y;
        private int type;

        public Construction(int x, int y, int type){
            this.x=x;
            this.y=y;
            this.type=type;

        }

        public int getType() {
            return type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean equals(int x,int y,int type){
            if(this.x==x&&this.y==y&&this.type==type){
                return true;
            }
            else{
                return false;

            }
        }
    }


    static final int BEAM=1;
    static final int PILLAR=0;

    static final int CREATE=1;
    static final int DELETE=0;

    static final int BLANK=5;


    public static int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        int[][]map=new int[n+1][n+1];//함수 그래프 형식을 배열 형식으로 전환하는 데 쓰임.
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                map[i][j]=BLANK;//초기화
            }
        }

        answer=create(map,build_frame,n);

        return answer;
    }

    public static int[][]create(int[][]map, int[][]build,int n){
        int length=map.length;
        int x,y,type,command;

        List<Construction>list=new ArrayList<>();


        int i=0;
        while(i<build.length){//빌드 배열에 대하여 순회한다.

            //빌드 배열의 요소들을 담아 놓는다.
            x=build[i][0];//배열로 따지면 y좌표다.
            y=build[i][1];//배열로 따지면 x좌표다.
            type=build[i][2];
            command=build[i][3];

            //함수 그래프 형태의 x,y를 배열로 전환한다.
            int convertY=x;
            int convertX=length-y-1;


            //변환된 해당 좌표에 구조물을 설치또는 삭제할 수 있는지 따진다.
            //가능하다면, map 배열에 변환된 정보를 저장한다.
            //또한, answer 배열에 변환되지 않은 정보를 저장한다.
            if(type==PILLAR){
                if(canDoPillar(convertX,convertY,map,command)){
                    if(command==CREATE){
                        map[convertX][convertY]=PILLAR;
                        list.add(new Construction(x,y,PILLAR));

                    }
                    else{
                        map[convertX][convertY]=BLANK;
                        //찾아내서 해당 되는 좌표는 삭제
                        int index=-1;
                        for(int a=0;a<list.size();a++){
                            if(list.get(a).equals(x,y,PILLAR)){
                                index=a;
                            }
                        }
                        if(index!=-1){
                            list.remove(index);
                        }
                    }
                }//if canDo

            }else{

                if(canDoBeam(convertX,convertY,map,command)){
                    if(command==CREATE){

                        map[convertX][convertY]=BEAM;

                        list.add(new Construction(x,y,BEAM));
                    }else{
                        map[convertX][convertY]=BLANK;

                        //찾아내서 해당 되는 좌표는 삭제
                        int index=-1;
                        for(int a=0;a<list.size();a++){
                            if(list.get(a).equals(x,y,BEAM)){
                                index=a;
                            }
                        }
                        if(index!=-1){
                            list.remove(index);
                        }

                    }

                }//if canDo

            }



            i++;
        }


        int[][]answer=new int[list.size()][3];//함수 그래프 형식으로 리턴할 예정인 배열.

        for(int a=0;a<list.size();a++){

            answer[a][0]=list.get(a).getX();
            answer[a][1]=list.get(a).getY();
            answer[a][2]=list.get(a).getType();
        }

        Arrays.sort(answer, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                int result=Integer.compare(o1[0],o2[0]);//x좌표 기준
                if(result==0){
                    result=Integer.compare(o1[1],o2[1]);//y좌표 기준
                    if(result==0){
                        result=Integer.compare(o1[2],o2[2]);//타입 기준
                    }
                }
                return result;
            }
        });

        System.out.println();
        for(int a=0;a<map.length;a++){
            for(int b=0;b<map.length;b++){
                System.out.print(map[a][b]+" ");
            }
            System.out.println();
        }
        System.out.println();


        //함수 그래프 형식으로 저장된 배열 리턴
        return answer;
    }

    public static boolean canDoBeam(int x, int y, int[][]map, int command){
        //보는 교촤점 좌표를 기준으로 오른쪽 방향으로 설치 또는 삭제

        if(command==CREATE){

            return check(x,y,map,BEAM);
        }
        else{

            map[x][y]=BLANK;//삭제한다고 가정.


            boolean canDo=true;

            //맵 전체에 대해 다 돌려본다.
            for(int i=0;i<map.length&canDo;i++) {

                for(int j=0;j<map.length&canDo;j++) {
                    if(map[i][j]!=BLANK){//빈 칸이 아니고
                        if(!check(i,j,map,map[i][j])){//삭제가 안되면
                            canDo=false;
                            break;
                        }
                    }
                }
            }

            map[x][y]=BEAM;//다시 원상복구

            return canDo;
        }


    }

    public static boolean check(int x, int y,int[][]map,int type) {
        if (type == BEAM) {//보에 해당하는 경우
            if (x + 1 < map.length && map[x + 1][y] == PILLAR) {
                return true;
            } else if (x + 1 < map.length && y + 1 < map.length && map[x + 1][y + 1] == PILLAR) {
                return true;
            }
            //양쪽 끝 부분이 다른 보와 동시에 연결되어 있다면,
            else if (y - 1 >= 0 && y + 1 < map.length && map[x][y - 1] == BEAM && map[x][y + 1] == BEAM) {
                return true;
            } else {
                return false;//그 외에는 불가
            }
        } else {//기둥에 해당하는 경우
            if (x == map.length - 1) {
                return true;
            }

            //다른 기둥 위에 있다면,
            else if (map[x + 1][y] == PILLAR) {
                return true;
            }
            //보의 한쪽 끝 부분 위에 있다면,
            else if (y - 1 >= 0 && map[x][y - 1] == BEAM) {
                return true;
            } else if (y + 1 < map.length && map[x][y + 1] == BEAM) {
                return true;
            } else {//그 외에는 할 수 없다.
                return false;
            }
        }
    }

    public static boolean canDoPillar(int x, int y, int[][]map, int command) {
        //기둥은 교차점 좌표를 기준으로 위쪽 방향으로 설치또는 삭제
        if(command==CREATE) {
        return check(x,y,map,PILLAR);
        }
        else {


            map[x][y]=BLANK;//삭제한다고 가정.


            boolean canDo=true;

            //맵 전체에 대해 다 돌려본다.
            for(int i=0;i<map.length&canDo;i++) {

                for(int j=0;j<map.length&canDo;j++) {
                    if(map[i][j]!=BLANK){//빈 칸이 아니고
                        if(!check(i,j,map,map[i][j])){//삭제가 안되면
                            canDo=false;
                            break;
                        }
                    }
                }
            }

            map[x][y]=PILLAR;//다시 원상복구

            return canDo;
        }
    }

}
