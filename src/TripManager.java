
public class TripManager {
	public TripManager() {
		
	}
	
	public void handleTrip(Passenger passenger, Location dest, Finder finder) {
		//Passenger requests a ride
		Trip trip = passenger.requestRide(dest, finder);
		
	}
}
