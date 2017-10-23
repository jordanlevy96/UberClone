import java.io.*;

import org.json.simple.JSONObject;

/**
 * Abstract representation of an Uber Trip
 * Contains Users involved in the Trip (i.e., Driver and Passenger) as well as their starting positions
 * before the trip. Estimated Time of Arrival (ETA), both for the Driver to pick up the Passenger and for the
 * Passenger to reach their destination from when they make the request, are calculated as eta1 and eta2.
 * Fare and total distance are calculated based on static PAYMENT_RATE and TRAVEL_RATE, which were carefully
 * chosen to create realistic-looking data.
 * Finally, CompletionStatus shows how the Trip is going -- especially useful for cancellations.
 * @author Jordan
 *
 */
public class Trip {
	private Driver driver;
	private Passenger passenger;
	private Location destination;
	private Location passengerStart;
	private Location driverStart;
	private double eta1; //driver to passenger
	private double eta2; //start to destination
	private double fare;
	private double totalDistance;
	private CompletionStatus status;
	
	private static final double PAYMENT_RATE = 0.25; //dollars per distance unit
	private static final double TRAVEL_RATE = 0.15; //time per distance unit
	
	public Trip(Driver driver, Passenger passenger, Location dest) {
		this.driver = driver;
		this.passenger = passenger;
		this.destination = dest;
		
		this.driverStart = driver.getLocation();
		this.passengerStart = passenger.getLocation();
		
		double driverToPassenger = driver.getLocation().getDistanceFrom(passenger.getLocation());
		double passengerToDest = passenger.getLocation().getDistanceFrom(destination);
		this.totalDistance = driverToPassenger + passengerToDest;
		
		this.eta1 = driverToPassenger * TRAVEL_RATE;
		this.eta2 = this.totalDistance * TRAVEL_RATE;
		
		this.fare = this.totalDistance * PAYMENT_RATE;
		this.status = CompletionStatus.IN_PROGRESS;
		
	}
	
	/**
	 * Special Constructor for bogus Trips with various cancellation statuses
	 * @param p
	 * @param s
	 */
	public Trip(Passenger p, CompletionStatus s) {
		this.passenger = p;
		this.status = s;
	}
	
	/**
	 * Generates a JSON file with all of the attributes and information about the Trip
	 * @param name
	 */
	public void exportJSON(String name) {
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		
		obj.put("passenger", passenger.exportToJSON());
		
		if (status == CompletionStatus.COMPLETED) {
			obj.put("passengerStart", passengerStart);
			
			obj.put("driver", driver.exportToJSON());
			obj.put("driverStart", driverStart.toString());
			
			obj.put("destination", destination.toString());
			obj.put("timeToPassenger", eta1);
			obj.put("timeToDestination", eta2);
			obj.put("fare", fare);
			obj.put("totalDistance", totalDistance);
		}
		
		try {
			FileWriter fw = new FileWriter(new File(name +".json"));
			fw.write(obj.toJSONString());
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Something went wrong while exporting JSON.");
			e.printStackTrace();
		}
		
	}
	
	public double getFare() {
		return this.fare;
	}
	
	public double getETA1() {
		return this.eta1;
	}
	
	public double getETA2() {
		return this.eta2;
	}
	
	public Passenger getPassenger() {
		return this.passenger;
	}
	
	public Driver getDriver() {
		return this.driver;
	}
	
	public Location getDestination() {
		return this.destination;
	}
	
	public void setStatus(CompletionStatus s) {
		this.status = s;
	}

	public CompletionStatus getStatus() {
		return this.status;
	}
}
