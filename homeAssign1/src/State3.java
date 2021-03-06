import java.util.*;
import java.io.*;

class State3{
	public int noArrivalTo1 = 0, numberInQueue1 = 0, numberInQueue2 = 0, accumulated1 = 0, accumulated2 = 0, noMeasurements = 0, noDepartures = 0;
	public double totalTime = 0;
	Random slump = new Random();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	LinkedList<Double> arrivalTimes = new LinkedList<Double>();

	public void TreatEvent(Event x){
		switch (x.eventType){
		case G.arrivalTo1:{
			arrivalTimes.addLast(G.time);
			noArrivalTo1++;
			if (numberInQueue1 == 0){
				EventList.InsertEvent(G.departureFrom1, G.time - (1.0)*Math.log(slump.nextDouble()));
			}
			numberInQueue1++;
			EventList.InsertEvent(G.arrivalTo1, G.time - (2.0)*Math.log(slump.nextDouble())); //Interarrival times = 2, 1,5 or 1,1
		} break;
		case G.departureFrom1:{
			numberInQueue1--;
			if (numberInQueue2 == 0){
				EventList.InsertEvent(G.departureFrom2, G.time - (1.0)*Math.log(slump.nextDouble()));
			}
			numberInQueue2++;
			if (numberInQueue1 > 0){
				EventList.InsertEvent(G.departureFrom1, G.time - (1.0)*Math.log(slump.nextDouble()));
			}
		} break;
		case G.departureFrom2:{
			noDepartures++;
			totalTime += G.time - arrivalTimes.poll();
			numberInQueue2--;
			if (numberInQueue2 > 0){
				EventList.InsertEvent(G.departureFrom2, G.time - (1.0)*Math.log(slump.nextDouble()));
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