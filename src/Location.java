/**
 * Class representation of a Location in a Cartesian plane
 * @author Jordan
 *
 */
public class Location {
	public int x;
	public int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Distance formula calculation for this Location and an Other Location
	 * @param other
	 * @return double value distance between Locations
	 */
	public double getDistanceFrom(Location other) {
		double distance = Math.sqrt(Math.pow((other.x - this.x), 2) + Math.pow((other.y - this.y), 2));
		return distance;
	}
	
	public String toString() {
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
	}

	/**
	 * Generate a random Location that fits inside a 300x300 grid
	 * @return
	 */
	public static Location generateRandomLocation() {
		int x = (int) (Math.random() * 300);
		int y = (int) (Math.random() * 300);
		System.out.println("Generating random location at (" + Integer.toString(x)
				+ ", " + Integer.toString(y) + ")");
		return new Location(x, y);
	}
}
