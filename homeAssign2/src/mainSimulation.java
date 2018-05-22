
import java.util.*;
import java.io.*;

public class mainSimulation {
	
	
	public static void main(String[] args) {
		Random rand = new Random();
		int r = 7000;
		boolean inReach;
		
		Signal actSignal;
		new SignalList();
		
		double ts = - (4000)*Math.log(rand.nextDouble());
		
		ConfigFile cf = new ConfigFile();
		
		for(int i = 1; i <= 10; i++){
			LinkedList<SensorData> sensorList = new LinkedList<SensorData>();
			StringBuilder sb = new StringBuilder("configFile");
			sb.append(i);
			sb.append(".txt");
						
			try{
				BufferedReader in = new BufferedReader(new FileReader(sb.toString()));
				String str; 
				int j = 0;		
				while((str = in.readLine())  != null){				
					j++;
					if(j % 2 == 0){						
						String[] ar = str.split(", ");
						int x = Integer.parseInt(ar[0]);
						int y = Integer.parseInt(ar[1]);
			
						double dist = Math.hypot(5000-x,5000-y);
						inReach = dist <= r;
						
						SensorData aSensor = new SensorData(x, y, inReach);					
						sensorList.add(aSensor);		
					}
				}
				in.close();
				
			} catch (IOException e) {
	            System.out.println("File Read Error");
	        }		
			
			//TODO: Start processes
			
		}		
		cf.W.close();
	}

}
