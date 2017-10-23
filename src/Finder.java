import java.util.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;

public class Finder {
	private ArrayList<Driver> availableDrivers;
	
	public Finder() {
		availableDrivers = new ArrayList<Driver>();
	}
	
	public void addDriver(Driver d) {
		availableDrivers.add(d);
	}
	
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
							return Double.compare(d1.getRating(), d2.getRating());
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
				
				passenger.notify("Insufficient funds!", "Trip cancelled!");
				Trip trip = new Trip(null, passenger, dest);
				trip.setStatus(CompletionStatus.INSUFFICIENT_FUNDS);
				return trip;
			}
			
			if (tempDriver.requestRide(tempTrip)) {
				driver = tempDriver;
			}
		}
		
		return new Trip(driver, passenger, dest);
	}
}
