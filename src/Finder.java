import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Utility class that keeps track of all registered Drivers and finds rides for seeking Passengers
 * @author Jordan
 *
 */
public class Finder {
	private ArrayList<Driver> availableDrivers;
	
	public Finder() {
		availableDrivers = new ArrayList<Driver>();
	}
	
	public void addDriver(Driver d) {
		availableDrivers.add(d);
	}
	
	/**
	 * Finds the closest (and highest rated) Driver to the passenger that would be able to
	 * give them a ride, and requests the Driver to do the Trip
	 * @param passenger
	 * @param dest(ination)
	 * @return Trip object with all relevant data, including cancellation status, if applicable
	 */
	public Trip requestDriver(Passenger passenger, Location dest) {
		Driver driver = null;
		Driver tempDriver;
		Location start = passenger.getLocation();
		Trip tempTrip;
		
		PriorityQueue<Driver> closestDrivers = new PriorityQueue<Driver>(10, //initial capacity does not matter
				new Comparator<Driver>() {
					@Override
					public int compare(Driver d1, Driver d2) {
						double dist1 = d1.getLocation().getDistanceFrom(start);
						double dist2 = d2.getLocation().getDistanceFrom(start);
						int comparison = Double.compare(dist1, dist2);
						if (comparison == 0) {
							return Double.compare(d2.getRating(), d1.getRating());
						}
						return comparison;
					}
				}
		);
		closestDrivers.addAll(availableDrivers);
		
		while (driver == null && !closestDrivers.isEmpty()) {
			tempDriver = closestDrivers.poll();
			if (!tempDriver.isAvailable()) {
				//this driver is not currently available
				continue;
			}
			tempTrip = new Trip(tempDriver, passenger, dest);
			if (tempTrip.getFare() > tempTrip.getPassenger().getBalance()) {
				/*
				   	though different rides from different drivers will have different fares,
					each iteration of this loop will be more expensive than the last
					meaning that if any are too expensive, all of the following will be as well,
					and the trip must be cancelled.
				*/
				
				return new Trip(passenger, CompletionStatus.INSUFFICIENT_FUNDS);
			}
			
			if (tempDriver.requestRide(tempTrip)) {
				driver = tempDriver;
			}
		}
		
		if (driver == null) {
			return new Trip(passenger, CompletionStatus.NO_DRIVER);
		}
		
		return new Trip(driver, passenger, dest);
	}
}
