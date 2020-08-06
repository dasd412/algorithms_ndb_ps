package binarySearch;

import java.io.*;
import java.util.Arrays;

public class LookingForParts {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int[]parts=new int[n];

        String[]splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            parts[i]=Integer.parseInt(splits[i]);
        }

        Arrays.sort(parts);//<-이진 탐색은 정렬될 경우에만 사용가능.

        int m=Integer.parseInt(br.readLine());

        int[]lookingFor=new int[m];
        splits=br.readLine().split(" ");
        for(int i=0;i<m;i++){
            lookingFor[i]=Integer.parseInt(splits[i]);
        }

        int targetIndex;
        for(int i=0;i<m;i++){

            targetIndex=binarySearch(lookingFor[i],0,n-1,parts);
            if(targetIndex==-1){
                bw.write("no ");
            }
            else{
                bw.write("yes ");
            }
        }


        br.close();
        bw.close();
    }

    private static int binarySearch(int target, int start, int end, int[] parts) {

        int mid;
        while(start<=end){
            mid=(start+end)/2;
            if(parts[mid]==target){
                return mid;
            }
            else if(parts[mid]>target){
                end=mid-1;

            }
            else{

                start=mid+1;
            }

        }


        return -1;
    }

}
