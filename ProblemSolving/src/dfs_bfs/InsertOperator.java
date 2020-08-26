package dfs_bfs;

import java.io.*;

public class InsertOperator {

    static BufferedWriter bw;

    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());

        String[]splits=br.readLine().split(" ");
        int[]arr=new int[n];

        for (int i = 0; i <n ; i++) {
            arr[i]=Integer.parseInt(splits[i]);
        }
        int[]op=new int[4];

        splits=br.readLine().split(" ");
        for (int i = 0; i <op.length ; i++) {
            op[i]=Integer.parseInt(splits[i]);
        }

        backTracking(arr[0],1,n,arr,op);


        bw.write(max+"\n"+min+"\n");




        br.close();
        bw.close();
    }

    private static void backTracking(int result,int index, int n, int[] arr, int[] op) {//arr을 index:1부터 n-1까지 반복하여 result에 그 결과를 담는 재귀 메서드
        if(index>=n){//기저 조건

           max=Math.max(max,result);
           min=Math.min(min,result);

            return;
        }
        else{

            for(int i=0;i<op.length;i++){//연산자에 대하여 반복한다.

                if(op[i]!=0){//해당 연산자를 아직 사용할 수 있다면,

                    op[i]--;//해당 연산자 사용 가능 횟수를 감소시키고
                    
                    if(i==0){//+

                        backTracking(result+arr[index],index+1,n,arr,op);
                    }
                    else if(i==1){//-

                        backTracking(result-arr[index],index+1,n,arr,op);
                    }else if(i==2){//*

                        backTracking(result*arr[index],index+1,n,arr,op);
                    }
                    else{// /

                        backTracking(result/arr[index],index+1,n,arr,op);
                    }

                    op[i]++;//분기를 탐색했으면 다시 복구한다.

                }

            }
            
        }

    }
}
