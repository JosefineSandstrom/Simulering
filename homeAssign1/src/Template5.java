import java.util.*;
import java.io.*;


public class Template5 extends G5{

	public static void main(String[] args) throws IOException {

		// The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
		// signal list in the main loop below.

		Signal actSignal;
		new SignalList();

		// Here process instances are created (five queues and one generator) and their parameters are given values. 

		Gen Generator = new Gen();		
		Dispatch disp = new Dispatch(); //Dispatch instance are created
		Generator.sendTo = disp; 		// The generated customers shall be sent to DISPATCH
		
		QS Q1 = new QS();
		disp.Q1 = Q1;		
		
		QS Q2 = new QS();
		disp.Q2 = Q2;
		
		QS Q3 = new QS();
		disp.Q3 = Q3;
		
		QS Q4 = new QS();
		disp.Q4 = Q4;
		
		QS Q5 = new QS();		
		disp.Q5 = Q5;
		
		
		Generator.mu = 2.0; //mean arrival times = 0.11, 0.15 or 2.0
		
		//To start the simulation the first signals are put in the signal list
		SignalList.SendSignal(READY,Generator, time);
		SignalList.SendSignal(MEASURE, Q1, time);
		SignalList.SendSignal(MEASURE, Q2, time);
		SignalList.SendSignal(MEASURE, Q3, time);
		SignalList.SendSignal(MEASURE, Q4, time);
		SignalList.SendSignal(MEASURE, Q5, time);
		
		
		
		// This is the main loop
    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}
		
    	
    	//Finally the result of the simulation is printed below:
    	double meanNoJobs = (Q1.accumulated / Q1.noMeasurements + Q2.accumulated / Q2.noMeasurements + 
    			Q3.accumulated / Q3.noMeasurements + Q4.accumulated / Q4.noMeasurements + Q5.accumulated / Q5.noMeasurements);
    	System.out.println("Mean number jobs in system: " + meanNoJobs);
    	
    	double meanTimeSystem = (Q1.totalTimeInSystem / Q1.noReady + Q2.totalTimeInSystem / Q2.noReady + Q3.totalTimeInSystem /
    			Q3.noReady + Q4.totalTimeInSystem / Q4.noReady + Q5.totalTimeInSystem / Q5.noReady)/5;
    	System.out.println("Mean time in system: " + meanTimeSystem);
    	
    	System.out.println("lambda in Little's theorem: " + meanNoJobs / meanTimeSystem);
    	
    	
		
	}
}