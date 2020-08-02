package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class BiggesNumberPrinciple {


    static BufferedWriter bw;

    static int[]temp;//병합용 임시 배열. static으로 해야 병합 메소드 호출마다 생성해야 하는 시간 낭비를 줄일 수 있음.


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n,m,k;

        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);
        k=Integer.parseInt(splits[2]);

        //입력되는 숫자 배열
        int[]numbers=new int[n];
        temp=new int[n];

        splits=br.readLine().split(" ");

        for(int i=0;i<n;i++){
            numbers[i]=Integer.parseInt(splits[i]);
        }

        //각 인덱스당 쓸 수 있는 횟수 배열
        int[]counts=new int[n];

        for(int i=0;i<n;i++){//모든 인덱스에 대해 k로 초기화 한다.
            counts[i]=k;
        }

        //가장 큰 수부터 택해야 가장 큰 합을 만들 수 있다. 따라서 내림차순 정렬한다.

        mergeSort(0,n-1,numbers);



        int max=0;



        int i=0;
        while(i<n&&m>0){//i<n은 사실 도달할 수 없다. 실질적으로 m>0인지가 종료 조건이다.


            if(counts[i]!=0){//만약 , 현재 인덱스의 사용횟수가 0이 아니라면,
                max+=numbers[i];//더한다.
                counts[i]--;//사용 횟수를 뺴고
                m--;//덧셈 횟수를 뺸다.


                if(i-1>=0){//i가 1번째 인덱스라면 (사실 이 문제에서 1 이상은 볼 필요가 없다.)
                    if(counts[i]==0){//혹시나 1번째 인덱스의 사용횟수가 0인 경우에는 초기화해준다.
                        counts[i]=k;
                    }
                    i--;//0번쨰 인덱스로 되돌려준다.
                    counts[i]=k;//0번째 인덱스의 사용횟수를 초기화해준다.

                }
            }

            else{//0번째 인덱스인 경우에는 i++로 1번쨰 인덱스로 해준다.
                i++;
            }




        }


        bw.write(Integer.toString(max)+"\n");





        br.close();
        bw.close();

    }

    private static void mergeSort(int start, int end, int[] numbers) {//병합정렬
        if(start>=end){
            return;
        }
        else{
            int mid=(start+end)/2;
            //분할
            mergeSort(start,mid,numbers);
            mergeSort(mid+1,end,numbers);
            //병합

            merge(start,mid,end,numbers);

        }
    }

    private static void merge(int start, int mid, int end, int[] numbers) {

        int i=start;
        int j=mid+1;
        int k=start;

        //내림차순 정렬
        while(i<=mid&&j<=end){
            if(numbers[i]<numbers[j]){

                temp[k++]=numbers[j++];
            }
            else{

                temp[k++]=numbers[i++];
            }
        }

        //남은 것들 넣기
        while(i<=mid){
            temp[k++]=numbers[i++];
        }

        while(j<=end){
            temp[k++]=numbers[j++];
        }

        //임시 배열에 정렬된 값 원래 배열에 옮기기
        for(int a=start;a<=end;a++){
            numbers[a]=temp[a];
        }

    }


}

