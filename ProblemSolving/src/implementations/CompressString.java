package implementations;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class CompressString {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str=br.readLine();


        int minSize=Integer.MAX_VALUE;

        for(int unit=1;unit<=str.length()/2;unit++){//1개 단위 부터 문자열의 길이 단위까지 반복한다.

            int count=compress(str,unit);//문자열을 unit 단위로 압축한다.

           minSize=Math.min(minSize,count);//문자열 길이를 최소인 것으로 갱신한다.
            
        }


        bw.write(Integer.toString(minSize)+"\n");


        br.close();
        bw.close();
    }

    private static int compress(String str, int unit) {


        List<String>compressed=new ArrayList<>();

       int i=0;


       while(i+unit<=str.length()){




           String sub=str.substring(i,i+unit);

           compressed.add(sub);



           i=i+unit;

       }

       if(i<str.length()) {//단위로 자른 후 아직 남은 문자열이 있다면,
           compressed.add(str.substring(i, str.length()));//나머지 채우기
       }

       int count=str.length();//문자열의 길이
       int equal=0;//같은 거 세는 변수
       for(i=1;i<compressed.size();i++){


           if(compressed.get(i-1).equals(compressed.get(i))){//이전 거랑 같다면,

               equal++;
           }
           else{//같지 않다면,

               if(equal!=0){//같은 게 존재한다면,
                   count-=(equal*unit);//문자열의 길이는 자르는 단위*같은 거 개수만큼 줄어듭니다.
                   count++;//대신 숫자로 하나 표현되므로, 숫자 하나 개수만큼 문자열의 길이를 증가시켜줍니다.
               }

               equal=0;
           }

       }

       if(equal!=0){//남는 거 처리..
           count-=(equal*unit);
           count++;
       }

        System.out.println("count: "+count+" unit"+unit);

       for(i=0;i<compressed.size();i++){
           System.out.print(compressed.get(i)+" ");
       }
        System.out.println();

        return count;//문자열의 길이를 리턴합니다.
    }
}
