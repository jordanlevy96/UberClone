
public class Trip {
	private Driver driver;
	private Passenger passenger;
	private Location destination;
	private double eta1; //driver to passenger
	private double eta2; //start to destination
	private double fare;
	private double totalDistance;
	
	private static final double PAYMENT_RATE = 0.25; //dollars per distance unit
	private static final double TRAVEL_RATE = 0.15; //time per distance unit
	
	public Trip(Driver driver, Passenger passenger, Location dest) {
		this.driver = driver;
		this.passenger = passenger;
		this.destination = dest;
		
		if (driver == null || passenger == null) {
			//this is possible if no drivers are available for a trip
			return;
		}
		
		double driverToPassenger = driver.getLocation().getDistanceFrom(passenger.getLocation());
		double passengerToDest = passenger.getLocation().getDistanceFrom(destination);
		this.totalDistance = driverToPassenger + passengerToDest;
		
		this.eta1 = driverToPassenger * TRAVEL_RATE;
		this.eta2 = this.totalDistance * TRAVEL_RATE;
		
		this.fare = this.totalDistance * PAYMENT_RATE;
		
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
}
