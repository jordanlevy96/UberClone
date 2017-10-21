
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
}
