package homeAssign2.src;

import java.util.*;

public class Student extends Proc {
	public int velocity;
	public int currentX;
	public int currentY;
	public int distance;
	public int direction; // number to represent which direction the student is walking
	Random rand = new Random();
	public int endX;
	public int endY;
	public double travelTime;
	public int totalMet;
	public int[] studentsMet;
	public int talkTime = 60;
	public boolean talking = false;

	public Student(int x, int y, int v) {
		currentX = x;
		currentY = y;
		velocity = v;
		totalMet = 0;
		studentsMet = new int [20];
	}

	public void TreatSignal(Signal x) {

		switch (x.signalType) {

		case WALK: {
			distance = 1 + rand.nextInt(10);
			direction = 1 + rand.nextInt(8);
			switch (direction) {

			case 1: { // straight up = 90 degrees
				endX = currentX;
				endY = Math.max(0, currentY - distance);
				travelTime = 1.0 / velocity;
			}
			break;

			case 2: { // 45 degrees
				if (currentX + distance > 19) {
					endX = 19;
					distance = 19 - currentX;
				} else {
					endX = (currentX + distance);
				}
				if (currentY - distance < 0) {
					endY = 0;
					distance = currentY;
					endX = currentX + distance;

				} else {
					endY = (int) (currentY - distance );
				}
				travelTime = Math.sqrt(2) / velocity;
			}
			break;

			case 3: { // 0 degrees
				endY = currentY;
				endX = Math.min(19, currentX + distance);
				travelTime = 1.0 / velocity;
			}
			break;

			case 4: { // -45 degrees
				if (currentX + distance > 19) {
					endX = 19;
					distance = 19 - currentX;
				} else {
					endX = (int) (currentX + distance);
				}
				if (currentY + distance > 19) {
					endY = 19;
					distance = 19 - currentY;
					endX = currentX+distance;
				} else {
					endY = (int) (currentY + distance );
				}
				travelTime = Math.sqrt(2) / velocity;
			}
			break;

			case 5: { // -90 degrees
				endX = currentX;
				endY = Math.min(19, currentY + distance);
				travelTime = 1.0 / velocity;
			}
			break;

			case 6: { // -135 degrees
				if (currentX - distance < 0) {
					endX = 0;
					distance = currentX;
				} else {
					endX = (int) (currentX - distance);
				}
				if (currentY + distance  > 19) {
					endY = 19;
					distance = 19 - currentY;
					endX = currentX - distance;
				} else {
					endY = (int) (currentY + distance );
				}
				travelTime = Math.sqrt(2) / velocity;
			}
			break;

			case 7: { // 180 degrees
				endY = currentY;
				endX = Math.max(0, currentX - distance);
				travelTime = 1.0 / velocity;
			}
			break;

			case 8: { // 135 degrees
				if (currentX - distance < 0) {
					endX = 0;
					distance = currentX;
				} else {
					endX = (int) (currentX - distance );
				}
				if (currentY - distance < 0) {
					endY = 0;
					distance = currentY;
					endX = currentX - distance;
				} else {
					endY = (int) (currentY - distance );
				}
				travelTime = Math.sqrt(2) / velocity;
			}
			break;
			}
			SignalList.SendSignal(CHECK, this, time + travelTime);
		}
		break;

		case TALK: {
			for (int i = 0; i < students.length; i++) { 
				if (students[i].currentX == currentX && students[i].currentY == currentY 
						&& students[i] != this) {
					if (studentsMet[i] == 0) {
						totalMet++;
						if (totalMet == 19) {
							doneStudents++;
						}
					}
					studentsMet[i]++;
				}
			}
			
			SignalList.SendSignal(ENDTALK, this, time + talkTime);
		}
		break;

		case CHECK: {
			if (talking){
				break;
			}
			if (currentX == endX && currentY == endY) {
				SignalList.SendSignal(WALK, this, time);
			} else {
				hall[currentX][currentY]--;
				move();
				hall[currentX][currentY]++;
				if (hall[currentX][currentY] == 2) {
					for (int i = 0; i < students.length; i++) {
						if (students[i].currentX == currentX && students[i].currentY == currentY
								&& students[i] != this) {
							SignalList.SendSignal(TALK, this, time);
							SignalList.SendSignal(TALK, students[i], time);
							students[i].talking = true;
							talking = true;
						}
					}

				} else {
					SignalList.SendSignal(CHECK, this, time + travelTime);
				}
			}
		}
		break;

		case ENDTALK:{
			talking = false;
			SignalList.SendSignal(CHECK, this, time + travelTime);
		}
		break;
		}
	}

	private void move() {
		switch (direction) {

		case 1: { // straight up = 90 degrees
			currentY--;
		}
		break;

		case 2: { // 45 degrees
			currentX++;
			currentY--;
		}
		break;

		case 3: { // 0 degrees
			currentX++;
		}
		break;

		case 4: { // -45 degrees
			currentX++;
			currentY++;
		}
		break;

		case 5: { // -90 degrees
			currentY++;
		}
		break;

		case 6: { // -135 degrees
			currentX--;
			currentY++;
		}
		break;

		case 7: { // 180 degrees
			currentX--;
		}
		break;

		case 8: { // 135 degrees
			currentX--;
			currentY--;
		}
		break;
		}
	}
}