import org.json.simple.JSONObject;

/**
 * Class representation of a Passenger that requests rides from a Driver
 * @author Jordan
 *
 */
public class Passenger extends User {
	public Passenger(String name, double balance) {
		super(name, balance);
	}
	
	public Passenger(String name, double balance, Location l) {
		super(name, balance, l);
	}
	
	/**
	 * Using a finder to find a Driver, requests a ride to dest(ination)
	 * and pays for the ride, if successful
	 * @param dest
	 * @param finder
	 * @return Trip object detailing everything that happened in the Trip
	 */
	public Trip requestRide(Location dest, Finder finder) {
		Trip trip = finder.requestDriver(this, dest);
		
		if (trip.getStatus() == CompletionStatus.INSUFFICIENT_FUNDS) {
			notify("Insufficient funds!", "Trip cancelled!");
			return trip;
		}
		else if (trip.getStatus() == CompletionStatus.NO_DRIVER) {
			notify("Could not find Driver!", "Trip cancelled!");
			return trip;
		}
		else {
			payForRide(trip);
			trip.setStatus(CompletionStatus.COMPLETED);
			return trip;
		}
	}
	
	/**
	 * sneaky secret (and secure :p) private function for Passengers to pay for their Trip
	 * @param trip
	 */
	private void payForRide(Trip trip) {
		this.balance -= trip.getFare();
		notify("You have paid for your trip.");
	}
	
	/**
	 * Generates a random review for a Driver after a Trip, with relevant comments
	 * @param trip
	 * @return Review object detailing their satisfaction with the Driver
	 */
	public Review generateDriverReview(Trip trip) {
		String comments;
		
		Double rating = Math.random() * 10;
		if (rating > 5) {
			comments = "Incredible trip! Best driver ever! :)";
		}
		else if (rating > 3.5) {
			comments = "Nice job. Safe driver. Clean car.";
		}
		else if (rating > 2) {
			comments = "Got there in one piece. A bit rude but not wholly intolerable.";
		}
		else {
			comments = "Bad experience. Would not recommend.";
		}
		
		return new Review(rating, trip, comments);
	}
	
	/**
	 * Create JSON representation of the Passenger, including their final Location
	 * @return JSONObject
	 */
	public JSONObject exportToJSON() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("balance", this.balance);
		obj.put("location", this.location.toString());
		obj.put("rating", rating);
		
		return obj;
	}
}
