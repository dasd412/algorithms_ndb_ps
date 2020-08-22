package implementations;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class ChickenDelivery {
    static BufferedWriter bw;

    static class Dir {
        private final int x;
        private final int y;

        public Dir(int x, int y){
            this.x=x;
            this.y=y;
        }

    }

    static class House{
        private final int x;
        private final int y;
        private int distance=Integer.MAX_VALUE;

        public House(int x, int y){
            this.x=x;
            this.y=y;

        }

        public void updateDistance(Dir store){
            //한 가게에 대해, 그 가게와의 거리를 계산한다.
            int calculated_distance=Math.abs(store.x-this.x)+Math.abs(store.y-this.y);

            if(this.distance>calculated_distance){
                this.distance=calculated_distance;
            }



        }



    }

    static int minDistance=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        List<Dir> directions=new ArrayList<>();
        List<House>houses=new ArrayList<>();

        int[][]map=new int[n][n];
        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(splits[j]);
                if(map[i][j]==2){//치킨집 위치 담기
                    directions.add(new Dir(i,j));
                }
                else if(map[i][j]==1){//가정집 위치 담기
                    houses.add(new House(i,j));
                }
            }
        }

       calculateAllDistances(directions,houses,m);

        bw.write(minDistance+"\n");


        br.close();
        bw.close();
    }

    private static void calculateAllDistances( List<Dir> directions,List<House>houses,int m) {


        //가게 거리 리스트에서 m개를 뽑는다.

        List<Dir>combination=new ArrayList<>();
        getCombination(directions,combination,0,directions.size(),m,houses);//가게 개수에서 m개를 뽑자. 뽑은 것은 combination에 담긴다.

    }

    private static void getCombination(List<Dir> directions, List<Dir> combination,int index, int n, int r,List<House>houses) {
        if(r==0){


            int sum=0;

            //뽑은 조합에 대해
            for (Dir store : combination) {
                for (House house : houses) {//가정집들에 대해 반복한다.
                    house.updateDistance(store);//가게와의 거리를 갱신한다.
                }
            }


            for (House house : houses) {
                sum += house.distance;
                house.distance = Integer.MAX_VALUE;//다시 초기화
            }

            minDistance=Math.min(minDistance,sum);


            return ;
        }
        for(int i=index;i<n;i++){
            combination.add(directions.get(i));
            getCombination(directions,combination,i+1,n,r-1,houses);
            combination.remove(combination.size()-1);
        }




    }

}
