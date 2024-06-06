package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.cachCountryState.CountryCache;
import com.mathew.bank.Mathewbank.service.UnRegUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/exposed")
public class UserUnregistered {

    @Autowired
    private UnRegUserService unRegUserService;
    @Autowired
    private CountryCache countryCache;


    //uses make a back account application from here
    @PostMapping("/applyForAccount")
    public boolean registerUser(@RequestParam int branchId, @RequestBody UserApplicationDTO userApplicationDTO, HttpServletResponse response){

        System.out.println(userApplicationDTO.toString()+" "+ branchId);

        return unRegUserService.applyForABankAccount(branchId, userApplicationDTO,response);

    }

    @GetMapping("/getBranchByCountryAndState")
    public List<BranchDTO> getAllBranchesByRegion(@RequestParam String country, String state,HttpServletResponse response){

        System.out.println(country+" "+state);

        return this.unRegUserService.branchDTOList(country, state, response);
    }

    @GetMapping("/getAllCountriesThatHaveBranches")
    public Set<String> getAllCountries(){
        return countryCache.getAllCountries();
    }

    @GetMapping("/getAllStateBranchesInCountry")
    public LinkedHashSet<String> getAllBranchesInCountry(@RequestParam String country){
        return this.countryCache.getStatesByCountry(country);
    }
}
