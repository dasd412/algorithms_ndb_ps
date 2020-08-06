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

        long[]dducks=new long[n];
        splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            dducks[i]=Long.parseLong(splits[i]);
        }

        Arrays.sort(dducks);

        long maxHeight=getMaxHeight(0,n-1,dducks,m);

        bw.write(Long.toString(maxHeight)+"\n");
        
        br.close();
        bw.close();
    }

    private static long getMaxHeight(int start, int end, long[] arr, long target) {

        long height=0;


        long[]heights=new long[(int) (arr[end]+1)];//0부터 떡의 최댓값까지 가능한 길이들을 담는 배열..

        /*
        떡의 길이가 19, 15, 10, 17인 경우 최솟값은 10, 최댓값은 19다.
        따라서 길이들을 담는 배열은 10,11,12,13....18,19를 담고 있다.

         */
        for(int a=0;a<heights.length;a++){
            heights[a]=a;

        }

        int i=0;
        int j=heights.length-1;

        long sum;

        while(i<=j){

            int mid=(i+j)/2;

            sum=0;

            for(int a=0;a<arr.length;a++){
                if(arr[a]>heights[mid]){//mid 인덱스의 길이 값보다 큰 떡 길이에 대해
                    sum=sum+arr[a]-heights[mid];//자를 길이의 합을 구한다.
                }

            }

            if(sum==target){//더한 값이 m과 같다면, 찾은 것이다.
                return heights[mid];
            }
            else if(sum>target){//더한 값이 m보다 크다면,

                height=heights[mid];//일단 적어도 m만큼의 떡은 얻으므로 길이를 저장한다.
                i=mid+1;//더한 값이 원하는 값보다 크므로 자르는 기준을 높인다.
            }
            else{//더한 값이 m보다 작다면,

                j=mid-1;//더한 값이 원하는 값보다 작으므로 자르는 기준을 낮춘다.
            }




        }







        return height;

    }
}
