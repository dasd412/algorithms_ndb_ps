package sorting;

public class Ex_QuickSort {
    public static void main(String[] args) {
        int[]arr={7,5,9,0,3,1,6,2,4,8};

        quickSort(0,arr.length-1,arr);

        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }


    }

    private static void quickSort(int start, int end, int[] arr) {

        if(start>=end){
            return;
        }
        else{
            //피봇 인덱스 왼쪽에는 피봇보다 작은 원소들이, 오른쪽에는 피봇보다 큰 원소들이 위치하도록 정렬한다.
            int pivotIndex=partition(start,end,arr);


            quickSort(start,pivotIndex-1,arr);//피봇이 위치한 인덱스보다 작은 쪽은 피봇 보다 작은 원소들에 대해 정렬하는 것이다.
            quickSort(pivotIndex+1,end,arr);//피봇이 위피한 인덱스보다 큰 쪽은 피봇보다 큰 원소들에 대해 정렬하는 것이다.

        }

    }

    private static int partition(int start, int end, int[] arr) {//피봇 인덱스는 start로 지정한다.

        int pivotIndx=start;

        int left=start+1;//왼쪽에서부터 탐색해서 pivot보다 큰 값을 찾는다.
        int right=end;//오른쪽에서부터 탐색해서 pivot보다 작은 값을 찾는다.

        while(left<=right){//교차하기 전까지

            while(left<=end&&arr[left]<=arr[pivotIndx]){//왼쪽이 끝에 도달하거나, 피봇 값보다 큰 요소를 찾을 때 까지 반복한다.
                left++;
            }

            while(right>start&&arr[right]>=arr[pivotIndx]){//오른쪽이 시작에 도달하거나, 피봇 값보다 작은 요소를 찾을 때까지 반복한다.
                right--;
            }

            if(left>right){//엇갈렸다면, 작은 데이터와 피봇을 교체한다.

                int temp=arr[pivotIndx];
                arr[pivotIndx]=arr[right];
                arr[right]=temp;

            }else{//엇갈리지 않았다면, 작은 데이터와 큰데이터를 교체한다.
                int temp=arr[left];//큰 데이터
                arr[left]=arr[right];
                arr[right]=temp;

            }

        }

        return right;//피봇이 위치한 인덱스를 리턴한다.

    }
}
