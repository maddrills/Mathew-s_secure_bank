package com.mathew.bank.Mathewbank.cachCountryState;

import java.util.*;

/**
 * singleton class for centralised value access  (initialization using <span style = "color : #00D100"> spring</span>) <br>
 * this cache holds all the country names as keys in a hash set and state as values<br>
 * the cache is created on initialization <br>
 * the cache can be updated everytime an admin adds a bank branch successfully<br>
 * <span style = "color : yellow" >WARNING :</span> <span span style = "color : #97a027"> using LinkedHashMap with a default bucket size of 16 </span>
 * */
public class CountryCache {

    //country name as key -> and a linked list of states corresponding to it
    private final Map<String, LinkedHashSet<String>> countriesAndStates = new LinkedHashMap<>();

    public void printThis(){
        System.out.println("Singleton is working");
    }

    //add a country and a state
    public boolean addACountryAndStateToCache(String country, String state){

        //check if country exists in map
        if(this.countriesAndStates.get(country) == null){
            //if it is then add a country and initialise a state list to it
            this.countriesAndStates.put(country, new LinkedHashSet<>(List.of(state)));
            return true;
        }
        //if not then add to it
        //first get the states from the country
        LinkedHashSet<String> states = this.countriesAndStates.get(country);
        //add a new state to it
        return states.add(state);
    }

    //Linked Hash map for O(n) iteration used here because the memory is very small a max of 193 buckets are only needed


    //get all state by country
    public LinkedHashSet<String> getStatesByCountry(String country){
        return this.countriesAndStates.get(country);
    }

    //get all countries
    public Set<String> getAllCountries(){
        return this.countriesAndStates.keySet();
    }

}
