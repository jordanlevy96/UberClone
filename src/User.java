import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstract class for Passenger and Driver to inherit from.
 * Describes their many shared attributes and functionality.
 * @author Jordan
 *
 */
public abstract class User {
	protected String name;
	protected double balance;
	protected ArrayList<Review> reviews;
	protected double rating;
	protected Location location;
	
	public User(String name, double b) {
		this.name = name;
		this.balance = b;
		this.rating = 4.0;
		this.location = Location.generateRandomLocation();
		this.reviews = new ArrayList<Review>();
	}
	
	public User(String name, double b, Location l) {
		this.name = name;
		this.balance = b;
		this.rating = 4.0;
		this.location = l;
		this.reviews = new ArrayList<Review>();
	}
	
	/**
	 * changes current Location of User, including a short delay based on how far they are moving
	 * @param destination
	 */
	public void moveTo(Location destination) {
		Timer t = new Timer();
		long delay = (long) this.location.getDistanceFrom(destination) * 500;
		
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				location = destination;
			}
		}, delay);
		
		notify("Arrived at " + location.toString());
	}
	
	/**
	 * adds a new Review, r, to this User's list of Reviews and updates its average rating
	 * @param r
	 */
	public void addReview(Review r) {
		reviews.add(r);
		updateRating();
	}
	
	/**
	 * Averages the user's rating from their Reviews
	 */
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
	
	/**
	 * Sends out a message (in print statements) addressed to this User
	 * @param messages
	 */
	public void notify(String... messages) {
		System.out.println("MESSAGE TO: " + this.name);
		
		for (String message : messages) {
			System.out.print("[");
			System.out.print(message);
			System.out.println("]");
		}
		System.out.println();
	}
}
