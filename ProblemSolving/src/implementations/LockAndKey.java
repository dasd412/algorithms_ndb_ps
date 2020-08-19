package implementations;

import java.io.*;

public class LockAndKey {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n,m;
        String[]splits=br.readLine().split(" ");
        n=Integer.parseInt(splits[0]);
        m=Integer.parseInt(splits[1]);

        int[][]key=new int[m][m];
        int[][]lock=new int[n][n];

        for(int i=0;i<m;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<m;j++){
                key[i][j]=Integer.parseInt(splits[j]);
            }
        }


        for(int i=0;i<n;i++){
            splits=br.readLine().split(" ");
            for(int j=0;j<n;j++){
                lock[i][j]=Integer.parseInt(splits[j]);
            }
        }

//        for(int a=0;a<4;a++){
//            rotateKey(key,a);
//            System.out.println();
//            for(int i=0;i<m;i++){
//
//                for(int j=0;j<m;j++){
//                    System.out.print(key[i][j]+" ");
//                }
//                System.out.println();
//            }
//
//        }

        boolean answer=canOpen(key,lock,n);


        bw.write(answer+"\n");


        br.close();
        bw.close();
    }


    public static boolean canOpen(int[][]key, int[][]lock,int n){

        /*
        자물쇠 3배 크기의 확장 배열을 만든다.
        */
        int[][]extension=new int[n*3][n*3];




        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                extension[n+i][n+j]=lock[i][j];//확장 배열의 정중앙에 자물쇠 배열을 놓는다.

            }
        }

        for(int rotate=0;rotate<4;rotate++){//해당 좌표에서  회전을 해본다.
            rotateKey(key);//열쇠 배열 회전
             for(int x=0;x<n*2;x++){//확장 배열을 순회한다. 단, 열 수 있는 게 확인되면 반복 종료
              for(int y=0;y<n*2;y++){


                  int[][] temp=new int[extension.length][extension.length];

                  for(int i=0;i<extension.length;i++){
                      for(int j=0;j<extension.length;j++){
                          temp[i][j]=extension[i][j];//임시 배열에 복사
                      }
                  }

                  for(int i=0;i<key.length;i++){


                      for(int j=0;j<key.length;j++){


                          temp[x+i][y+j]+=key[i][j];//키를 더해본다.



                      }//j
                  }//i


                  if(isOpen(temp)){
                      return true;
                  }



              }
          }
        }


        return false;
    }//canOpen

    public static  boolean isOpen(int[][]temp){
        for(int a=temp.length/3;a<(temp.length/3)*2;a++){
            for(int b=temp.length/3;b<(temp.length/3)*2;b++){
                if(temp[a][b]!=1){//1이 아닌게 존재한다면, 자물쇠가 열린 것이 아니다.

                    return false;
                }
            }

        }

        return true;
    }

    private static void rotateKey(int[][] key) {
        int[][]temp=new int[key.length][key.length];

        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){

                temp[i][j]=key[j][key.length-1-i];

            }
        }

        for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                key[i][j]=temp[i][j];
            }
        }

    }

}
