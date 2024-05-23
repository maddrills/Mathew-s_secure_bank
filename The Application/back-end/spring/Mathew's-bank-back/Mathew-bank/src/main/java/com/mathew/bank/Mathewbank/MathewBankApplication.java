package com.mathew.bank.Mathewbank;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MathewBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathewBankApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(@Autowired AdminService adminService){
		return (args) -> {


			adminService.addAnyEmployee(new Employee(
					"Bla Bla Bla",
					null,
					null
			));
		};
	}

}
