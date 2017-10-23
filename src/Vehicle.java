/**
 * Class representation of a Driver's car.
 * Really just some fluff to make everything seem more legit
 * @author Jordan
 *
 */
public class Vehicle {
	private String make;
	private String model;
	private String color;
	private int year;
	
	public Vehicle(String color, int year, String make, String model) {
		this.color = color;
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public String toString() {
		return color + " " + Integer.toString(year) + " " + make + " " + model; 
	}
}
