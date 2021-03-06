import java.util.*;
import java.io.*;

class State{
	public int noArrivalTo1 = 0, numberInQueue1 = 0, numberInQueue2 = 0, accumulated1 = 0, accumulated2 = 0, noMeasurements = 0, noRejected = 0;
	//public double rejectedProb;

	Random slump = new Random();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case G.arrivalTo1:{
				noArrivalTo1++;
				if (numberInQueue1 < 10){
					if (numberInQueue1 == 0){
						EventList.InsertEvent(G.departureFrom1, G.time - (2.1)*Math.log(slump.nextDouble()));
					}
					numberInQueue1++;
					
				} else {
					noRejected++;
				}
				EventList.InsertEvent(G.arrivalTo1, G.time + 5); //interarrival time = 1, 2 or 5
			} break;
			case G.departureFrom1:{
				numberInQueue1--;
				if (numberInQueue2 == 0){
					EventList.InsertEvent(G.departureFrom2, G.time + 2);
				}
				numberInQueue2++;
				if (numberInQueue1 > 0){
					EventList.InsertEvent(G.departureFrom1, G.time - (2.1)*Math.log(slump.nextDouble()));
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
				EventList.InsertEvent(G.MEASURE, G.time - (5.0)*Math.log(slump.nextDouble()));
				W.println(String.valueOf(numberInQueue1));
			} break;
		}
	}
}