package binarySearch;

public class BinarySearch_Recursion {

    public static void main(String[] args) {

        int[]arr={1,3,5,7,9,11,13,15,17,19};//<-이진 탐색은 반드시 정렬되어 있어야만 할 수 있다.

        int targetIndex=binarySearch(7,0,arr.length-1,arr);

        if(targetIndex==-1){
            System.out.println("there is no target ..");
        }
        else{


            System.out.println(targetIndex);

        }
    }

    private static int binarySearch(int target,int start, int end, int[] arr) {

        if(start>end){
            return -1;//타겟이 없다.
        }
        else{
            int mid=(start+end)/2;

            if(arr[mid]==target){//중간 인덱스의 값과 타겟이 같으면 찾은 것이다.
                return mid;
            }
            else if(arr[mid]>target){//중간 인덱스 값이 타겟보다 크면, 중간 인덱스 이전 꺼만 보면 된다.

               return binarySearch(target,start,mid-1,arr);
            }
            else{//중간 인덱스 값이 타겟보다 작으면 ,중간 인덱스 이후 꺼만 보면 된다.

                return binarySearch(target,mid+1,end,arr);
            }


        }

    }
}
