import static org.junit.Assert.*;

import org.junit.Test;

public class TestCases {

	@Test
	public void testDriverAttributes() {
		Vehicle franksCar = new Vehicle("red", 2000, "Honda", "Accord");
		Driver d = new Driver("Frank", franksCar);
		
		assertEquals(d.getName(), "Frank");
		assertEquals(d.getBalance(), 100.0, 0.001); //100 is default value for Driver balance
		assertEquals(d.getVehicle(), franksCar);
	}
	
	@Test
	public void testPassengerAttributes() {
		Passenger p = new Passenger("Dee", 12);
		
		assertEquals(p.getName(), "Dee");
		assertEquals(p.getBalance(), 12.0, 0.001);
	}
	
	@Test
	/**
	 * make sure locations stay inside 300 x 300 grid when generated
	 */
	public void testRandomLocations() {
		Location l1 = Location.generateRandomLocation();
		Location l2 = Location.generateRandomLocation();
		Location l3 = Location.generateRandomLocation();
		
		assertTrue(l1.x <= 300);
		assertTrue(l1.y <= 300);
		
		assertTrue(l2.x <= 300);
		assertTrue(l2.y <= 300);
		
		assertTrue(l3.x <= 300);
		assertTrue(l3.y <= 300);
	}
	
	@Test
	public void testRatingPriority() {
		Finder f = new Finder();
		Driver d1 = new Driver("Alfred", new Vehicle("red", 2017, "Chevy", "Bolt"), new Location(10, 10));
		Driver d2 = new Driver("Bruce", new Vehicle("white", 2000, "Honda", "Civic"), new Location(10, 10));
		Passenger p = new Passenger("Selina", 10000);
		
		//give both drivers bogus reviews to make one better than the other
		d1.addReview(new Review(10.0, new Trip(d1, p, Location.generateRandomLocation()), ""));
		d2.addReview(new Review(0.0, new Trip(d2, p, Location.generateRandomLocation()), ""));
		
		f.addDriver(d1);
		f.addDriver(d2);
		
		Trip t = TripManager.handleTrip(p, Location.generateRandomLocation(), f);
		
		System.out.println(d1.getRating());
		System.out.println(d2.getRating());
		
		assertEquals(t.getDriver().getName(), "Alfred"); //Alfred was the one that got the good review
	}
	
	@Test
	public void testInsufficientFunds() {
		Driver d = new Driver("Peanut Butter", new Vehicle("blue", 2010, "Honda", "Accord"), new Location(10, 10));
		Passenger p = new Passenger("Jelly", 1, new Location(250, 250));
		
		Finder finder = new Finder();
		finder.addDriver(d);
		
		Trip t = TripManager.handleTrip(p, new Location(100, 100), finder);
		
		assertTrue(t.getStatus() == CompletionStatus.INSUFFICIENT_FUNDS);
	}
	
	@Test
	public void testNoDriver() {
		Driver d = new Driver("Strawberry", new Vehicle("blue", 2010, "Honda", "Accord"), new Location(10, 10));
		Passenger p = new Passenger("Banana", 1, new Location(250, 250));
		
		Finder finder = new Finder();
//		finder.addDriver(d);
		
		Trip t = TripManager.handleTrip(p, new Location(100, 100), finder);
		
		assertTrue(t.getStatus() == CompletionStatus.NO_DRIVER);
	}

}
