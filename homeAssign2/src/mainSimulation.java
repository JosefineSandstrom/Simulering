
import java.util.*;
import java.io.*;

public class mainSimulation {


	public static void main(String[] args) {
		Random rand = new Random();
		int [] radius = new int[]{6000, 7000, 8000, 9000, 10000, 11000};
		boolean inReach;
		//int noSimulations;
		Gateway gw = new Gateway();

		Signal actSignal;
		new SignalList();

		double ts = 4000;
		SimpleFileWriter fileToMatlab = new SimpleFileWriter("results.txt",false);

		//ConfigFile cf = new ConfigFile();

		for(int i = 0; i < radius.length; i++){ //changed for 1d)
			LinkedList<Sensor> sensorList = new LinkedList<Sensor>();
			StringBuilder sb = new StringBuilder("configFile");
			G.sensorSending.clear();
			sb.append(2);	//n = 2000
			sb.append(".txt");
			//noSimulations = 20000; //changed for 1d)
			G.time = 0;
			G.succTran = 0;
			gw.noStarts = 0;
			gw.noEnds = 0;
			G.unSuccTran = 0;
			G.gwBusy = false;
			G.load = 0;
			G.r = radius[i];
			
			
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
						inReach = dist <= G.r;
						

						Sensor aSensor = new Sensor(x, y, inReach);		
						aSensor.sendTo = gw;
						sensorList.add(aSensor);

					}
				}
				in.close();

			} catch (IOException e) {
				System.out.println("File Read Error");
			}		


			for(int j = 0; j < sensorList.size(); j++){ 
				SignalList.SendSignal(G.WAKEUP, sensorList.get(j), sensorList.get(j), G.time - (ts)*Math.log(rand.nextDouble()));
			}

			//Main loop
			while (G.time < 20000){//*(i)){
				actSignal = SignalList.FetchSignal();
				G.time = actSignal.arrivalTime;
				actSignal.destination.TreatSignal(actSignal);
				
			}
			//Calculations			
			double throughput = (1.0)*G.succTran / G.time; 
			double packetloss = (1.0)*G.unSuccTran / G.load;
			System.out.println(throughput);
			fileToMatlab.println(String.valueOf(packetloss));
		}		
		//cf.W.close();
		fileToMatlab.close();
	}

}
