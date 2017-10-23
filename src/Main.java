import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * Do stuff! :)
 * @author Jordan
 *
 */
public class Main {
	public static void main(String[] args) {
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		ArrayList<Trip> trips = new ArrayList<Trip>();
		
		Finder finder = new Finder();
						
		extractDataFromFile("src/input_data.txt", drivers, passengers);
		
		for (Driver d : drivers) {
//			d.setStatus(Status.AVAILABLE);
			finder.addDriver(d);
		}
		
		for (Passenger p : passengers) {
			trips.add(TripManager.handleTrip(p, Location.generateRandomLocation(), finder));
		}
		
		handleJSON(trips, drivers, passengers);
	}

	/**
	 * Read from file (filename) to extract Drivers and Passengers
	 * Drivers appear as "driver <name> <car color> <car year> <car make> <car model> <Location x> <Location y>"
	 * Passengers appear as "passenger <name> <starting balance> <Location x> <Location y>
	 * @param filename
	 * @param drivers
	 * @param passengers
	 */
	private static void extractDataFromFile(String filename, ArrayList<Driver> drivers,
			ArrayList<Passenger> passengers) {

		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				String[] args = line.split(" ");
				if (args[0].equals("driver")) {
					String name = args[1];
					String color = args[2];
					int year = Integer.parseInt(args[3]);
					String make = args[4];
					String model = args[5];
					int x = Integer.parseInt(args[6]);
					int y = Integer.parseInt(args[7]);
					
					drivers.add(new Driver(name, new Vehicle(color, year, make, model), new Location(x, y)));
				}
				else if (args[0].equals("passenger")) {
					String name = args[1];
					double balance = Double.parseDouble(args[2]);
					int x = Integer.parseInt(args[3]);
					int y = Integer.parseInt(args[4]);
					
					passengers.add(new Passenger(name, balance, new Location(x, y)));
				}
			}
			br.close();
			fr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * exports all data into JSON
	 * Each trip is summed into a JSON file named passengerName-trip.log
	 * Entire run is summarized into a "final-output.txt" with all the finalized data after every trip
	 * @param trips
	 * @param drivers
	 * @param passengers
	 */
	public static void handleJSON(ArrayList<Trip> trips,
			ArrayList<Driver> drivers, ArrayList<Passenger> passengers) {
		
		//get JSON logs for each trip
		for (Trip t : trips) {
			t.exportJSON(t.getPassenger().getName() + "-trip.log");
		}
		
		//create final output
		JSONObject obj = new JSONObject();
		obj.put("numTrips", trips.size());
		int transactions = 0;
		for (Trip t : trips) {
			if (t.getStatus() == CompletionStatus.COMPLETED) {
				transactions += 2;
			}
		}
		obj.put("numTransactions", transactions);
		
		for (Driver d : drivers) {
			JSONObject driver = new JSONObject();
			driver.put("balance", d.getBalance());
			driver.put("rating", d.getRating());
			driver.put("location", d.getLocation().toString());
			obj.put(d.getName(), driver);
		}
		
		for (Passenger p : passengers) {
			JSONObject passenger = new JSONObject();
			passenger.put("balance", p.getBalance());
			passenger.put("rating", p.getRating());
			passenger.put("location", p.getLocation().toString());
			obj.put(p.getName(), passenger);
		}
		
		try {
			FileWriter fw = new FileWriter(new File("final-output.txt"));
			fw.write(obj.toJSONString());
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Something went wrong while exporting JSON.");
			e.printStackTrace();
		}
		
	}
}
