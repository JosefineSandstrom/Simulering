import java.util.*;
import java.io.*;


public class Template2006 {
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	State actState = new State();
    	new EventList();
        EventList.InsertEvent(G.arrivalTo1, 1);	
        EventList.InsertEvent(G.MEASURE, 5);
    	while (actState.noMeasurements < 10000){
    		actEvent = EventList.FetchEvent();
    		G.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	double meanQ1 = 1.0*actState.accumulated1/actState.noMeasurements;
    	double rejectedProb = 1.0*actState.noRejected/(meanQ1+actState.noRejected);
    	System.out.println(1.0*actState.accumulated2/actState.noMeasurements);	//The mean nr of customers in queue 2
    	//System.out.println(actState.accumulated);	//Nr of customers in queue when sampling
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	System.out.println(rejectedProb);	//the probability that a customer is rejected at Q1
    	actState.W.close();
    }
}