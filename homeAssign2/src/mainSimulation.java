
import java.util.*;
import java.io.*;

public class mainSimulation {
	
	
	public static void main(String[] args) {
		Random rand = new Random();
		int r = 7000;
		boolean inReach;
		int noSimulations;
		Gateway gw = new Gateway();
		
		Signal actSignal;
		new SignalList();
		
		double ts = - (4000)*Math.log(rand.nextDouble());
		SimpleFileWriter fileToMatlab = new SimpleFileWriter("results.m",false);
		
		ConfigFile cf = new ConfigFile();
		
		for(int i = 1; i <= 10; i++){
			LinkedList<Sensor> sensorList = new LinkedList<Sensor>();
			StringBuilder sb = new StringBuilder("configFile");
			sb.append(i);
			sb.append(".txt");
			noSimulations = 1000*i*100;
			G.time = 0;
			G.succTran = 0;
			//G.unSuccTran = 0; ??????
						
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
						
						Sensor aSensor = new Sensor(x, y, inReach);		
						aSensor.sendTo = gw;
						sensorList.add(aSensor);
						
					}
				}
				in.close();
				
			} catch (IOException e) {
	            System.out.println("File Read Error");
	        }		
			
			for(int k = 0; k < sensorList.size(); k++){
				
				SignalList.SendSignal(G.WAKEUP,sensorList.get(k), G.time - (ts)*Math.log(rand.nextDouble()));
			}
			
			//Main loop
			while (noSimulations > 0){
	    		actSignal = SignalList.FetchSignal();
	    		G.time = actSignal.arrivalTime;
	    		actSignal.destination.TreatSignal(actSignal);
	    		noSimulations--;
	    	}
			
			double throughput = (1.0)*(G.succTran / G.time); 
			//System.out.println("Time: " + G.time);
			//System.out.println("succTran:" + G.succTran);
			double packetloss = (1.0)*G.unSuccTran / G.load;
			//System.out.println(packetloss);
			System.out.println(throughput);
			//fileToMatlab.println(String.valueOf(packetloss));
		}		
		cf.W.close();
		fileToMatlab.close();
	}

}
