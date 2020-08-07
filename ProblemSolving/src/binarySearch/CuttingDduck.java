package binarySearch;

import java.io.*;
import java.util.Arrays;

public class CuttingDduck {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split(" ");
        int n=Integer.parseInt(splits[0]);
        long m=Long.parseLong(splits[1]);

        long[]trees=new long[n];
        splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            trees[i]=Long.parseLong(splits[i]);
        }

        Arrays.sort(trees);

        long maxHeight=getMaxHeight(0,n-1,trees,m);

        bw.write(Long.toString(maxHeight)+"\n");

        br.close();
        bw.close();
    }

    private static long getMaxHeight(int start, int end, long[] arr, long target) {

        long height=0;


        /*
        떡의 길이가 19, 15, 10, 17인 경우 최솟값은 10, 최댓값은 19다.
        따라서 길이들의 범위는  0,1,2,3,4....18,19이다.

         */


        long i=0;
        long j=arr[end];

        long sum;

        while(i<=j){

            long mid=(i+j)/2;

            sum=0;

            for(int a=0;a<arr.length;a++){
                if(arr[a]> mid){//mid 인덱스의 길이 값보다 큰 떡 길이에 대해
                    sum=sum+arr[a]- mid;//자를 길이의 합을 구한다.
                }

            }

            if(sum==target){//더한 값이 m과 같다면, 찾은 것이다.
                return  mid;
            }
            else if(sum>target){//더한 값이 m보다 크다면,

                height= mid;//일단 적어도 m만큼의 떡은 얻으므로 길이를 저장한다.
                i=mid+1;//더한 값이 원하는 값보다 크므로 자르는 기준을 높인다.
            }
            else{//더한 값이 m보다 작다면,

                j=mid-1;//더한 값이 원하는 값보다 작으므로 자르는 기준을 낮춘다.
            }




        }







        return height;

    }
}
