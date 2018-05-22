

public class SensorData {
	public int x;
	public int y;
	public boolean inReach;
	
	public SensorData(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public SensorData(int x, int y, boolean inReach) {
		super();
		this.x = x;
		this.y = y;
		this.inReach = inReach;
	}
	
	public int getX(SensorData p){
		return p.x;
	}
	
	public int getY(SensorData p){
		return p.y;
	}

}
