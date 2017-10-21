import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;

public class Finder {
	private ArrayList<Driver> availableDrivers;
//	private PriorityQueue<Passenger> seekingPassengers;
	
	public Finder() {
		availableDrivers = new ArrayList<Driver>();
//		seekingPassengers = new PriorityQueue<Passenger>();
	}
	
	public Driver requestDriver(Location start, Location dest) {
		Driver driver = null;
		Driver temp;
		PriorityQueue<Driver> closestDrivers = new PriorityQueue<Driver>(availableDrivers.size(),
				new Comparator<Driver>() {
					@Override
					public int compare(Driver d1, Driver d2) {
						double dist1 = d1.getLocation().getDistanceFrom(start);
						double dist2 = d2.getLocation().getDistanceFrom(start);
						int comparison = Double.compare(dist1, dist2);
						if (comparison == 0) {
							return Double.compare(d1.getRating(), d2.getRating());
						}
						return comparison;
					}
				}
		);
		closestDrivers.addAll(availableDrivers);
		
		while (driver == null && !closestDrivers.isEmpty()) {
			temp = closestDrivers.poll();
			if (temp.requestRide()) {
				driver = temp;
			}
		}
		
		return driver;
	}
	
//	public Passenger findPassenger(Location loc) {
//		return seekingPassengers.poll(); //gets first seeking passenger
//		//not sure if necessary at all
//	}
}
