package dynamicProgramming;

public class Memoization {

static long[]arr=new long[51];

    public static void main(String[] args) {
        System.out.println(   fibonacci(50));
    }

    private static long fibonacci(int i) {

        if(i==1||i==2){//1,2번쨰 항은 1로 초기화되어있다.
            return 1;
        }

        if(arr[i]!=0){//기록한 적이 있으면, 바로 반환하여 불필요한 중복 계산을 없앤다.
            return arr[i];
        }

            arr[i]=fibonacci(i-1)+fibonacci(i-2);
            return arr[i];


    }
}
