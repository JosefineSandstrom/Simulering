package homeAssign2;

import java.util.*;
import java.io.*;

public class mainSimulation {


	public static void main(String[] args) {
		Random rand = new Random();
		int v = 2;

		Signal actSignal;
		new SignalList();
		
		int [] [] hall = new int [20][20]; 
		
		for(int i = 0; i < 20; i++){
			int startX = rand.nextInt(20);
			int startY = rand.nextInt(20);
			Student aStudent = new Student(startX, startY, v);
		}

		//Main loop
		while (G.time < 100000){
			actSignal = SignalList.FetchSignal();
			G.time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		//Calculations			
		

	}		

}
