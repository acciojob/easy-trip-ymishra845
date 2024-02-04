package com.driver.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repository.AirportRepository;

@Service
public class AirportService {

	@Autowired
	private AirportRepository airportRepository;

	public void addAirport(Airport airport) {
		// TODO Auto-generated method stub
		
		this.airportRepository.airport.put(airport.getAirportName(), airport);
		
	}

	public String getLargestAirportName() {
		// TODO Auto-generated method stub
		
		 List<Airport> airports = airportRepository.getAllAirports();
		if(this.airportRepository.airport.isEmpty()) {
			return null;
		}
		Airport largestAirport = airports.get(0);
		
		for(Airport ap:airports) {
			if (ap.getNoOfTerminals() > largestAirport.getNoOfTerminals() ||
                    (ap.getNoOfTerminals() == largestAirport.getNoOfTerminals() &&
                     ap.getAirportName().compareTo(largestAirport.getAirportName()) < 0)) {
                largestAirport = ap;
            }
		}
		
		 return largestAirport.getAirportName();
	}

	public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
		// TODO Auto-generated method stub
		 double shortestDuration = -1; 
		 
		 List<Flight> flights = this.airportRepository.getAllFlights();
		 for (Flight flight : flights) {
		        if (flight.getFromCity() == fromCity && flight.getToCity() == toCity) {
		            // Found a direct flight between the two cities
		            if (shortestDuration == -1 || flight.getDuration() < shortestDuration) {
		                // Update shortestDuration if it's the first direct flight or shorter than the current shortest
		                shortestDuration = flight.getDuration();
		            }
		        }
		    }
	
		       
		return shortestDuration;
	}

	public int getNumberOfPeopleOn(Date date, String airportName) {
		// TODO Auto-generated method stub
		int totalPeople=0;
		
		 for (Flight flight : airportRepository.getAllFlights()) {
	            if (flight.getFlightDate().equals(date) && airportRepository.airport.get(airportName).equals(airportName)) {
	                // For each matching flight, add the number of passengers to the total
	                totalPeople += this.airportRepository.getNumberOfPassengers(airportName);
	            }
	        }
		return totalPeople;
	}

	public int calculateFlightFare(Integer flightId) {
		// TODO Auto-generated method stub
		
		 int numberOfPassengers = airportRepository.getNumberOfPassengersForFlight(flightId);

	        // Calculate the flight fare based on the provided formula
	        return 3000 + numberOfPassengers * 50;
	}

	public void addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		
		this.airportRepository.passenger.put(passenger.getPassengerId(), passenger);
		
	}

	public void addFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		this.airportRepository.flight.put(flight.getFlightId(), flight);
		
	}

	public int calculateRevenueOfAFlight(Integer flightId) {
		// TODO Auto-generated method stub
		 int numberOfPassengers = airportRepository.getNumberOfPassengersForFlight(flightId);
		 int totalRevenue = 3000 + numberOfPassengers * 50;

		    return totalRevenue;
	}

	public String cancelATicket(Integer flightId, Integer passengerId) {
		// TODO Auto-generated method stub
		Flight flight = airportRepository.flight.get(flightId);
		
		if(!this.airportRepository.flight.containsKey(flightId) || !this.airportRepository.passenger.containsKey(passengerId)) {
			return "FAILURE";
		}
		this.airportRepository.passenger.remove(passengerId);
		return "SUCCESS";
	}

	
	public String bookATicket(Integer flightId, Integer passengerId) {
		
		// TODO Auto-generated method stub
		int numberOfPassengers = airportRepository.getNumberOfPassengersForFlight(flightId);

		Flight flight = airportRepository.flight.get(flightId);
		 
		 if(numberOfPassengers>flight.getMaxCapacity()|| this.airportRepository.passenger.containsKey(passengerId)) {
			 return "FAILURE";
		 }
		 
		
		return "SUCCESS";
	}

	public String getAirportNameFromFlightId(Integer flightId) {
		// TODO Auto-generated method stub
		Flight flight = airportRepository.flight.get(flightId);
	    
		City city=flight.getFromCity();
		Airport airport=airportRepository.airport.get(city);
		
		
		 
		
		return airport.getAirportName();
	}

	public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
		// TODO Auto-generated method stub
		int count=0;
		
		if(this.airportRepository.passenger.containsKey(passengerId)) {
			count++;
		}
		
		return count;
	}
	
	
	
	
	
}
