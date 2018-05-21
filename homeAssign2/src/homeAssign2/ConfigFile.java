package homeAssign2;

import java.util.*;


public class ConfigFile {
	private static double ts = 4000;
	Random rand = new Random();
	LinkedList<Position> sensorList = new LinkedList<Position>();
	Position gwPos = new Position(500, 500);
	
	
	public ConfigFile(){
		
		
		while(sensorList.size() <= 1000){
			
			int x = (int) (10000*rand.nextDouble());
			int y = (int) (10000*rand.nextDouble());
			Position sensorPos = new Position(x,y);
			
			if(!sensorList.contains(sensorPos) && !sensorPos.equals(gwPos)){
				sensorList.add(sensorPos);
			}
		}
		
	}
	
}
	
	
	
	
	
	//W.println(String.valueOf());

