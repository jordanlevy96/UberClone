
public class Passenger extends User {
	public Passenger(String name, double balance, Finder f) {
		super(name, balance, f);
	}
	
	public Trip requestRide(Location dest) {
		Trip trip = finder.requestDriver(this, dest);
		
		if (trip == null) {
			//Trip cancelled due to insufficient funds
			return null;
		}
		else if (trip.getDriver() == null) {
			System.out.println("Could not find Driver!");
			System.out.println("Trip cancelled!");
			return null;
		}
		else {
			payForRide(trip);
			return trip;
		}
	}
	
	private void payForRide(Trip trip) {
		this.balance -= trip.getFare();
	}
}
