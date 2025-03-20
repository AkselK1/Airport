package com.cgi.airport;

import com.cgi.airport.exception.FlightAlreadyExistsException;
import com.cgi.airport.model.Flight;
import com.cgi.airport.repository.FlightRepository;
import com.cgi.airport.service.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.cgi.airport.service.FlightService;

import java.time.LocalDate;

@SpringBootApplication
public class AirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}

	// Adding fake data to my database
	@Bean
	public CommandLineRunner dataLoader(FlightRepository flightRepository) {
		return args -> {
			Flight flight1 = new Flight();
			flight1.setLeavingDate(LocalDate.of(2025, 3, 22));
			flight1.setReturningDate(LocalDate.of(2025, 3, 27));
			flight1.setDestination("Hawaii");
			flight1.setDeparture("Tallinn");
			flight1.setPrice(530);

			Flight flight2 = new Flight();
			flight2.setLeavingDate(LocalDate.of(2025, 3, 30));
			flight2.setReturningDate(LocalDate.of(2025, 3, 31));
			flight2.setDestination("Spain");
			flight2.setDeparture("Tallinn");
			flight2.setPrice(1200);

			Flight flight3 = new Flight();
			flight3.setLeavingDate(LocalDate.of(2025, 4, 10));
			flight3.setReturningDate(LocalDate.of(2025, 4, 15));
			flight3.setDestination("New York");
			flight3.setDeparture("Berlin");
			flight3.setPrice(800);

			Flight flight4 = new Flight();
			flight4.setLeavingDate(LocalDate.of(2025, 4, 20));
			flight4.setReturningDate(LocalDate.of(2025, 4, 25));
			flight4.setDestination("Tokyo");
			flight4.setDeparture("Moscow");
			flight4.setPrice(1500);


			/**
			flightRepository.save(flight1);
			flightRepository.save(flight2);
			flightRepository.save(flight3);
			flightRepository.save(flight4);
			 */
		};

	}

	@Bean
	public CommandLineRunner testFlightService(FlightService flightService) {
		return args -> {
			Flight flight1 = new Flight();
			flight1.setLeavingDate(LocalDate.of(2025, 3, 22));
			flight1.setReturningDate(LocalDate.of(2025, 3, 27));
			flight1.setDestination("Hawaii");
			flight1.setDeparture("Tallinn");
			flight1.setPrice(530);

			Flight flight2 = new Flight();
			flight2.setLeavingDate(LocalDate.of(2025, 3, 30));
			flight2.setReturningDate(LocalDate.of(2025, 3, 31));
			flight2.setDestination("Spain");
			flight2.setDeparture("Tallinn");
			flight2.setPrice(1200);

			Flight flight3 = new Flight();
			flight3.setLeavingDate(LocalDate.of(2025, 4, 10));
			flight3.setReturningDate(LocalDate.of(2025, 4, 15));
			flight3.setDestination("New York");
			flight3.setDeparture("Berlin");
			flight3.setPrice(800);

			Flight flight4 = new Flight();
			flight4.setLeavingDate(LocalDate.of(2025, 4, 20));
			flight4.setReturningDate(LocalDate.of(2025, 4, 25));
			flight4.setDestination("Tokyo");
			flight4.setDeparture("Moscow");
			flight4.setPrice(1500);

			try {
				Flight savedFlight1 = flightService.saveFlight(flight1);
				Flight savedFlight2 = flightService.saveFlight(flight2);
				Flight savedFlight3 = flightService.saveFlight(flight3);
				Flight savedFlight4 = flightService.saveFlight(flight4);
			} catch (FlightAlreadyExistsException e) {
				System.err.println("Could not save flight: " + e.getMessage());
			}
		};
	}

}
