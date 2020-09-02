package sorting;

import java.io.*;


public class Scores {

    static BufferedWriter bw;

    static Student[]temp;

    static class Student implements Comparable<Student>{

        String name;
        int korean,english,math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public String toString() {
            return name+"\n";
        }

        @Override
        public int compareTo(Student other) {
           int result=this.korean-other.korean;
           if(result==0){

               result=this.english-other.english;

               if(result==0){

                   result=this.math-other.math;

                   if(result==0){//이름은 사전순으로

                       return this.name.compareTo(other.name);
                   }
                   else{//수학점수는 감소하는 순서로

                       if(result>0){
                           return -1;
                       }
                       else{
                           return 1;
                       }
                   }

               }
               else{//영어점수는 증가하는 순서로
                   if(result>0){
                       return 1;
                   }
                   else{
                       return -1;
                   }

               }

           }
           else{//국어 점수는 감소하는 순서대로
               if(result>0){
                   return -1;
               }
               else{
                   return 1;
               }
           }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        Student[]students=new Student[n];
        temp=new Student[n];

        String[]splits;

        for (int i = 0; i <n ; i++) {
        splits=br.readLine().split(" ");
        String name=splits[0];
        int korean=Integer.parseInt(splits[1]);
        int english=Integer.parseInt(splits[2]);
        int math=Integer.parseInt(splits[3]);

        students[i]=new Student(name,korean,english,math);

        }

        mergeSort(0,students.length-1,students);

        for (Student student:students) {
            bw.write(student.toString());
        }



        br.close();
        bw.close();
    }

    private static void mergeSort(int start, int end, Student[] students) {
        if(start>=end){
            return;
        }
        else{
            int mid=(start+end)/2;
            mergeSort(start,mid,students);
            mergeSort(mid+1,end,students);
            merge(start,mid,end,students);
        }
    }

    private static void merge(int start, int mid, int end, Student[] students) {

        int i=start;
        int j=mid+1;
        int k=start;

        while(i<=mid&&j<=end){

            if(students[i].compareTo(students[j])<0){
                temp[k++]=students[i++];
            }
            else{
                temp[k++]=students[j++];
            }

        }

        while(i<=mid){
            temp[k++]=students[i++];
        }

        while(j<=end){
            temp[k++]=students[j++];
        }

        for (int l = start; l <=end ; l++) {
            students[l]=temp[l];

        }

    }
}
