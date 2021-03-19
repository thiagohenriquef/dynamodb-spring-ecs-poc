package br.com.holelocations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class HoleLocationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoleLocationsApplication.class, args);
	}

}
