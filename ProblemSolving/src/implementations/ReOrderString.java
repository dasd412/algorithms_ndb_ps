package implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReOrderString {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str=br.readLine();

        char[]arr=str.toCharArray();

        List<Character> chs=new ArrayList<>();


        int sum=-1;//-1이면 숫자가 존재하지 않았다는 것을 뜻한다.

        for(int i=0;i<arr.length;i++){

            if(Character.getNumericValue(arr[i])>=0&&Character.getNumericValue(arr[i])<=9){//숫자라면
                if(sum==-1){//처음으로 숫자가 나온거라면,
                    sum=0;
                }

                sum+=Character.getNumericValue(arr[i]);
            }
            else{
                chs.add(arr[i]);
            }

        }

        Collections.sort(chs);//오름차순 정렬한다.


        StringBuilder sb=new StringBuilder();

        for(int i=0;i<chs.size();i++){
            sb.append(chs.get(i));
        }

        if(sum!=-1) {//숫자가 존재하는 경우에만 수를 이어서 붙인다.
            sb.append(sum);

        }


        bw.write(sb+"\n");

        br.close();
        bw.close();
    }
}
