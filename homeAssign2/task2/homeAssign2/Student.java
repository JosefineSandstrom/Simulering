package homeAssign2;

public class Student extends Proc{
	public int startX;
	public int startY;
	public int velocity;
	public int currentX;
	public int currentY;

	public Student(int x, int y, int v) {
		startX = x;
		startY = y;
		velocity = v;
	}
	
	
	public void TreatSignal(Signal x) {

		switch (x.signalType){

			case WALK:{
									
			} break;
			
			case TALK:{
				
			} break;
			
			case CHECK:{
				
			} break;
		}
	}
}
