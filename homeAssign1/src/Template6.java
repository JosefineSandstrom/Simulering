import java.util.*;
import java.io.*;


public class Template6 {
 
    public static void main(String[] args) throws IOException {
    	
    	Event actEvent;
    	State6 actState = new State6();
    	new EventList();
    	
        EventList.InsertEvent(G6.ARRIVAL, 0);	
        //EventList.InsertEvent(G6.MEASURE, 5);
        
    	while (actState.noMeasurements < 1000){
    		actEvent = EventList.FetchEvent();
    		G6.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);   		
    	}
    	
    	System.out.println("The average work time: " + 1.0*actState.goHomeTime / 1000);
    	System.out.println("The average time in system " + 1.0*actState.totalTimeCustomer/actState.noCustomers);
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	
    	
    	actState.W.close();
    }
}