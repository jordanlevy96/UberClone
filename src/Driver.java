import org.json.simple.JSONObject;

/**
 * Class representation of a Driver who picks up and drops off Passengers
 * @author Jordan
 *
 */
public class Driver extends User {
	private Vehicle car;
	private Status status;
	
	public Driver(String name, Vehicle v) {
		super(name, 100.00);
		this.car = v;
		this.status = Status.AVAILABLE;
	}
	
	public Driver(String name, Vehicle v, Location l) {
		super(name, 100.00, l);
		this.car = v;
		this.status = Status.AVAILABLE;
	}

	/**
	 * Ask if a Driver is willing to do a Trip for a Passenger
	 * In lieu of a real human making decisions, there is just a random 10% chance that
	 * the Driver says no :)
	 * @param trip
	 * @return boolean value, whether or not the Driver will take the Trip
	 */
	public boolean requestRide(Trip trip) {
		boolean answer = true;
		
		double chance = Math.random() * 10;
		if (chance < 1) {
			answer = false;
		}
		
		if (trip.getFare() > trip.getPassenger().getBalance()) {
			//insufficient funds!
			answer = false;
		}
		
		if (answer) {
			double amount = trip.getFare();
			payDriver(amount * 0.75);
			//payUber(amount * 0.25);
			
			this.status = Status.OCCUPIED;
			trip.getPassenger().notify(this.getName() + " has accepted your trip.",
					"They are now en route to your location; ETA " + trip.getETA1() + " minutes",
					"You should arrive at your destination in " + trip.getETA2() + " minutes.");
		}
		
		return answer;
	}
	
	/**
	 * sneaky private class (nice and secure ;) ) to pay drivers for their service
	 * @param amount
	 */
	private void payDriver(double amount) {
		this.balance += amount;
		notify("You have been paid for your upcoming ride.");
	}
	
	/**
	 * checks status of Driver
	 * @return boolean value, whether or not the Driver is available
	 */
	public boolean isAvailable() {
		if (this.status == Status.AVAILABLE) {
			return true;
		}
		
		return false;
	}
	
	public Vehicle getVehicle() {
		return this.car;
	}
	
	/**
	 * generate random Review for a Passenger with relevant comments
	 * @param trip
	 * @return Review object
	 */
	public Review generatePassengerReview(Trip trip) {
		double rating = Math.random() * 10;
		String comments;
		
		if (rating > 5.0) {
			comments = "Great passenger! Very kind and courteous.";
		}
		else if (rating > 3.5) {
			comments = "Decent. Stayed quiet but mostly respectful";
		}
		else if (rating > 1.5) {
			comments = "Bad passenger. Rude and disrespectful.";
		}
		else {
			comments = "Do not let this person in your car.";
		}
		
		return new Review(rating, trip, comments);
	}
	
	public void setStatus(Status s) {
		this.status = s;
	}
	
	/**
	 * Create JSON Object representation of Driver, including their final Location and average rating
	 * @return JSONObject
	 */
	public JSONObject exportToJSON() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("car", this.car.toString());
		obj.put("balance", this.balance);
		obj.put("location", this.location.toString());
		obj.put("rating", rating);
		
		return obj;
	}
}
