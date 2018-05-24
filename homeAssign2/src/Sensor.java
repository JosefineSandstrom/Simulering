import java.util.Random;

public class Sensor extends Proc{
	
	public double ts = 4000;
	public Proc sendTo;
	public int x;
	public int y;
	public boolean inReach;
	Random rand = new Random();
	
	public Sensor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Sensor(int x, int y, boolean inReach) {
		super();
		this.x = x;
		this.y = y;
		this.inReach = inReach;
	}
	
	public void TreatSignal(Signal x) {
		switch (x.signalType){
			
			case WAKEUP:{
				SignalList.SendSignal(SEND,this, time);
				SignalList.SendSignal(WAKEUP, this, time - (ts)*Math.log(rand.nextDouble()));
			} break;
			
			case SEND:{
				if(inReach){ 
					load++; 
					SignalList.SendSignal(ARRIVAL, sendTo, time);					
				}
			}break;
		}
		
	}
	
	
}
