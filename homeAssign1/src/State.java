import java.util.*;
import java.io.*;

class State{
	public int numberInQueue1 = 0, numberInQueue2 = 0, accumulated1 = 0, accumulated2 = 0, noMeasurements = 0, noRejected = 0;
	//public double rejectedProb;

	Random slump = new Random();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case G.arrivalTo1:{
				if (numberInQueue1 < 10){
					numberInQueue1++;
					if (numberInQueue1 == 1){
						EventList.InsertEvent(G.departureFrom1, G.time - (1/2.1)*Math.log(slump.nextDouble()));
					}
					EventList.InsertEvent(G.arrivalTo1, G.time + 1); //interarrival time = 1, 2 or 5
				} else {
					noRejected++;
				}				
			} break;
			case G.departureFrom1:{
				numberInQueue1--;
				numberInQueue2++;
				if (numberInQueue2 == 1){
					EventList.InsertEvent(G.departureFrom2, G.time + 2);
				}
				if (numberInQueue1 > 0){
					EventList.InsertEvent(G.departureFrom1, G.time - (1/2.1)*Math.log(slump.nextDouble()));
				}
			} break;
			case G.departureFrom2:{
				numberInQueue2--;
				if (numberInQueue2 > 0){
					EventList.InsertEvent(G.departureFrom2, G.time + 2);
				}
			} break;
			case G.MEASURE:{
				noMeasurements++;
				accumulated1 = accumulated1 + numberInQueue1;
				accumulated2 = accumulated2 + numberInQueue2;
				EventList.InsertEvent(G.MEASURE, G.time - (1.0/5)*Math.log(slump.nextDouble()));
				W.println(String.valueOf(numberInQueue1));
			} break;
		}
	}
}