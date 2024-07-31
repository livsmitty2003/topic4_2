package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoJsonFiles {

	public static void saveToFile(String filename, Car car, boolean append)
	{
		PrintWriter pw;
		try
		{
			//Create a file File to write
			
		File file = new File(filename);
		FileWriter fw = new FileWriter(file, append);
		pw = new PrintWriter(fw);
		
		//Write Car as JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(car);
		pw.println(json);
		
		//Cleanup
		pw.close();
		}
		
		catch (IOException e) 
		{
			//Print Exceptions 
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Car> readFromFile(String filename) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		try
		{
			//Open the file File to read
			File file = new File(filename);
			Scanner s = new Scanner(file);
			
			//Create list of Cars by reading JSON file
			while(s.hasNext());
			
			//Read a string of JSON and convert to a Car
			String json = s.nextLine();
			ObjectMapper objectMapper = new ObjectMapper();
			Car car = objectMapper.readValue(json, Car.class);
			cars.add(car);
			
			//Cleanup
			s.close();
		}
		catch (IOException e)
		{
			//print exception
			e.printStackTrace();
		}
		//Return the list of cars 
		return cars;
		
	}
	
	public static void main(String[] args) throws IOException {
		// Create some cars using an array
		Car[] cars = new Car[5];
		cars[0] = new Car(2018, "Ford", "Mustang", 0, 1.5d);
		cars[1] = new Car(2018, "Chevy", "Blazer", 1000, 1.5d);
		cars[2] = new Car(2018, "Toyota", "Camry", 2000, 1.5d);
		cars[3] = new Car(2018, "Toyota", "Avalon", 90000, 2.5d);
		cars[4] = new Car(2018, "GMC", "Silverado", 100000, 3.5d);

		//Write the cars to a file as JSON
	for(int x=0; x<4; x++) 
	{
		saveToFile("out.json", cars[x], true);
	}
	
	//Read the Cars from the file and print out
	ArrayList<Car>carsList = readFromFile("out.json");
	for (Car car: carsList)
	{
		String text = Integer.toString(car.getYear()) + "," + car.getMake() + "," + car.getModel() + ", " + Integer.toString(car.getOdometer()) + ", " + Double.toString(car.getEngineLiters());
		System.out.println(text);
	}

	}
}
