import java.util.*;
import java.io.*;


public class Template3 {
 
    public static void main(String[] args) throws IOException {
    	
    	Event actEvent;
    	State3 actState = new State3();
    	new EventList();
        EventList.InsertEvent(G.arrivalTo1, 0);	
        EventList.InsertEvent(G.MEASURE, 5);
    	while (actState.noMeasurements < 10000){
    		actEvent = EventList.FetchEvent();
    		G.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	double accumulated = actState.accumulated1 + actState.accumulated2;
    	System.out.println("The mean number of customers in the queuing network: " + 1.0*accumulated/actState.noMeasurements);
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	System.out.println("The mean time a customer spends in the system: " + (1.0)*actState.totalTime/actState.noDepartures);
    	
    	actState.W.close();
    }
}