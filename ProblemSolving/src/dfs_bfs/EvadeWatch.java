package dfs_bfs;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class EvadeWatch {
    static BufferedWriter bw;

    static class Dimension{
        int x;
        int y;

        public Dimension(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Dimension{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static final String  student="S";
    static final String teacher="T";
    static final String none="X";
    static final String wall="W";

    static int studentCount=0;

    static boolean canEvade=false;


    static String[][]map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

       map=new String[n][n];

        List<Dimension> list=new ArrayList<>();

        for (int i = 0; i <n ; i++) {
            String[]splits=br.readLine().split(" ");
            for (int j = 0; j <n ; j++) {
               map[i][j]=splits[j];
               if(map[i][j].equals(none)){//빈 곳을 리스트에 담는다.
                   list.add(new Dimension(i,j));
               }
               else if(map[i][j].equals(student)){
                   studentCount++;
               }
            }
        }



        List<Dimension>results=new ArrayList<>();//빈 곳 3개를 뽑아 담는 리스트

       combination(results,list,0,list.size(),3);

        if(canEvade){
            bw.write("YES");
        }
        else{
            bw.write("NO");
        }


        br.close();
        bw.close();
    }

    private static void combination(List<Dimension> results, List<Dimension> list, int index, int n, int r) {


        if(canEvade){//확정된 답이 존재한다면, 다른 분기는 볼 필요가 없다.
            return;
        }

        if(r==0){//base case

            //임시 맵 생성
            String[][]mockMap=new String[map.length][map[0].length];

            for (int i = 0; i <mockMap.length ; i++) {
                for (int j = 0; j <mockMap[0].length ; j++) {
                    mockMap[i][j]=map[i][j];
                }
            }

            //뽑은 3 곳에 벽 세우기
            for (Dimension result : results) {
                mockMap[result.x][result.y] = wall;
            }


//            System.out.println();
//            for (int i = 0; i <mockMap.length ; i++) {
//                for (int j = 0; j <mockMap[0].length ; j++) {
//                    System.out.print(mockMap[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            boolean isWatched=false;//감시당하고 있는지를 나타냄.

            for (int i = 0; i <mockMap.length ; i++) {
                for (int j = 0; j <mockMap[0].length ; j++) {
                    if(mockMap[i][j].equals(teacher)){//(i,j)에 선생님이 있다면,
                      isWatched=watch(i,j,mockMap);//감시 영역을 체크한다.

                        if(isWatched){
                            return;//하나라도 감시 당하고 있으면 적절한 분기가 아님.
                        }
                    }
                }
            }


           // 모든 곳에서 감시당하고 있지 않으면 탈출 가능!
            canEvade=true;



            return;
        }
        else{

            for(int i=index;i<n;i++){
                results.add(list.get(i));
                combination(results,list,i+1,n,r-1);
                results.remove(results.size()-1);
            }
        }


    }

    private static boolean watch(int i, int j, String[][] mockMap) {


        //(i,j)를 기준으로 동서남북에 대해 학생이 존재하는 지 탐색한다. 단, 그 사이에 벽이 있으면 안된다.
        int north=i;

        while(north>=0){

            if(mockMap[north][j].equals(student)){
                return true;
            }


            if(mockMap[north][j].equals(wall)){
                break;
            }

            north--;
        }

        int south=i;
        while(south<mockMap.length){

            if(mockMap[south][j].equals(student)){
                return true;
            }

            if(mockMap[south][j].equals(wall)){
                break;
            }

            south++;
        }

        int east=j;

        while(east<mockMap.length){

            if(mockMap[i][east].equals(student)){
                return true;
            }

            if(mockMap[i][east].equals(wall)){
                break;
            }

            east++;
        }


        int west=j;

        while(west>=0){

            if(mockMap[i][west].equals(student)){
                return true;
            }

            if(mockMap[i][west].equals(wall)){
                break;
            }

            west--;
        }



        return false;//동서남북 어디서도 학생을 찾을 수 없었다는 것을 나타낸다.
    }

}
