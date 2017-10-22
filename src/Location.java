
public class Location {
	public int x;
	public int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getDistanceFrom(Location other) {
		double distance = Math.sqrt(Math.pow((other.x - this.x), 2) + Math.pow((other.y - this.y), 2));
		return distance;
	}
	
	public String toString() {
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
	}

	public static Location generateRandomLocation() {
		int x = (int) (Math.random() * 300);
		int y = (int) (Math.random() * 300);
		System.out.println("Generating random location at (" + Integer.toString(x)
				+ ", " + Integer.toString(y) + ")");
		return new Location(x, y);
	}
}
