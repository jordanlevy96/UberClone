
public class Trip {
	private Driver driver;
	private Passenger passenger;
	private Location destination;
	private double eta;
	private double fare;
	private double totalDistance;
	
	private static final double PAYMENT_RATE = 3.5;
	
	public Trip(Driver driver, Passenger passenger, Location dest) {
		this.driver = driver;
		this.passenger = passenger;
		this.destination = dest;
		calculateFare();
		calculateETA();
	}
	
	public void calculateDistance() {
		double driverToPassenger = driver.getLocation().getDistanceFrom(passenger.getLocation());
		double passengerToDest = passenger.getLocation().getDistanceFrom(destination);
		this.totalDistance = driverToPassenger + passengerToDest;
	}
	
	public double getFare() {
		return this.fare;
	}
	
	public void calculateFare() {
		this.fare = this.totalDistance * PAYMENT_RATE;
	}
	
	public double getETA() {
		return this.eta;
	}
	
	public void calculateETA() {
		this.eta = 0;
	}
	
	public Passenger getPassenger() {
		return this.passenger;
	}
	
	public Driver getDriver() {
		return this.driver;
	}
}
