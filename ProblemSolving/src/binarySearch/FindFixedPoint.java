package binarySearch;

import java.io.*;

public class FindFixedPoint {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]arr=new int[n];

        String[]strings=br.readLine().split(" ");

        for (int i = 0; i <n ; i++) {
            arr[i]=Integer.parseInt(strings[i]);//오름차순 정렬된 상태로 입력이 들어온다.
        }

        int fixedPoint=getFixedPoint(0,arr.length-1,arr);

        bw.write(fixedPoint+"\n");

        br.close();
        bw.close();
    }

    private static int getFixedPoint(int start, int end, int[] arr) {
        /*
        arr 배열이 오름차순 정렬되어 있기 때문에 이분 탐색을 활용할 수 있다.
         */


        while(start<=end){

            int mid=(start+end)/2;

            if(arr[mid]==mid){//원소 값과 인덱스가 같으면 고정점을 찾은 것이다.
                return mid;
            }
            else if(arr[mid]<mid){//원소 값이 인덱스보다 작다면,


                start=mid+1;
            }
            else{//원소 값이 인덱스보다 크다면,

               end=mid-1;
            }

        }


        return -1;//없다면 -1을 리턴한다.
    }
}
