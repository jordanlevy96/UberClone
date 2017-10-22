
public class Passenger extends User {
	public Passenger(String name, double balance) {
		super(name, balance);
	}
	
	public Trip requestRide(Location dest, Finder finder) {
		Trip trip = finder.requestDriver(this, dest);
		
		if (trip == null) {
			//Trip cancelled due to insufficient funds
			return null;
		}
		else if (trip.getDriver() == null) {
			notify("Could not find Driver!", "Trip cancelled!");
			return null;
		}
		else {
			payForRide(trip);
			return trip;
		}
	}
	
	private void payForRide(Trip trip) {
		this.balance -= trip.getFare();
		notify("You have paid for your trip.");
	}
}
