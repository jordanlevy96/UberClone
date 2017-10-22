
public class TripManager {
	public static void handleTrip(Passenger passenger, Location dest, Finder finder) {
		//Passenger requests a ride
		Trip trip = passenger.requestRide(dest, finder);
		if (trip == null) {
			//trip was cancelled
			return;
		}
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
