package greedy;

import java.io.*;


public class AdventureGuild {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n= Integer.parseInt(br.readLine());
        int[]fears=new int[1000001];//모험가의 공포도 리스트



        int max=0;

        String[]splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
            int index= Integer.parseInt(splits[i]);
            fears[index]++;
            max=Math.max(max,index);
        }

        int groupCount=0;

        int i=1;
        for(;i<=max;i++){

            if(fears[i]>=i){//i의 공포도를 갖는 모험가가 i명 이상 존재하면

                for(int j=fears[i];j>=i;){//i미만이 되기 전까지
                    groupCount++;//그룹 하나를 만들고
                    j-=i;//넣은 모험가들을 명단에서 뺀다.
                }

            }
            else{//더 이상 i의 공포도를 갖는 모험가가 i명 이상 존재하지 않는다면, 나머지를 모아 처리할 수 있는지 확인한다.

                if(fears[i]!=0&&fears[i+1]+fears[i]>=i+1){//i번째와 i+1번째 공포도를 갖는 모험가들의 합이 i+1명 이상이라면 한 그룹으로 모아 처리한다.
                    groupCount++;
                    fears[i+1]-=fears[i];
                    fears[i]=0;
                }

            }
        }





        bw.write(Integer.toString(groupCount)+"\n");


        
        

        

        br.close();
        bw.close();
    }

}
