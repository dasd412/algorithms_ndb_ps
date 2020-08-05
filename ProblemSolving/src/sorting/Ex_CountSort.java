package sorting;

public class Ex_CountSort {

    /*

    계수 정렬(count sort)은 데이터의 크기가 int를 넘지 않고, 범위가 제한되어 있으며
    데이터의 자료형이 정수인 경우에만 사용할 수 있는 정렬 방법이다.
    대신, 시간 복잡도는 O(n)정도밖에 안되어 매우 효율적이다.
     */

    public static void main(String[] args) {
        int[]arr={7,5,9,0,3,1,6,2,9,1,4,8,0,5,2};//0에서부터 9까지의 범위를 갖고 있다.

        int[]counts=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            counts[arr[i]]++;
        }

        for(int i=0;i<counts.length;i++){

            while(counts[i]>0){
                System.out.println(i);
                counts[i]--;
            }

        }



    }
}
