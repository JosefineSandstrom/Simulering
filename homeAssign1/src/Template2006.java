import java.util.*;
import java.io.*;


public class Template2006 {
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	State actState = new State();
    	new EventList();
        EventList.InsertEvent(G.arrivalTo1, 0);	
        EventList.InsertEvent(G.MEASURE, 5);
    	while (actState.noMeasurements < 10000){
    		actEvent = EventList.FetchEvent();
    		G.time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	double rejectedProb = 1.0*actState.noRejected/actState.noArrivalTo1;
    	System.out.println("Mean number of customers in Q2: " + 1.0*actState.accumulated2/actState.noMeasurements);	//The mean nr of customers in queue 2
    	System.out.println(actState.noMeasurements);	//Number of measurements 
    	System.out.println("Prob rejection: " + rejectedProb);	//the probability that a customer is rejected at Q1
    	
    	actState.W.close();
    }
}