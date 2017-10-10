import java.util.ArrayList;

public class Finder {
	private ArrayList<Driver> availableDrivers;
	private ArrayList<Passenger> seekingPassengers;
	
	public Finder() {
		availableDrivers = new ArrayList<Driver>();
		seekingPassengers = new ArrayList<Passenger>();
	}
	
	public Driver requestDriver(int[][] start, int[][] dest) {
		return availableDrivers.get(0); //gets first available driver
		//should instead find the NEAREST driver
	}
	
	public Passenger findPassenger(int[][] loc) {
		return seekingPassengers.get(0); //gets first seeking passenger
		//not sure if necessary at all
	}
}
