import java.util.*;

public class Dispatch extends Proc{

	//The random number generator is started:
	Random slump = new Random();
	public Proc sendTo;    //Where to send customers
	public QS Q1, Q2, Q3, Q4, Q5;
	public int temp, noCustomers, noInQ1, noInQ2, noInQ3, noInQ4, noInQ5;
	public List<QS> list = new ArrayList<QS>();

	
	
	
	
	public void TreatSignal(Signal x) {
		switch (x.signalType){
		case ARRIVAL:{
			
			//Algorithm 1: Random
			temp = 1 + slump.nextInt(5);
			if (temp == 1){
				sendTo = Q1;
			} else if (temp == 2){
				sendTo = Q2;
			} else if (temp == 3){
				sendTo = Q3;
			} else if (temp == 4){
				sendTo = Q4;
			} else if (temp == 5){
				sendTo = Q5;
			}
			
			//Algorithm 2: Round Robin
//			noCustomers++;
//			temp = noCustomers % 5;
//			if (temp == 1){
//				sendTo = Q1;
//			} else if (temp == 2){
//				sendTo = Q2;
//			} else if (temp == 3){
//				sendTo = Q3;
//			} else if (temp == 4){
//				sendTo = Q4;
//			} else if (temp == 0){
//				sendTo = Q5;
//			}
						
			//Algorithm 3: Shortest queue
//			int min = Integer.MAX_VALUE;
//			int minIndex = 0;
//			list.add(Q1);
//			list.add(Q2);
//			list.add(Q3);
//			list.add(Q4);
//			list.add(Q5);
//			
//			//Find min
//			for(int i = 0; i < 5; i++){
//				if(compareTo(min,list.get(i).numberInQueue) > 0){
//					min = list.get(i).numberInQueue;
//					minIndex = i;
//				}
//			}
//			
//			List<QS> tempList = new ArrayList<QS>();
//			tempList.add(list.get(minIndex));
//			
//			//Check if several queues have same numberInQueue as the value min
//			for(int i = 0; i < 5; i++){
//				if(min == list.get(i).numberInQueue && minIndex != i){
//					tempList.add(list.get(i));
//				}
//			}
//			
//			//Choose queue randomly 
//			int chosenIndex = slump.nextInt(tempList.size());
//			
//			sendTo = tempList.get(chosenIndex);
//			
			
			
			SignalList.SendSignal(ARRIVAL, sendTo, time);
			break;
		}
		
		}
	}

	int compareTo(int x, int y){
		if(x > y){
			return 1;
		} else if (x == y){
			return 0;
		} else {
			return -1;
		}
	}
}

