
public class Review {
	private double rating;
	private Trip trip;
	private String comments;
	
	public Review(double rating, Trip trip, String comments) {
		if (rating > 5.0) {
			this.rating = 5.0;
		}
		else if (rating < 0) {
			this.rating = 0.0;
		}
		else {
			this.rating = rating;
		}
		
		this.trip = trip;
		this.comments = comments;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public String getComments() {
		return this.comments;
	}
}
