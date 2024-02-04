package com.driver.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;

@Repository
public class AirportRepository {

	public  HashMap<String,Airport> airport= new HashMap<>();
	public HashMap<Integer,Flight> flight= new HashMap<>();
	public HashMap<Integer,Passenger> passenger= new HashMap<>();
	public List<Airport> getAllAirports() {
		// TODO Auto-generated method stub
		
		 return new ArrayList<>(airport.values());
		
	}
	public List<Flight> getAllFlights() {
		// TODO Auto-generated method stub
		
		return new ArrayList<>(flight.values());
	}
	public int getNumberOfPassengers(String airportName) {
		// TODO Auto-generated method stub
		int numberOfPassengers = 0;
		for (Entry<String, Airport> entry : this.airport.entrySet()) {
		    if (entry.getKey().equals(airportName)) {
		        numberOfPassengers++;
		    }
		}
		return numberOfPassengers;
		
	}
	public int getNumberOfPassengersForFlight(Integer flightId) {
		// TODO Auto-generated method stub
		int numberOfPassengers = 0;
		for (Entry<Integer, Passenger> entry : this.passenger.entrySet()) {
		    if (entry.getKey().equals(flightId)) {
		        numberOfPassengers++;
		    }
		}
		return numberOfPassengers;
	}
	
	
	
			
}
