package greedy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class NumberCardGame {
    static BufferedWriter bw;

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n,m;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int[][]cards=new int[n][m];

        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<m;j++){
                cards[i][j]=Integer.parseInt(splits[j]);
            }
        }

        int max=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            int min=cards[i][0];//i번째 행에서 0번째 값을 기준으로 한다.
            for(int j=1;j<m;j++){//1~m-1열까지 순회한다.

                if(min>cards[i][j]){//더 작은 값이 있으면 갱신한다.
                    min=cards[i][j];
                }
            }
            max=Math.max(min,max);//한 행의 순회가 끝날 때마다 최댓값을 갱신한다.
        }


        bw.write(Integer.toString(max)+"\n");

        br.close();
        bw.close();
    }
}
