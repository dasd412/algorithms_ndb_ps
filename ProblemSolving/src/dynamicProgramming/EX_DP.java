package dynamicProgramming;

public class EX_DP {
    public static void main(String[] args) {
        System.out.println(fibonacci(50));
    }

    private static long fibonacci(int i) {

        long[]dp=new long[i];

        dp[0]=1;
        dp[1]=1;

        for(int a=2;a<i;a++){
            dp[a]=dp[a-1]+dp[a-2];
        }

        return dp[i-1];
    }
}
