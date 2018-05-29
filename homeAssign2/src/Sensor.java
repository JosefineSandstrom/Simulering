import java.util.ArrayList;
import java.util.Random;

public class Sensor extends Proc{
	
	public double ts = 4000;
	public Proc sendTo;
	public int x;
	public int y;
	public boolean inReach;
	public boolean isSending;
	public ArrayList<Sensor> neighbors;
	public double lb = 0;
	public double ub = 10;
	Random rand = new Random();
	public boolean temp = false;
	
	public Sensor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Sensor(int x, int y, boolean inReach, boolean isSending) {
		super();
		this.x = x;
		this.y = y;
		this.inReach = inReach;
		this.isSending = isSending;
	}
	
	public Sensor(int x, int y, boolean inReach, boolean isSending,ArrayList<Sensor> neighbors) {
		super();
		this.x = x;
		this.y = y;
		this.inReach = inReach;
		this.isSending = isSending;
		this.neighbors = neighbors;
	}
	
	
	public void TreatSignal(Signal x) {
		switch (x.signalType){
			
			case WAKEUP:{
				SignalList.SendSignal(SEND,this, time);
				SignalList.SendSignal(WAKEUP, this, time - (ts)*Math.log(rand.nextDouble()));
			} break;
			
			case SEND:{				
				boolean allowedToSend = true; 
				
				for(int i = 0; i < neighbors.size(); i++){
					if(neighbors.get(i).isSending){
						allowedToSend = false;
						break;
					}
				}
				
				if(!allowedToSend){
					if(inReach){ 
						double timeTmp = lb + (ub-lb)*rand.nextDouble();
						SignalList.SendSignal(SENDING, this, time + timeTmp);
						SignalList.SendSignal(ARRIVAL, sendTo, time + timeTmp);		
						
					} else { 
						temp = true;
						SignalList.SendSignal(OUTOFRANGE, this, time + lb + (ub-lb)*rand.nextDouble());
					}
				} else {
					if(inReach){ 
						SignalList.SendSignal(SENDING, this, time);
						SignalList.SendSignal(ARRIVAL, sendTo, time);				
					} else {
						isSending = true;
						SignalList.SendSignal(OUTOFRANGE, this, time + 1);
					}
				}					
			}break;
			
			case OUTOFRANGE:{	
				if(temp){
					isSending = true;
					temp = false;
					SignalList.SendSignal(OUTOFRANGE, this, time + 1);
				}
				isSending = false;
			}break;
			
			case SENDING:{	
				isSending = true;
				SignalList.SendSignal(DONESENDING, this, time + 1);
			}break;
			
			case DONESENDING:{	
				isSending = false;
			}break;
		}
		
	}
	
	
}
