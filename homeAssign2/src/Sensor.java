import java.util.Random;

public class Sensor extends Proc{

	public int load, succTran, collisions;
	public double ts = 4000;
	public Proc sendTo;
	Random rand = new Random();
	
	public void TreatSignal(Signal x) {
		switch (x.signalType){
			
			case WAKEUP:{
				SignalList.SendSignal(SEND,this, time - (ts)*Math.log(rand.nextDouble()));
			} break;
			
			case SEND:{
				
			}break;
		}
		
	}
	
	
}
