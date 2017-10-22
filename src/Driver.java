
public class Driver extends User {
	private Vehicle car;
	private Status status;
	
	public Driver(String name, Vehicle v) {
		super(name, 100.00);
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
	
	public void setStatus(Status s) {
		this.status = s;
	}
}
