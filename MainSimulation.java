import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.
    	// The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
    	// signal list in the main loop below.

    	Signal actSignal;
    	new SignalList();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.
    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q3 = new QS();
    	Q3.sendTo = null;
    
    	QS Q2 = new QS();
    	Q2.sendTo = Q3;
    	
    	QS Q1 = new QS();
    	Q1.sendTo = Q2;

    	Gen Generator = new Gen();
    	Generator.lambda = 9; //Generator ska generera nio kunder per sekund  //Generator shall generate 9 customers per second
    	Generator.sendTo = Q1; //De genererade kunderna ska skickas till k�systemet QS  // The generated customers shall be sent to Q1
    
    	boolean is1Busy = false;
    	boolean is2Busy = false;
    	
    	if(!is1Busy){
			is1Busy = true; 
   	    	SignalList.SendSignal(READY, Generator, time, 1);
	    	SignalList.SendSignal(MEASURE, Q1, time, 1);
	    	SignalList.SendSignal(MEASURE, Q2, time, 1);
	    	SignalList.SendSignal(MEASURE, Q3, time, 1);
	    	
    		
    		
    	} else if(!is2Busy){
    		 
		} else {
			
		}
    	//H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
    	//To start the simulation the first signals are put in the signal list




    	// Detta �r simuleringsloopen:
    	// This is the main loop

    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:
    	//Finally the result of the simulation is printed below:

    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q1.accumulated/Q1.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q2.accumulated/Q2.noMeasurements);
    	System.out.println("Mean number of customers in queuing system: " + 1.0*Q3.accumulated/Q3.noMeasurements);

    }
}