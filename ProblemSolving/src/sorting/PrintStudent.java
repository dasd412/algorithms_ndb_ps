package sorting;

import java.io.*;

public class PrintStudent {
    static BufferedWriter bw;

    static class Student{
        String name;
        int score;

        public Student(String name, int score){
            this.name=name;
            this.score=score;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        Student[]infos=new Student[n];

        String[]splits;

        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            infos[i]=new Student(splits[0],Integer.parseInt(splits[1]));
        }

        quickSort(0,infos.length-1,infos);

        for(int i=0;i<infos.length;i++){
            bw.write(infos[i].name+" ");
        }


        br.close();
        bw.close();
    }

    private static void quickSort(int start, int end, Student[] infos) {

        if(start>=end){
            return;
        }
        else{
            int pivotIndex=partition(start,end,infos);

            quickSort(start,pivotIndex-1,infos);
            quickSort(pivotIndex+1,end,infos);
        }

    }

    private static int partition(int start, int end, Student[] infos) {

        int pivotIndex=start;//<-피봇을 해당 구간의 맨 왼쪽 꺼로 잡는다.

        int left=start+1;
        int right=end;

        while(left<=right){

            while(left<=end&&infos[left].score<=infos[pivotIndex].score){//피봇보다 큰 놈 찾으면 종료
                left++;
            }
            while(right>start&&infos[right].score>=infos[pivotIndex].score){//피봇보다 작은 놈 찾으면 종료.
                right--;
            }

            if(left>right){//교차했으면,피봇을 옮겨준다.

                Student temp=infos[right];
                infos[right]=infos[pivotIndex];
                infos[pivotIndex]=temp;

            }
            else{//아직 교차 전이라면,

                Student temp=infos[right];
                infos[right]=infos[left];
                infos[left]=temp;
            }


        }

        return right;

    }


}
