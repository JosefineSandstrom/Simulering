import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc{
	public int numberInQueue = 0, noReady;
	public double accumulated, noMeasurements, totalTimeInSystem;
	public Proc sendTo;
	Random slump = new Random();
	LinkedList<Double> arrivalTimes = new LinkedList<Double>();

	public void TreatSignal(Signal x){
		switch (x.signalType){

			case ARRIVAL:{				
				arrivalTimes.addLast(time);
				if (numberInQueue == 0){
					SignalList.SendSignal(READY,this, time - (0.5)*Math.log(slump.nextDouble()));
				}
				numberInQueue++;
				
			} break;

			case READY:{
				noReady++;
				totalTimeInSystem += time - arrivalTimes.poll();
				numberInQueue--;
				if (numberInQueue > 0){
					SignalList.SendSignal(READY, this, time - (0.5)*Math.log(slump.nextDouble()));
				}
				
			} break;

			case MEASURE:{
				noMeasurements++;
				accumulated = accumulated + numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + 2.0*slump.nextDouble());
			} break;
		}
	}
}