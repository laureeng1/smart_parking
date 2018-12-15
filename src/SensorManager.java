import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SensorManager {
	 
	  protected String sensorsPath = "./sensors";
	  protected  int frequency; // Scan frequency in seconds
	  protected ArrayList<String> sensorFiles = new ArrayList<String>();

	  public SensorManager(int frequency, String sensorsPath) {
		    this.frequency = frequency;
		    this.sensorsPath = sensorsPath;
		  }

	  private ArrayList<String> listNewFiles() {
		  String[] files = new File(this.sensorsPath).list();
	    ArrayList<String> newFiles = new ArrayList<String>();

	    for (String file : files) {
	      if (!this.sensorFiles.contains(file))
	        newFiles.add(file);
	    }

	    return newFiles;
	  }

	  private ArrayList<Sensor> listNewSensors() {
	    // Listing new files
	    ArrayList<String> newFiles = listNewFiles();

	    if (newFiles.size() > 0)
	      this.sensorFiles.addAll(newFiles);

	    // Reading new files
	    ArrayList<Sensor> newSensors = new ArrayList<Sensor>();

	    newFiles.forEach(file -> {
	      String path = this.sensorsPath + "/" + file;

	      try {
	        newSensors.add(Sensor.fileToSensor(new File(path)));
	      } catch (Exception e) {
	        System.out.println("ERROR for reading sensor file \"" + file + "\"");
	      }
	    });

	    return newSensors;
	  }

	  public void start() {
	    TimerTask timerTask = new TimerTask() {
	      public void run() {
	        ArrayList<Sensor> newSensors = listNewSensors();

	        if (newSensors.size() > 0) {
	          System.out.println("New sensor(s) added: " + newSensors);
	        }
	      }
	    };

	    Timer timer = new Timer();
	    timer.schedule(timerTask, 0, this.frequency * 1000);
	  }
}
