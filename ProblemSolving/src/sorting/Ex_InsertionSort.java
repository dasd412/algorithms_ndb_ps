package sorting;

public class Ex_InsertionSort {

    public static void main(String[] args) {
        int[]arr={7,5,9,0,3,1,6,2,4,8};

        for(int i=1;i<arr.length;i++){//0번쨰 원소는 정렬되어있다고 가정한다.

            for(int j=i;j>0;j--){//i번째부터 1번째 원소까지 탐색한다.

                if(arr[j]<arr[j-1]){//이전 원소를 살펴본다. 이전 원소가 더 크다면, 교환한다.
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;

                }
                else{//이전 원소가 더 작다면 더 이상 교환할 필요없다.
                    break;
                }
            }

        }

        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }

    }
}

