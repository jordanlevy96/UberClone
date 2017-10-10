
public class Review {
	private double rating;
	private String comments;
	
	public Review(double rating, String comments) {
		if (rating > 5.0) {
			this.rating = 5.0;
		}
		else if (rating < 0) {
			this.rating = 0.0;
		}
		else {
			this.rating = rating;
		}
		
		this.comments = comments;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public String getComments() {
		return this.comments;
	}
}
