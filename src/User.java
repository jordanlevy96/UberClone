import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class User {
	protected String name;
	protected double balance;
	protected ArrayList<Review> reviews;
	protected double rating;
	protected Location location;
	protected Status status;
	
	public User(String name, double b) {
		this.name = name;
		this.balance = b;
		this.rating = 4.0;
		this.status = Status.OFFLINE;
	}
	
	protected enum Status {
		OFFLINE,
		AVAILABLE,
		OCCUPIED;
	}
	
	public void moveTo(Location destination) {
		Timer t = new Timer();
		long delay = (long) this.location.getDistanceFrom(destination) * 100;
		
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				location = destination;
			}
		}, delay);
		
		notify("Arrived at " + location.toString());
	}
	
	public void addReview(Review r) {
		reviews.add(r);
		updateRating();
	}
	
	private void updateRating() {
		double avg = 0.0;
		for (Review r : reviews) {
			avg += r.getRating();
		}
		
		avg = avg / reviews.size();
		
		this.rating = avg;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location l) {
		this.location = l;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void notify(String... messages) {
		System.out.println("MESSAGE TO " + this.name);
		
		for (String message : messages) {
			System.out.print("[");
			System.out.println(message);
			System.out.print("]");
		}
	}
}
