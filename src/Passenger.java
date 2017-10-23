import org.json.simple.JSONObject;

public class Passenger extends User {
	public Passenger(String name, double balance) {
		super(name, balance);
	}
	
	public Passenger(String name, double balance, Location l) {
		super(name, balance, l);
	}
	
	public Trip requestRide(Location dest, Finder finder) {
		Trip trip = finder.requestDriver(this, dest);
		
		if (trip == null) {
			//Trip cancelled due to insufficient funds
			return null;
		}
		else if (trip.getDriver() == null) {
			notify("Could not find Driver!", "Trip cancelled!");
			trip.setStatus(CompletionStatus.NO_DRIVER);
			return trip;
		}
		else {
			payForRide(trip);
			trip.setStatus(CompletionStatus.COMPLETED);
			return trip;
		}
	}
	
	private void payForRide(Trip trip) {
		this.balance -= trip.getFare();
		notify("You have paid for your trip.");
	}
	
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
	
	public JSONObject exportToJSON() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("balance", this.balance);
		obj.put("location", this.location.toString());
		obj.put("rating", rating);
		
		return obj;
	}
}
