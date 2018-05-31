
import java.util.Random;

public class Sensor extends Proc{

	public double ts = 4000;
	public Proc sendTo;
	public int x;
	public int y;
	public boolean inReach;
	public double lb = 0;
	public double ub = 1000;
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
			SignalList.SendSignal(SEND, this, this, time);
			SignalList.SendSignal(WAKEUP, this, this, time - (ts)*Math.log(rand.nextDouble()));
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
				SignalList.SendSignal(WAIT, this, this, time + lb + (ub-lb)*rand.nextDouble());		
			} else {
				sensorSending.add(this);
				if(inReach){ 
					SignalList.SendSignal(ARRIVAL, this, sendTo, time);				
				} else {
					SignalList.SendSignal(OUTOFRANGE, this, this, time + 1);
				}
			}					
		}break;

		case OUTOFRANGE:{	
			sensorSending.remove(this);
		}break;

		case WAIT:{	
			sensorSending.add(this);
			if(inReach){
				SignalList.SendSignal(ARRIVAL, this, sendTo, time);
			} else {
				SignalList.SendSignal(OUTOFRANGE, this, this, time + 1);
			}
		}break;

		}

	}


}
