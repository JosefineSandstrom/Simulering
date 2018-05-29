import java.util.ArrayList;
import java.util.Random;

public class Sensor extends Proc{
	
	public double ts = 4000;
	public Proc sendTo;
	public int x;
	public int y;
	public boolean inReach;
//	public boolean isSending;
//	public ArrayList<Sensor> neighbors;
	public double lb = 0;
	public double ub = 1000;
	Random rand = new Random();
	public boolean temp = false;
	
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
				boolean allowedToSend = true; 
				
				
			for(int i = 0; i < sensorSending.size(); i++){
				double dist = Math.hypot(sensorSending.get(i).x - this.x, sensorSending.get(i).y - this.y);
				if(dist <= r){
					allowedToSend = false;
					break;
				}
			}
				
				
				if(!allowedToSend){
					if(inReach){ 
						SignalList.SendSignal(WAIT, sendTo, time + lb + (ub-lb)*rand.nextDouble());		
						
					} else { 
						SignalList.SendSignal(WAIT, this, time + lb + (ub-lb)*rand.nextDouble());
					}
				} else {
					sensorSending.add(this);
					if(inReach){ 
						SignalList.SendSignal(ARRIVAL, sendTo, time);				
					} else {
						SignalList.SendSignal(OUTOFRANGE, this, time + 1);
					}
				}					
			}break;
			
			case OUTOFRANGE:{	
				sensorSending.remove(0);
			}break;
			
			case WAIT:{	
				sensorSending.add(this);
				if(inReach){
					SignalList.SendSignal(ARRIVAL, this, time);
				} else {
					SignalList.SendSignal(OUTOFRANGE, this, time + 1);
				}
			}break;
			
		}
		
	}
	
	
}
