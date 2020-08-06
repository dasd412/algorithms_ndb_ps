package binarySearch;

public class BinarySearch_Iteration {

    public static void main(String[] args) {

        int[]arr={1,3,5,7,9,11,13,15,17,19};//<-이진 탐색은 반드시 정렬되어 있어야만 할 수 있다.

        int targetIndex=binarySearch(11,0,arr.length-1,arr);

        if(targetIndex==-1){
            System.out.println("there is no target ..");
        }
        else{


            System.out.println(targetIndex);

        }
    }

    private static int binarySearch(int target, int start, int end, int[] arr) {

        int targetIndex=-1;

        int mid;

        while(start<=end){

            mid=(start+end)/2;

            if(arr[mid]==target){
                return mid;//찾았으면, 바로 리턴한다.
            }
            else if(arr[mid]>target){//중간 인덱스의 값이 타겟보다 크다면

                end=mid-1;//왼쪽 부분을 확인한다.
            }
            else{//중간 인덱스의 값이 타겟보다 작으면,
                start=mid+1;//오른쪽 부분을 확인한다.

            }


        }

        return targetIndex;
    }
}
