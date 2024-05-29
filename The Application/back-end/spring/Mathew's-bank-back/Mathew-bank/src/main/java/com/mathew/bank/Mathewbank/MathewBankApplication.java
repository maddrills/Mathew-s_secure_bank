package com.mathew.bank.Mathewbank;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MathewBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathewBankApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(@Autowired AdminService adminService){
		return (args) -> {


//			adminService.addAnyEmployee(new Employee(
//					"BlaBlaBla",
//					null,
//					null
//			));

//			adminService.addEmployeeAndDetails(
//					"9449050762",
//					"mathew francis",
//					"mat@admin",
//					LocalDate.of(1998, 7, 21),
//					543234.50,
//					"12345",
//					new HashSet<>(Set.of("admin"))
//			);

//			Role role = adminService.findARoleInDb("admin");
//
//			if(role != null){
//				System.out.println(role.getRole());
//			}

		};
	}

}
