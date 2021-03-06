import java.util.*;
import java.io.*;

class State6{
	public int noMeasurements = 0, lambda = 4, noCustomers = 0, noInQueue = 0;
	public double goHomeTime = 0, totalTimeCustomer = 0;
	
	Random slump = new Random();
	LinkedList<Double> arrivalTimes = new LinkedList<Double>();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	public void TreatEvent(Event x){
		switch (x.eventType){
		case G6.ARRIVAL:{
			if (G6.time <= 8){
				arrivalTimes.addLast(G6.time);
				if(noInQueue == 0){
					EventList.InsertEvent(G6.DEPARTURE, G6.time + 1.0/6 + (1.0/3-1.0/6)*slump.nextDouble());
				}	
				noInQueue++;
				EventList.InsertEvent(G6.ARRIVAL, G6.time - (1.0/lambda)*Math.log(slump.nextDouble()));
			} else {
				if (noInQueue == 0){
					EventList.InsertEvent(G6.MEASURE, G6.time);
				}
			} 
		} break;
		case G6.DEPARTURE:{
			totalTimeCustomer += G6.time - arrivalTimes.poll();
			noCustomers++;
			noInQueue--;
			if(G6.time > 8){
				if(noInQueue == 0){
					EventList.InsertEvent(G6.MEASURE, G6.time);
				} 
			}
			if(noInQueue > 0){
				EventList.InsertEvent(G6.DEPARTURE, G6.time + 1.0/6 + (1.0/3-1.0/6)*slump.nextDouble());
			}
		} break;
		case G6.MEASURE:{
			noMeasurements++;
			goHomeTime += G6.time % 24;
			//System.out.println("totalTimeCustomer: " + totalTimeCustomer);
			//G6.time = 0;
			EventList.InsertEvent(G6.ARRIVAL, 0);
			W.println(String.valueOf(noMeasurements));
		} break;
		}
	}
}