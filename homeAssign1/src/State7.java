import java.util.*;
import java.io.*;

class State7{
	public int noMeasurements = 0;
	public double totalTime = 0;
	public boolean alive1 = true, alive2 = true, alive3 = true, alive4 = true, alive5 = true;
		
	Random slump = new Random();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	public void TreatEvent(Event x){
		switch (x.eventType){
		case G7.lifeSpan1:{
			 alive1 = false;
			 alive2 = false;
			 alive5 = false;
			 if(!(alive1 || alive2 || alive3 || alive4 || alive5)){
				 EventList.InsertEvent(G7.MEASURE, G7.time);
			 }
		} break;
		case G7.lifeSpan2:{
			alive2 = false;
			if(!(alive1 || alive2 || alive3 || alive4 || alive5)){
				 EventList.InsertEvent(G7.MEASURE, G7.time);
			 }
		} break;
		case G7.lifeSpan3:{
			 alive3 = false;
			 alive4 = false;
			 if(!(alive1 || alive2 || alive3 || alive4 || alive5)){
				 EventList.InsertEvent(G7.MEASURE, G7.time);
			 }
		} break;
		case G7.lifeSpan4:{
			 alive4 = false;
			 if(!(alive1 || alive2 || alive3 || alive4 || alive5)){
				 EventList.InsertEvent(G7.MEASURE, G7.time);
			 }
		} break;
		case G7.lifeSpan5:{
			 alive5 = false;
			 if(!(alive1 || alive2 || alive3 || alive4 || alive5)){
				 EventList.InsertEvent(G7.MEASURE, G7.time);
			 }
		} break;
		case G7.MEASURE:{
			noMeasurements++;
			totalTime += G7.time;
			alive1 = alive2 = alive3 = alive4 = alive5 = true;
			EventList.InsertEvent(G7.lifeSpan1, 1 + (5.0-1.0)*slump.nextDouble());	
	        EventList.InsertEvent(G7.lifeSpan2, 1 + (5.0-1.0)*slump.nextDouble());	
	        EventList.InsertEvent(G7.lifeSpan3, 1 + (5.0-1.0)*slump.nextDouble());	
	        EventList.InsertEvent(G7.lifeSpan4, 1 + (5.0-1.0)*slump.nextDouble());	
	        EventList.InsertEvent(G7.lifeSpan5, 1 + (5.0-1.0)*slump.nextDouble());
			W.println(String.valueOf(noMeasurements));
		} break;
		}
	}
}