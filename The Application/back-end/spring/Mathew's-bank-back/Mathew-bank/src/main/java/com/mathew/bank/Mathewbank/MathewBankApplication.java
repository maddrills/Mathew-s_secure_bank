package com.mathew.bank.Mathewbank;


import com.mathew.bank.Mathewbank.cachCountryState.CountryCache;
import com.mathew.bank.Mathewbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class MathewBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathewBankApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(@Autowired AdminService adminService, CountryCache countryCache){
		return (args) -> {
			//adminService.createTheAdminAccount();

			countryCache.printThis();
			countryCache.addACountryAndStateToCache("India","Karnataka");
			countryCache.addACountryAndStateToCache("India","Delhi");
			countryCache.addACountryAndStateToCache("America","NewYork");
			countryCache.addACountryAndStateToCache("America","California");
			countryCache.addACountryAndStateToCache("India","Andra");
			countryCache.addACountryAndStateToCache("India","TamilNadu");


			System.out.println("---states---");
			countryCache.getStatesByCountry("India").forEach(System.out::println);
			System.out.println("-");
			countryCache.getStatesByCountry("America").forEach(System.out::println);

			System.out.println("----Countries----");
			countryCache.getAllCountries().forEach(System.out::println);
		};
	}

}
