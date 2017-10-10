
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
	
	
}
