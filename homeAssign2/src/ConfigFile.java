
import java.util.*;


public class ConfigFile {
	Random rand = new Random();
	LinkedList<SensorData> positions;
	SensorData gwPos = new SensorData(5000, 5000);
	SimpleFileWriter W; 
	
	public ConfigFile(){
			
		
		for(int i = 1; i <= 10; i++){
			positions = new LinkedList<SensorData>();
			StringBuilder sb = new StringBuilder("configFile");
			sb.append(i);
			sb.append(".txt");
			W = new SimpleFileWriter(sb.toString(), false);
			
			while(positions.size() < (i*1000)){
				
				int x = (int) (10000*rand.nextDouble());
				int y = (int) (10000*rand.nextDouble());
				SensorData sensorPos = new SensorData(x,y);
				
				if(!positions.contains(sensorPos) && !sensorPos.equals(gwPos)){
					positions.add(sensorPos);
					W.println("Point " + positions.size());
					W.println(String.valueOf(x) + ", " + String.valueOf(y));
				}
				
			}
			W.close();
		}
		
		
	}
	
}
	
	
	
	
	
	

