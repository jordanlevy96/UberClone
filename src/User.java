import java.util.ArrayList;

public class User {
	protected String name;
	protected double balance;
	protected ArrayList<Review> reviews;
	protected double rating;
	protected Finder finder;
	
	public User(String name, double b, Finder f) {
		this.name = name;
		this.balance = b;
		this.rating = 5.0;
		this.finder = f;
	}
	
	public void addReview(Review r) {
		reviews.add(r);
		updateRating();
	}
	
	public void updateRating() {
		double avg = 0.0;
		for (Review r : reviews) {
			avg += r.getRating();
		}
		
		avg = avg / reviews.size();
		
		this.rating = avg;
	}
}