package sorting;


public class Ex_SelectionSort {


    public static void main(String[] args) {


        int[]arr={7,5,9,0,3,1,6,2,4,8};

        for(int i=0;i<arr.length;i++){//i는 최솟값과 바꿀 기준 인덱스이다.

            int minIndex=i;//최소 값을 갖는 인덱스
            for(int j=i+1;j<arr.length;j++){//i+1번쨰부터 순회한다.

                if(arr[minIndex]>arr[j]){//더 작은 값의 인덱스가 있다면 갱신한다.
                    minIndex=j;
                }


            }

            //찾은 인덱스에 대해 스와핑한다.
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;

        }
        for(int i=0;i<arr.length;i++){

            System.out.println(arr[i]+" ");
        }

    }


}
