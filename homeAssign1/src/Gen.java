import java.util.*;
import java.io.*;

//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen extends Proc{
	
	//The random number generator is started:
	Random slump = new Random();

	//There are two parameters:
	public Proc sendTo;    //Where to send customers
	public double mu;	//Mean arrival time 
	
	//What to do when a signal arrives
	public void TreatSignal(Signal x){
		switch (x.signalType){
			case READY:{
				SignalList.SendSignal(ARRIVAL, sendTo, time);
				SignalList.SendSignal(READY, this, time + (2.0*mu)*slump.nextDouble());}
				break;
		}
	}
}