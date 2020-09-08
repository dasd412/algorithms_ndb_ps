package dynamicProgramming;

import java.io.*;

public class Retire {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        int[]times=new int[n];
        int[]pays=new int[n];

        String[]strings;
        for (int i = 0; i <n ; i++) {
            strings=br.readLine().split(" ");
            times[i]=Integer.parseInt(strings[0]);
            pays[i]=Integer.parseInt(strings[1]);
        }

        int maxProfit=getMaxProfit(times,pays,n);

        bw.write(maxProfit+"\n");



        br.close();
        bw.close();
    }

    private static int getMaxProfit(int[] times, int[] pays, int n) {

        //dp 테이블 초기화
        int[]dp=new int[n];
        for (int i = 0; i <n ; i++) {
            dp[i]=pays[i];
        }

        for (int i = 0; i <n ; i++) {

            int time=i+times[i];

            while(time<n){//i+times[i] 부터 n-1까지 반복한다.
                
                /*
                if가 아니라 while을 써야하는 이유는 예제 입력 4를 확인하면 알 수 있다.
                출력값이 90이 되어야 하는 이유는 다음과 같다.
                6일째 기준에서 최대 값은 자명하게 60이다. 왜냐하면 1일째에 50, 6일 째에 +10을 하는게 최대이기 때문이다.
                그런데, 6일쨰 상담은 걸리는 시간이 단 '하루'이다.
                따라서 6일을 고르고 나서 7,8,9,10일 모두 택할 수 있다. (물론, 퇴사 기간에 맞는 등 조건을 만족해야 한다.)

                조건을 만족하는 것을 고르면 7일과 8일은 고를 수 있다. 왜냐하면 각각 상담기간이 2일, 3일이기 때문에
                퇴사일을 맞출 수 있기 때문이다.
                즉, 계산해보면 7일의 경우 금액 80이지만, 8일의 경우 금액 90을 얻을 수 있다.
                그러므로 if가 아닌, while을 써서 시간이 가능한 것들을 모두 탐색해야 한다.

                
                 */


                dp[time]=Math.max(dp[time],dp[i]+pays[time]);//현재 기록된 값과 비교하여 더 큰 값으로 갱신한다.
                time++;
            }

        }

        int max=0;

        for (int i = 0; i <n ; i++) {
           if(i+times[i]>n){//기간을 넘는 것은 무효로 처리한다.
               dp[i]=0;
           }
           max=Math.max(max,dp[i]);
        }


        return max;
    }

}
