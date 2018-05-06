import java.util.*;
import java.io.*;


public class Template5 {
 
    public static void main(String[] args) throws IOException {
    	
    	Event actEvent;
    	State5 actState = new State5();
    	new EventList();
        EventList.InsertEvent(G5.ARRIVAL, 0);	
        EventList.InsertEvent(G4.MEASURE, 5);
    	while (actState.noMeasurements < 4000){
    		actEvent = EventList.FetchEvent();
    		G4.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	
    	
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	
    	
    	actState.W.close();
    }
}