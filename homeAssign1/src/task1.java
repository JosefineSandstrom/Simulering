
public class task1 extends G{

	public static void main(String[] args) {
		// The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
    	// signal list in the main loop below.

    	Signal actSignal;
    	new SignalList();
    	
    	// Here process instances are created (two queues and one generator) and their parameters are given values. 

    	QS Q2 = new QS();
    	Q2.sendTo = null;
    
    	QS Q1 = new QS();
    	Q2.sendTo = Q2;

    	Gen Generator = new Gen();
    	Generator.lambda = 1; //Generator shall generate 1, 1/2 or 1/5 customers per second
    	Generator.sendTo = Q1; // The generated customers shall be sent to Q1
    	
    	
    
	}

}
