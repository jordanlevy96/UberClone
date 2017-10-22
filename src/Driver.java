
public class Driver extends User {
	private Vehicle car;
	private Status status;
	
	public Driver(String name, Vehicle v, Finder f) {
		super(name, 100.00);
		this.car = v;
		
	}
	
	public Vehicle getCar() {
		return this.car;
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
					"They are now en route to your location.");
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
}
