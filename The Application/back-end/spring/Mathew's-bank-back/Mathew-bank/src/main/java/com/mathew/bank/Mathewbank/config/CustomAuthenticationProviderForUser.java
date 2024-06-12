package com.mathew.bank.Mathewbank.config;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.service.UserInBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProviderForUser implements AuthenticationProvider {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //get credentials from login form
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        //sanity check
        if(username.isEmpty() || pwd.isEmpty()) return null;

        System.out.println(pwd);
        System.out.println(username);
        //just gets the name
        User customer = userRepository.getUserDetailsByUserName(username);

        //if the person exists
        if(customer != null){
            System.out.println(customer.getPassword());

            //check for a match
            if (passwordEncoder.matches(pwd, customer.getPassword())) {

                //then it is a match a number of springs granted authorities
                List<GrantedAuthority> authorities = new ArrayList<>();

                //loop through the users authorities and add each of them to simple granted authority
                try{
                    customer.getRoles().forEach(a -> authorities.add(new SimpleGrantedAuthority(a.getRole())));
                }catch (Exception e){
                    //user doesn't have permissions or roles = null
                    System.out.println(e.toString());
                    return null;
                }
                //final send the username password and auth as a token which will call the authenticate method in the ProviderManager
                // in this edit i wont store the password but a use id

                //this is so that i can get a global access to an authenticated users name and id
                username = username+","+customer.getId();

                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            }
            else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
        else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //tells spring that i want to support username password style of auth
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
