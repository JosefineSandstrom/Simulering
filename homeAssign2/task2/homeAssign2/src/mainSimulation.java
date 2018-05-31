package homeAssign2.src;


import java.util.*;
import java.io.*;

public class mainSimulation {


	public static void main(String[] args) {
		Random rand = new Random();
		int v;
		Signal actSignal;
		new SignalList(); 
		
		
		for(int i = 0; i < 20; i++){
			v = 1 + rand.nextInt(7); //2, 4 or 1 + rand.nextInt(7) m/s
			int startX = rand.nextInt(20);
			int startY = rand.nextInt(20);
			Student aStudent = new Student(startX, startY, v);
			G.hall[startX][startY]++;
			SignalList.SendSignal(G.WALK, aStudent, 0);
			G.students[i] = aStudent;
		}
		
//		for(int i = 0; i < G.students.length; i++) {
//			for(int j = 0; j < G.students.length; j++) {
//				if(G.students[i].currentX == G.students[j].currentX && 
//						G.students[i].currentY == G.students[j].currentY && i!=j) {
//					SignalList.SendSignal(G.TALK, G.students[i], 0);
//				} else {
//					SignalList.SendSignal(G.WALK, G.students[i], 0);
//				}
//			}
//		}

		//Main loop
		while (G.doneStudents < 20){
			actSignal = SignalList.FetchSignal();
			G.time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
			//System.out.println("Done students" + G.doneStudents);
		}
		

		//Calculations			
		System.out.println(G.time);
		

	}		

}
