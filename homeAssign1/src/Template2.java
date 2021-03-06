import java.util.*;
import java.io.*;


public class Template2 {
 
    public static void main(String[] args) throws IOException {
    	//Random slump = new Random();
    	//System.out.println((1.0/150)*Math.log(slump.nextDouble()));
    	
    	Event actEvent;
    	State2 actState = new State2();
    	new EventList();
        EventList.InsertEvent(G2.arrivalA, 0);	
        EventList.InsertEvent(G2.MEASURE, 5);
    	while (actState.noMeasurements < 1000){
    		actEvent = EventList.FetchEvent();
    		G2.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	
    	System.out.println("The mean number of jobs in the buffer: " + 1.0*actState.accumulated/actState.noMeasurements);
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	
    	
    	actState.W.close();
    }
}