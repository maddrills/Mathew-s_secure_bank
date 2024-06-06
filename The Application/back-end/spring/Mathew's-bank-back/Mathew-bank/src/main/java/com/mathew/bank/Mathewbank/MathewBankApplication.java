package com.mathew.bank.Mathewbank;


import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.cachCountryState.CountryCache;
import com.mathew.bank.Mathewbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class MathewBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathewBankApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(@Autowired AdminService adminService, CountryCache countryCache){
		return (args) -> {
			//adminService.createTheAdminAccount();

/*			countryCache.addACountryAndStateToCache("India","Karnataka");
			countryCache.addACountryAndStateToCache("India","Delhi");
			countryCache.addACountryAndStateToCache("America","NewYork");
			countryCache.addACountryAndStateToCache("America","California");
			countryCache.addACountryAndStateToCache("India","Andra");
			countryCache.addACountryAndStateToCache("India","TamilNadu");*/

			List<BranchDTO> branchDTOList = adminService.getAllBranches();
			System.out.println(branchDTOList);
			branchDTOList.forEach(branchDTO -> countryCache.addACountryAndStateToCache(
                    branchDTO.getCountry(),
                    branchDTO.getState()
            ));

			countryCache.getAllCountries().forEach( country -> {

				System.out.print("|Country| : "+country);
				System.out.print(" (State) : -> ");
				countryCache.getStatesByCountry(country).forEach(state -> System.out.print(state + ", "));
				}
			);
		};
	}

}
