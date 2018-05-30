import java.util.ArrayList;

public class G {
	public static final int WAKEUP = 1, SEND = 2, ARRIVAL = 3, PROCESSING_TEST = 4, PROCESSING_FAIL = 5, OUTOFRANGE = 6, WAIT = 7;
	public static double time = 0;
	public static boolean gwBusy;
	public static int succTran, unSuccTran, r, load;
	
	public static ArrayList<Sensor> sensorSending = new ArrayList<Sensor>();
}
