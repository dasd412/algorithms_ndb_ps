package greedy;

public class LiveOfMuzi {

    public int solution(int[] food_times, long k) {
        int answer = 0;

        long time=0;//시간 경과를 나타내는 변수
        int index=0;//음식의 인덱스

        long total=0;
        for(int i=0;i<food_times.length;i++){
            total+=food_times[i];//음식 총량을 미리 계산한다.
        }


        int i=0;

        while(time<=k){//시간이 k가 될때까지 반복한다.
            if(index==food_times.length){//인덱스가 마지막이라면, 다시 처음으로 돌아간다.
                index=0;
            }
            if(total<=0){
                return -1;//남은 음식이 없다면, -1 리턴.
            }


            if(food_times[index]==0){//해당 인덱스의 음식이 없다면 시간 경과 없이 넘긴다.
                index++;


            }
            else{//해당 인덱스의 음식이 남아있다면,



                food_times[index]--;//먹는다.
                total--;//음식 총량을 하나 감소시킨다.
                index++;//다음 음식으로 넘긴다.
                time++;//시간이 경과한다.

            }
            i++;
        }
        answer=index;

        return answer;
    }
}
