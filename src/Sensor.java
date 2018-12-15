import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sensor {
	protected int id;
	  protected int frequency;
	  protected String link;
	  protected int car;

	  public Sensor(int id, int frq, String lnk, int car) {
	    this.id = id;
	    this.frequency = frq;
	    this.link = lnk;
	    this.car = car;
	  }
	  
	  public static Sensor fileToSensor(File file) throws IOException {
	    FileInputStream tmp = new FileInputStream(file);
	    BufferedReader buffer = new BufferedReader(new InputStreamReader(tmp));

	    int sid = -1;
	    int frq = -1;
	    String lnk = "";
	    int car = -1;
	    String line = null;
	    while ((line = buffer.readLine()) != null) {
	      String key = line.substring(0, 3);
	      String info = line.substring(5);

	      switch (key) {
	        case "SID":
	          sid = Integer.parseInt(info);
	          break;
	        case "FRQ":
	          frq = Integer.parseInt(info);
	          break;
	        case "LNK":
	          lnk = info;
	          break;
	        case "CAR":
	            car = Integer.parseInt(info);
	            break;
	        default:
	          break;
	      }
	    }

	    buffer.close();

	    if (sid == -1 || frq == -1 || lnk == "" || car <= -1 || car > 1 ) {
	      throw new IOException();
	    }

	    return new Sensor(sid, frq, lnk,car);
	  }

	  public int getId() {
	    return this.id;
	  }

	  public int getFrequency() {
	    return this.frequency;
	  }

	  public String getLink() {
	    return this.link;
	  }
	  public int getCar() {
		    return this.car;
		  }
	  public String toString() {
	    return "Sensor " + id + " (" + frequency + "s, \"" + link + "\")" + " Presence car : " + car + " ";
	  }
}
