package dfs_bfs;

import java.io.*;

public class MovingBlocks {
    static BufferedWriter bw;

    static class Robot{

        int a,b;

        public Robot(int a, int b){
            this.a=a;
            this.b=b;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        int[][]board=new int[n][n];

        for (int i = 0; i <n ; i++) {
            String[]splits=br.readLine().split(" ");
            for (int j = 0; j <n ; j++) {
                board[i][j]=Integer.parseInt(splits[j]);
            }
        }

        Robot robot=new Robot(0,0);

        int[][]test={{0,0},{0,0}};

        rotateLeft(test,robot);

        for (int i = 0; i <n ; i++) {

            for (int j = 0; j <n ; j++) {
                System.out.print(test[i][j]+" ");
            }
            System.out.println();
        }

        rotateRight(test,robot);
        for (int i = 0; i <n ; i++) {

            for (int j = 0; j <n ; j++) {
                System.out.print(test[i][j]+" ");
            }
            System.out.println();
        }


        int minTime=solution(board);

        bw.write(minTime+"\n");

        br.close();
        bw.close();
    }

    private static void rotateRight(int[][] board, Robot robot) {
    }

    private static void rotateLeft(int[][] board, Robot robot) {
    }

    private static int solution(int[][] board) {
        int answer=0;

        return answer;
    }
}
