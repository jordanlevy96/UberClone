
public class Vehicle {
	private String make;
	private String model;
	private String color;
	private int year;
	
	public Vehicle(String make, String model, int year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public String toString() {
		return color + " " + Integer.toString(year) + " " + make + " " + model; 
	}
}
