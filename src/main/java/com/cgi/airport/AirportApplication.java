package com.cgi.airport;

import com.cgi.airport.exception.FlightAlreadyExistsException;
import com.cgi.airport.exception.SeatAlreadyExistsException;
import com.cgi.airport.model.Flight;
import com.cgi.airport.model.Seat;
import com.cgi.airport.service.FlightService;
import com.cgi.airport.service.SeatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class AirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner testFlightService(FlightService flightService) {
		return args -> {
			Flight flight1 = new Flight();
			flight1.setLeavingDate(LocalDate.of(2025, 3, 22));
			flight1.setDestinationLoc("Hawaii");
			flight1.setDepartureLoc("Tallinn");
			flight1.setPrice(530);

			Flight flight2 = new Flight();
			flight2.setLeavingDate(LocalDate.of(2025, 3, 30));
			flight2.setDestinationLoc("Spain");
			flight2.setDepartureLoc("Tallinn");
			flight2.setPrice(1200);

			Flight flight3 = new Flight();
			flight3.setLeavingDate(LocalDate.of(2025, 4, 10));
			flight3.setDestinationLoc("New York");
			flight3.setDepartureLoc("Berlin");
			flight3.setPrice(800);

			Flight flight4 = new Flight();
			flight4.setLeavingDate(LocalDate.of(2025, 4, 20));
			flight4.setDestinationLoc("Tokyo");
			flight4.setDepartureLoc("Moscow");
			flight4.setPrice(1500);

			try {
				Flight savedFlight1 = flightService.saveFlight(flight1);
				System.out.println("Flight1 saved : " + savedFlight1);
				Flight savedFlight2 = flightService.saveFlight(flight2);
				System.out.println("Flight2 saved: " + savedFlight2);
				Flight savedFlight3 = flightService.saveFlight(flight3);
				System.out.println("Flight3 saved: " + savedFlight3);
				Flight savedFlight4 = flightService.saveFlight(flight4);
				System.out.println("Flight4 saved: " + savedFlight4);
			} catch (FlightAlreadyExistsException e) {
				System.err.println("Could not save flight: " + e.getMessage());
			}
		};
	}

	@Bean
	public CommandLineRunner testSeatGenerator(SeatService seatService, FlightService flightService) {
		// Generates a map of taken seats, where key = row, value = list of column letters ('A', 'B', etc)
		HashMap<Integer, List<Character>> takenSeats = seatService.generateRandomTakenSeats();
		List<Flight> flights = flightService.getAllFlights();
		return args -> {
			for (Flight flight : flights) {
				List<Seat> seatsForFlights = seatService.generateSeatsForFlight(flight, takenSeats);
				for (Seat seat : seatsForFlights) {
					try {
						seatService.saveSeat(seat);
					} catch (SeatAlreadyExistsException e) {
						System.err.println("Could not save seat: " + e.getMessage());
					}
				}
			}
		};
	}

}
