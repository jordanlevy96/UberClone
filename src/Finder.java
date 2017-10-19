import java.util.PriorityQueue;

public class Finder {
	private PriorityQueue<Driver> availableDrivers;
	private PriorityQueue<Passenger> seekingPassengers;
	
	public Finder() {
		availableDrivers = new PriorityQueue<Driver>();
		seekingPassengers = new PriorityQueue<Passenger>();
	}
	
	public Driver requestDriver(int[][] start, int[][] dest) {
		return availableDrivers.poll(); //gets first available driver
		//should instead find the NEAREST driver
	}
	
	public Passenger findPassenger(int[][] loc) {
		return seekingPassengers.poll(); //gets first seeking passenger
		//not sure if necessary at all
	}
}
