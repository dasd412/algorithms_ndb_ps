package binarySearch;

import java.io.*;
import java.util.Arrays;

public class InstallWIFI {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n,c;

        String[]strings=br.readLine().split(" ");
        n=Integer.parseInt(strings[0]);
        c=Integer.parseInt(strings[1]);

        long[]homes=new long[n];

        for (int i = 0; i <n ; i++) {
            homes[i]=Long.parseLong(br.readLine());
        }

        Arrays.sort(homes);

        long maxDistance=getMaxDistance(homes,n,c);
        bw.write(maxDistance+"\n");

        br.close();
        bw.close();
    }

    private static long getMaxDistance(long[] homes, int n, int c) {

        long minLength=1;//최소 거리는 1이다.
        long maxLength=homes[homes.length-1]-homes[0];//최대 거리는 가장 오른쪽 값- 가장 왼쪽 값이다. homes 배열은 오름차순 정렬되어 있다.

        long maxDistance=1;

        while(minLength<=maxLength){

            long gap=(minLength+maxLength)/2;//거리 차이를 구한다.

            int count=1;//현재 gap으로 거리를 측정하였을 때, 몇개의 공유기를 설치할 수 있는지를 나타내는 변수. 첫 번째 요소에는 무조건 설치하므로 초기값은 1이다.

            long standard=homes[0]+gap;//기준은 첫 번째 요소+거리 차이
            for (int i = 1; i <homes.length ; i++) {//두 번쨰 요소부터 시작하여 반복한다.

                if(homes[i]>=standard){//만약 i번째 요소가 기준 값 보다 크다면,
                    count++;//공유기 설치 개수를 증가하고
                    standard=homes[i]+gap;//새로운 기준으로 변경한다.
                }
            }

            if(count<c){//세본 것이 원하는 공유기 설치 개수보다 작다면, gap을 너무 크게 잡은 것이다.
                maxLength=gap-1;

            }else{
                /*
                세본 것이 원하는 개수보다 크다면, gap을 너무 작게 잡은 것이다.

                그리고 원하는 개수랑 같다면, 혹시나 더 큰 값이 있을 수 있으므로 gap을 더 키워본다.

                 */

                maxDistance=gap;

                minLength=gap+1;

            }

            



        }

        return maxDistance;
    }

}
