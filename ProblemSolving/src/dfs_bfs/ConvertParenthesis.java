package dfs_bfs;

import java.io.*;

public class ConvertParenthesis {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.close();
        bw.close();
    }

    public String solution(String p) {
        String answer = "";

        //w를 u,v로 분리
        String[]w=split(p);

        String u=w[0];
        String v=w[1];

        if(correct(u)){//u가 올바른 괄호 문자열인 경우
            while(correct(u)){//u가 올바른 괄호 문자열이라면, 문자열 v에 대해 반복한다.


                u=append(u,v);//u에 v를 붙인다.

                if(v==""){//v가 빈 문자열이면 끝낸다.
                    break;
                }
                w=split(v);//v에 대해 다시 수행
                u=w[0];
                v=w[1];
            }
        }
        else{//u가 올바르지 않은 괄호 문자열인 경우
            recursion(u,v);
        }




        return answer;
    }

    private void recursion(String u, String v) {
    }

    private String append(String u, String v) {
        return "";
    }

    private boolean correct(String u) {
        return false;
    }

    private String[] split(String p) {

        return new String[p.length()];
    }
}
