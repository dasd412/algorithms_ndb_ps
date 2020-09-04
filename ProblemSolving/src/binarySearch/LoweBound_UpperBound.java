package binarySearch;

import java.io.*;

public class LoweBound_UpperBound {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,x;

        String[]str=br.readLine().split(" ");
        n=Integer.parseInt(str[0]);
        x=Integer.parseInt(str[1]);


        int[]arr=new int[n];

        str=br.readLine().split(" ");
        for (int i = 0; i <n ; i++) {
            arr[i]=Integer.parseInt(str[i]);
        }

        int lowerIndex=lowerBound(0,arr.length-1,arr,x);

        if(lowerIndex==-1){//하한값에서 해당 요소가 없음이 확인되면, -1을 출력하고 끝낸다.
            bw.write(-1+"\n");

        }
        else{

            int upperIndex=upperBound(0,arr.length-1,arr,x);

            bw.write(upperIndex-lowerIndex+"\n");
        }



        br.close();
        bw.close();
    }

    private static int upperBound(int start, int end, int[] arr, int key) {

        while(start<end){

            int mid=(start+end)/2;

            if(arr[mid]<=key){
                start=mid+1;
            }
            else{
                end=mid;
            }

        }
        return end;
    }

    private static int lowerBound(int start, int end, int[] arr, int key) {

        while(start<end){

            int mid=(start+end)/2;
            if(arr[mid]<key){

                start=mid+1;
            }
            else{
                end=mid;
            }

        }

        if(arr[end]!=key){//하한값이 key가 아니라면 해당 원소가 배열에 존재하지 않는다는 뜻이다.
            return -1;
        }
        else{
            return  end;
        }


    }
}
