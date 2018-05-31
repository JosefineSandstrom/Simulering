import java.util.Random;

public class Gateway extends Proc{

	public int noStarts, noEnds;
	public double tm = 1;
	Random rand = new Random();


	public void TreatSignal(Signal x) {

		switch (x.signalType){

			case ARRIVAL:{
				noStarts++;
				load++;
				if(gwBusy){
					SignalList.SendSignal(PROCESSING_FAIL, x.source, this, time + tm);
				} else {
					gwBusy = true;
					SignalList.SendSignal(PROCESSING_TEST, x.source, this, time + tm);
				}						
			} break;
	
			case PROCESSING_TEST:{
				noEnds++;
				sensorSending.remove(x.source);
				if(noEnds == noStarts){
					succTran++;
					gwBusy = false;
				} else {
					unSuccTran++;
				}
			}break;
			
			case PROCESSING_FAIL:{
				unSuccTran++;
				sensorSending.remove(x.source);	
				noEnds++;
				if(noEnds == noStarts){
					gwBusy = false;
				}
			}break;
		}

	}

}
