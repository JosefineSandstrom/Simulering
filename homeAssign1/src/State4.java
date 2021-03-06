import java.util.*;
import java.io.*;

class State4{
	public int noBusyServers = 0, noMeasurements = 0, N = 100, lambda = 4, sTime = 10, T = 4;
	
	Random slump = new Random();
	SimpleFileWriter W = new SimpleFileWriter("number.m", false);
	
	public void TreatEvent(Event x){
		switch (x.eventType){
		case G4.ARRIVAL:{
			if (noBusyServers < N){
				noBusyServers++;
				EventList.InsertEvent(G4.DEPARTURE, G4.time + sTime);
			}
			EventList.InsertEvent(G4.ARRIVAL, G4.time - (1.0/lambda)*Math.log(slump.nextDouble())); 
		} break;
		case G4.DEPARTURE:{
			noBusyServers--;			
		} break;
		case G4.MEASURE:{
			noMeasurements++;
			
			EventList.InsertEvent(G4.MEASURE, G4.time - (1.0 * T)*Math.log(slump.nextDouble()));
			W.println(String.valueOf(noBusyServers));
		} break;
		}
	}
}