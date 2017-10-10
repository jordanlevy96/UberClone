
public class Trip {
	private int[][] start;
	private int[][] destination;
	private double eta;
	
	public Trip(int[][] start, int[][] dest) {
		this.start = start;
		this.destination = dest;
		calculateETA();
	}
	
	public void calculateETA() {
		this.eta = 0;
	}
}
