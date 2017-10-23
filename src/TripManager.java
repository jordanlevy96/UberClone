/**
 * Helper class that runs through all the steps of a Trip
 * @author Jordan
 *
 */
public class TripManager {
	/**
	 * The main function of the program; handles just about everything for a successful Trip
	 * @param passenger
	 * @param dest
	 * @param finder
	 * @return
	 */
	public static Trip handleTrip(Passenger passenger, Location dest, Finder finder) {
		//Passenger requests a ride
		Trip trip = passenger.requestRide(dest, finder);
		if (trip.getStatus() != CompletionStatus.COMPLETED) {
			//trip was cancelled
			return trip;
		}
		Driver driver = trip.getDriver();
		
		//Driver gets to Passenger
		driver.moveTo(passenger.getLocation());
		passenger.notify("Your driver has arrived!", "Look for a " + trip.getDriver().getVehicle().toString());
		
		//Arrive at destination
		driver.moveTo(dest);
		driver.notify(passenger.getName() + "'s trip is complete!", "Don't forget to leave a review!");
		passenger.moveTo(dest);
		passenger.notify("You have arrived!", "Don't forget to review your driver!");
		
		Review dReview = driver.generatePassengerReview(trip);
		passenger.addReview(dReview);
		Review pReview = passenger.generateDriverReview(trip);
		driver.addReview(pReview);
		driver.setStatus(Status.AVAILABLE);
		
		return trip;
	}
}
