
public class Trip {
	private Location driverStart;
	private Location passengerStart;
	private Location destination;
	private double eta;
	private double fare;
	private double totalDistance;
	
	private static final double PAYMENT_RATE = 3.5;
	
	public Trip(Location driver, Location passenger, Location dest) {
		this.driverStart = driver;
		this.passengerStart = passenger;
		this.destination = dest;
		calculateFare();
		calculateETA();
	}
	
	public void calculateDistance() {
		double driverToPassenger = driverStart.getDistanceFrom(passengerStart);
		double passengerToDest = passengerStart.getDistanceFrom(destination);
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
}
