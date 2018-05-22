
import java.util.*;


public class ConfigFile {
	Random rand = new Random();
	ArrayList<Position> sensorList;
	Position gwPos = new Position(500, 500);
	SimpleFileWriter W; 
	
	public ConfigFile(){
			
		
		for(int i = 1; i <= 10; i++){
			sensorList = new ArrayList<Position>();
			StringBuilder sb = new StringBuilder("configFile");
			sb.append(i);
			sb.append(".txt");
			W = new SimpleFileWriter(sb.toString(), false);
			
			while(sensorList.size() < (i*1000)){
				
				int x = (int) (10000*rand.nextDouble());
				int y = (int) (10000*rand.nextDouble());
				Position sensorPos = new Position(x,y);
				
				if(!sensorList.contains(sensorPos) && !sensorPos.equals(gwPos)){
					sensorList.add(sensorPos);
					W.println("Point " + sensorList.size());
					W.println(String.valueOf(x) + ", " + String.valueOf(y));				
//					W.println(String.valueOf(x));
//					W.println(String.valueOf(y));
				}
				
			}
			
		}
		
		
	}
	
}
	
	
	
	
	
	

