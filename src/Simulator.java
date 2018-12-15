
public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    SensorManager sensorManager = new SensorManager(10, "./sensors");
	    sensorManager.start();
	    System.out.println("Simulator is started!");
	}

}
