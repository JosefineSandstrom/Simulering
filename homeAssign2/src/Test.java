

import java.util.*;

import java.io.*;

public class Test {


	public static void main(String[] args) {
		Random rand = new Random();
		G.r = 7000;
		boolean inReach;
		int noSimulations;
		Gateway gw = new Gateway();


		Signal actSignal;
		new SignalList();

		double ts = 4000;
		SimpleFileWriter fileToMatlab = new SimpleFileWriter("results.txt",false);

		//ConfigFile cf = new ConfigFile();

		for(int i = 1; i <= 10; i++){
			LinkedList<Sensor> sensorList = new LinkedList<Sensor>();
			StringBuilder sb = new StringBuilder("configFile");
			sb.append(i);
			sb.append(".txt");
			noSimulations = 10000*i;
			G.time = 0;
			G.succTran = 0;
			gw.noStarts = 0;
			gw.noEnds = 0;
			G.unSuccTran = 0;
			G.gwBusy = false;
			G.load = 0;
			G.sensorSending.clear();

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
				SignalList.SendSignal(G.WAKEUP,sensorList.get(j), sensorList.get(j), G.time - (ts)*Math.log(rand.nextDouble()));
			}

			//Main loop
			while (noSimulations > 0){
				actSignal = SignalList.FetchSignal();
				G.time = actSignal.arrivalTime;
				actSignal.destination.TreatSignal(actSignal);
				noSimulations--;
			}

			//Calculations			
			double throughput = (1.0)*G.succTran / G.time; 
			double packetloss = (1.0)*G.unSuccTran / G.load;
			//System.out.println(packetloss);
			System.out.println(throughput);
			fileToMatlab.println(String.valueOf(packetloss));
		}		
		//cf.W.close();
		fileToMatlab.close();
	}

}