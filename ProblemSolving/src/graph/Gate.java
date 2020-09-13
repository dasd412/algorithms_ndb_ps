package graph;

import java.io.*;

public class Gate {

    static BufferedWriter bw;

    static class Info implements Comparable<Info>{

        private int flight;
        private int gate;

        public Info(int flight, int gate){
            this.flight=flight;
            this.gate=gate;
        }

        @Override
        public int compareTo(Info other) {
            return this.gate-other.gate;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g=Integer.parseInt(br.readLine());
        int p=Integer.parseInt(br.readLine());

        Info[]infos=new Info[p];


        for (int i = 0; i <p ; i++) {
            int gate=Integer.parseInt(br.readLine());
            infos[i]=new Info(i+1,gate);
        }


        int max=getMax(infos,g,p);
        bw.write(max+"\n");

        br.close();
        bw.close();
    }

    private static int getMax(Info[] infos, int g, int p) {

        boolean[]has=new boolean[g+1];

        int max=0;


        for (int i = 0; i <infos.length ; i++) {
            int gate=infos[i].gate;


            boolean hasChanged=false;

            while(gate>0){

                if(!has[gate]){
                    max++;
                    has[gate]=true;
                    hasChanged=true;
                    break;
                }

                gate--;
            }

            if(!hasChanged){
                break;
            }


        }






        return max;
    }


}
