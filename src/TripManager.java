
public class TripManager {
	public TripManager() {
		
	}
	
	public void handleTrip(Passenger passenger, Location dest, Finder finder) {
		//Passenger requests a ride
		Trip trip = passenger.requestRide(dest, finder);
		Driver driver = trip.getDriver();
		
		//Driver gets to Passenger
		driver.moveTo(passenger.getLocation());
		passenger.notify("Your driver has arrived!", "Look for a " + trip.getDriver().getVehicle().toString());
		
		//Arrive at destination
		driver.moveTo(dest);
		passenger.moveTo(dest);
		passenger.notify("You have arrived!", "Don't forget to review your driver!");
		
		Review review = passenger.generateReview(trip);
		driver.addReview(review);
		driver.setStatus(Status.AVAILABLE);
	}
}
