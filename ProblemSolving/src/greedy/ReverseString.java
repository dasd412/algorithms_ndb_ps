package greedy;

import java.io.*;


public class ReverseString {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[]splits=br.readLine().split("");


        int reverseCount;//총 뒤집기 개수

        int oneCount=0;//1의 개수, 연속된 1은 하나의 1로 취급
        int zeroCount=0;//0의 개수, 연속된 0은 하나의 0으로 취급

        int tempZeroCount=0;//0이 연속되는 지 나타내는 플래그
        int tempOneCount=0;//1이 연속되는 지 나타내는 플래그

        /*

        예를 들어 0001100은
        010과 마찬가지다.
        왜냐하면 연속된 하나 이상의 숫자를 잡을 수 있다는 조건 때문이다.
        따라서 0001100은
        010으로 치환할 수 있으므로 실질적인 0의 개수는 2개, 1의 개수는 1개다.

        다른 예로는 111000000011을 들어보면, 역시 101로 치환 할 수 있다.

         */

        for(int i=0;i<splits.length;i++){

            if(Integer.parseInt(splits[i])==0){//0이라면,


                if(tempZeroCount==0){//0이 연속된 게 아니라면,
                    zeroCount++;//0의 개수를 증가시킨다.
                }

                tempOneCount=0;//1이 연속되지 않았다고 표시한다.

               tempZeroCount++;//0이 연속 된다.
            }
            else{//1이라면,

                if(tempOneCount==0){//1이 연속된 게 아니라면,
                    oneCount++;//1의 개수를 증가시킨다.
                }



                tempZeroCount=0;//0이 연속된 게 아니라고 표시한다.

               tempOneCount++;//1이 연속 된다고 표시한다.
            }
        }

        reverseCount=Math.min(zeroCount,oneCount);//실질적인 0과 1의 개수 중에 더 작은 것을 취한다.

        bw.write(Integer.toString(reverseCount)+"\n");

        br.close();
        bw.close();
    }

}
