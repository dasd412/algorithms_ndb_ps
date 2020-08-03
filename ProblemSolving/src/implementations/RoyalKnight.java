package implementations;

import java.io.*;

public class RoyalKnight {

    static BufferedWriter bw;

    static String[]ys={"a","b","c","d","e","f","g","h"};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String dir=br.readLine();
        String[]splits=dir.split("");
        int x,y=0;
        x=Integer.parseInt(splits[1])-1;

        for(int i=0;i<ys.length;i++){
            if(ys[i].equals(splits[0])){
                y=i;
                break;
            }
        }


        int moveCase= getMoveCase(x,y);

        bw.write(Integer.toString(moveCase)+"\n");


        br.close();
        bw.close();
    }

    private static int getMoveCase(int x, int y) {//갈 수 있는 경우의 수 세는 매소드

        int moveCase=0;

        if(x-2>=0){//위로 2칸 갈 수 있는가
            if(y-1>=0){//왼쪽으로 1칸 갈 수 있는가
                moveCase++;
            }
            if(y+1<8){//오른쪽으로 1칸 갈 수 있는가
                moveCase++;
            }

        }

        if(x+2<8){//아래로 2칸 갈 수 있는 가

            if(y-1>=0){//왼쪽으로 1칸 갈 수 있는가
                moveCase++;
            }
            if(y+1<8){//오른쪽으로 1칸 갈 수 있는가
                moveCase++;
            }

        }

        if(y-2>=0){//왼쪽으로 2칸 갈 수 있는가

            if(x-1>=0){//위로 1칸 갈 수 있는가
                moveCase++;
            }
            if(x+1<8){//아래로 1칸 갈 수 있는가
                moveCase++;
            }
        }

        if(y+2<8){//오른쪽으로 2칸 갈 수 있는가
            if(x-1>=0){//위로 1칸 갈 수 있는가
                moveCase++;
            }
            if(x+1<8){//아래로 1칸 갈 수 있는가
                moveCase++;
            }
        }


        return moveCase;
    }

}
