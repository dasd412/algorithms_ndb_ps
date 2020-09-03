package sorting;

import java.io.*;
import java.util.*;

public class FailRatio {

    static class Stage implements Comparable<Stage> {

        double ratio;
        int stageNumber;

        public Stage(double ratio, int stageNumber) {
            this.ratio = ratio;
            this.stageNumber = stageNumber;
        }

        public String toString() {
            return this.ratio + " " + this.stageNumber;
        }

        @Override
        public int compareTo(Stage other) {

            if (this.ratio < other.ratio) {
                return 1;
            } else if (this.ratio > other.ratio) {
                return -1;
            } else {
                return this.stageNumber - other.stageNumber;
            }
        }
    }


        static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int length=Integer.parseInt(br.readLine());

        int[]stages=new int[length];
        String[]splits=br.readLine().split(" ");
        for (int i = 0; i <length ; i++) {
            stages[i]=Integer.parseInt(splits[i]);
        }

        int[]answer=solution(n,stages);

        for (int e:answer
             ) {
            bw.write(e+" ");
        }

        br.close();
        bw.close();
    }

        public static  int[] solution(int N, int[] stages) {
            int[] answer = new int[N];

            List<Stage>list=new ArrayList<>();


            int[]counts=new int[N+2];//계수 정렬용 배열

            for(int i=0;i<stages.length;i++){
                counts[stages[i]]++;
            }

            int challenge=stages.length;

            for(int i=1;i<counts.length-1;i++){
                double ratio=counts[i]/(challenge*1.0);
                int stageNumber=i;

                list.add(new Stage(ratio,stageNumber));

                challenge-=counts[i];

            }



            Collections.sort(list);


            for(int i=0;i<list.size();i++){
                answer[i]=list.get(i).stageNumber;
            }


            return answer;
        }
}
