import org.json.simple.JSONObject;

public class Driver extends User {
	private Vehicle car;
	private Status status;
	
	public Driver(String name, Vehicle v) {
		super(name, 100.00);
		this.car = v;
	}
	
	public Driver(String name, Vehicle v, Location l) {
		super(name, 100.00, l);
		this.car = v;
	}

	public boolean requestRide(Trip trip) {
		boolean answer = true; //TODO: make this an actual decision
		
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
	
	private void payDriver(double amount) {
		this.balance += amount;
		notify("You have been paid for your upcoming ride.");
	}
	
	public boolean isAvailable() {
		if (this.status == Status.AVAILABLE) {
			return true;
		}
		
		return false;
	}
	
	public Vehicle getVehicle() {
		return this.car;
	}
	
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
