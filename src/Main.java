
public class Main {
	public static void main(String[] args) {
		Finder finder = new Finder();
		
		Driver driver1 = new Driver("Ned", new Vehicle("red", 1999, "Honda", "Civic"));
		Driver driver2 = new Driver("Catelyn", new Vehicle("black", 2001, "Ford", "Escape"));
		
		Passenger passenger1 = new Passenger("Sansa", 60);
		Passenger passenger2 = new Passenger("Robb", 140);
		
		driver1.setStatus(Status.AVAILABLE);
		driver2.setStatus(Status.AVAILABLE);
		
		finder.addDriver(driver1);
		finder.addDriver(driver2);
		
		TripManager.handleTrip(passenger1, Location.generateRandomLocation(), finder);
		TripManager.handleTrip(passenger2, Location.generateRandomLocation(), finder);
	}
}
