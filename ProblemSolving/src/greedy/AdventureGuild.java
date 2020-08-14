package greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AdventureGuild {
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n= Integer.parseInt(br.readLine());


        List<Integer> fears=new ArrayList<>();

        String[]splits=br.readLine().split(" ");
        for(int i=0;i<n;i++){
           fears.add (Integer.parseInt(splits[i]));


        }

        Collections.sort(fears);

        int groupCount=0;

        int count=0;

        for(int i=0;i<fears.size();i++){
            count++;
            if(count>=fears.get(i)){
                groupCount++;
                count=0;
            }
        }







        bw.write(Integer.toString(groupCount)+"\n");


        
        

        

        br.close();
        bw.close();
    }

}
