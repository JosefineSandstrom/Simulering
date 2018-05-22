
import java.util.*;
import java.io.*;

public class mainSimulation {
	
	
	public static void main(String[] args) {
		Random rand = new Random();
		
		Signal actSignal;
		new SignalList();
		
		double ts = - (4000)*Math.log(rand.nextDouble());
		
		ConfigFile cf = new ConfigFile();
		
		for(int i = 1; i <= 10; i++){
			LinkedList<Position> sensorList = new LinkedList<Position>();
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
						String[] ar=str.split(", ");
						int x = Integer.parseInt(ar[0]);
						int y = Integer.parseInt(ar[1]);
						
						Position pos = new Position(x, y);
						sensorList.add(pos);
						
						
						//SignalList.SendSignal(WAKEUP,this, ts);
						
					}
				}
				in.close();
			} catch (IOException e) {
	            System.out.println("File Read Error");
	        }
			
			
			
			
			
			
			
			cf.W.close();
		}
		
		
	}

}
