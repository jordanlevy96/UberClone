
public class Driver extends User {
	private Vehicle car;
	private Status status;
	
	private enum Status {
		OFFLINE,
		AVAILABLE,
		OCCUPIED;
	}
	
	public Driver(String name, Vehicle v, Finder f) {
		super(name, 100.00, f);
		this.car = v;
		this.status = Status.OFFLINE;
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
		}
		
		return answer;
	}
	
	private void payDriver(double amount) {
		this.balance += amount;
	}
}
