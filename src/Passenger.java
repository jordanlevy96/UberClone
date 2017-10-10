
public class Passenger extends User {
	public Passenger(String name, double balance, Finder f) {
		super(name, balance, f);
	}
	
	public Driver requestRide(int[][] start, int[][] dest) {
		return finder.requestDriver(start, dest);
	}
}
