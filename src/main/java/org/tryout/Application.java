package org.tryout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tryout.service.CellPhoneService;
import org.tryout.service.CellPhoneUsageService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner test(CellPhoneService cellPhoneService, CellPhoneUsageService cellPhoneUsageService) {
		return (args) -> {
			System.out.println(cellPhoneUsageService.getTotalMinutes());
			System.out.println(cellPhoneUsageService.getTotalData());
			System.out.println(cellPhoneUsageService.getAverageMinutes());
			System.out.println(cellPhoneUsageService.getAverageData());
			System.out.println(cellPhoneUsageService.listAll());
			System.out.println(cellPhoneUsageService.listCellPhoneUsagePerMonth());

		};
	}


}
