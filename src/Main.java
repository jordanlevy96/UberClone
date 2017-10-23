import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		
		Finder finder = new Finder();
						
		extractDataFromFile("src/input_data.txt", drivers, passengers);
		
		for (Driver d : drivers) {
			d.setStatus(Status.AVAILABLE);
			finder.addDriver(d);
		}
		
		for (Passenger p : passengers) {
			TripManager.handleTrip(p, Location.generateRandomLocation(), finder).exportJSON(p.getName());;
		}
		
	
	}

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
}
