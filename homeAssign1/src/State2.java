import java.util.*;
import java.io.*;

class State2{
	Random slump = new Random();
	
	public int AinQueue = 0, BinQueue = 0, accumulated = 0, noMeasurements = 0;
	public double xA = 0.002, xB = 0.004, lambda = 150, d = 1;
	
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);

	public void TreatEvent(Event x){
		switch (x.eventType){
		case G2.arrivalA:{			
			if (BinQueue+AinQueue == 0){
				EventList.InsertEvent(G2.departureA, G2.time + xA);
			}
			AinQueue++;
			EventList.InsertEvent(G2.arrivalA, G2.time - (1.0/150)*Math.log(slump.nextDouble())); 				
		} break;
		case G2.arrivalB:{
			if (AinQueue+BinQueue == 0){
				EventList.InsertEvent(G2.departureB, G2.time + xB);
			} 	
			BinQueue++;
		} break;
		case G2.departureA:{
			AinQueue--;
			EventList.InsertEvent(G2.arrivalB, G2.time + d);
			if (AinQueue > 0){
				EventList.InsertEvent(G2.departureA, G2.time + xA);
			} else if (BinQueue > 0){
				EventList.InsertEvent(G2.departureB, G2.time + xB);				
			}
		} break;
		case G2.departureB:{
			BinQueue--;
			if (AinQueue > 0){
				EventList.InsertEvent(G2.departureA, G2.time + xA);
			} else if (BinQueue > 0){
				EventList.InsertEvent(G2.departureB, G2.time + xB);				
			}
		} break;
		case G2.MEASURE:{
			noMeasurements++;
			accumulated = accumulated + AinQueue + BinQueue;
			EventList.InsertEvent(G2.MEASURE, G2.time + 0.1);
			W.println(String.valueOf(accumulated));
		} break;
		}
	}
}