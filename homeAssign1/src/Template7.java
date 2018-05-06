import java.util.*;
import java.io.*;


public class Template7 {
 
    public static void main(String[] args) throws IOException {
    	Random slump = new Random();
    	
    	Event actEvent;
    	State7 actState = new State7();
    	new EventList();
    	
        EventList.InsertEvent(G7.lifeSpan1, 1 + (5.0-1.0)*slump.nextDouble());	
        EventList.InsertEvent(G7.lifeSpan2, 1 + (5.0-1.0)*slump.nextDouble());	
        EventList.InsertEvent(G7.lifeSpan3, 1 + (5.0-1.0)*slump.nextDouble());	
        EventList.InsertEvent(G7.lifeSpan4, 1 + (5.0-1.0)*slump.nextDouble());	
        EventList.InsertEvent(G7.lifeSpan5, 1 + (5.0-1.0)*slump.nextDouble());	
        
        
    	while (actState.noMeasurements < 1000){
    		actEvent = EventList.FetchEvent();
    		G7.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);   		
    	}
    	
    	System.out.println("The mean time until system breaks down: " + 1.0*actState.totalTime / 1000);
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	
    	
    	actState.W.close();
    }
}