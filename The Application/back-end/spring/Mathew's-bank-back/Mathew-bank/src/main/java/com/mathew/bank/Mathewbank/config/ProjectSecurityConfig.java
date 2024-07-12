package com.mathew.bank.Mathewbank.config;

import com.mathew.bank.Mathewbank.filter.security.JWTTokenGeneratorFilter;
import com.mathew.bank.Mathewbank.filter.security.JWTTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //the token is generated here
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        //CSRF cookie
        final CookieCsrfTokenRepository cookieCsrfTokenRepo = new CookieCsrfTokenRepository();
        //make secure true when using only https
        cookieCsrfTokenRepo.setCookieCustomizer(responseCookieBuilder -> responseCookieBuilder.secure(true));

        // bellow line is used when you are using JWT tokens instead of jSession session keys but i put always because i guess CSRF token needs it
        http.
                logout((logout) -> logout.deleteCookies("Authorization", "JSESSIONID", "XSRF-TOKEN"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //now because we aare sending the JWT token to The UI Application in a Header
                //we need to manage it in the CORs config
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        //check CORs and CSRF in Previous commits
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        //the JWT will be sent to UI under Authorization header and XSR under X-XSRF-TOKEN
                        config.setExposedHeaders(List.of("Authorization", "X-XSRF-TOKEN"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))


                //temporarily disabling cross sight resource forgery
                //.csrf(AbstractHttpConfigurer::disable)
//                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/Sign-up/signup-user","/register","/user/getXSRfToken")
//                        //.csrfTokenRepository(new CookieCsrfTokenRepository())
//                        .csrfTokenRepository(cookieCsrfTokenRepo)
//                )
                .csrf(AbstractHttpConfigurer::disable)
                //.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

                //token generation after BasicAuthenticationFilter.class
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                //then position the verification filter
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        //only admin can use this rout
                        //user roles :- ROLE_admin ROLE_employee ROLE_manager ROLE_user
                        .requestMatchers(
                                "/admin/get_all_users",
                                "/admin/list_all_branches_with_manager",
                                "/admin/get_all_employees_by_a_role_name",
                                "/admin/get_all_employees",
                                "/admin/create_a_branch",
                                "/admin/manager_to_branch",
                                "/admin/get_all_employees_under",
                                "/admin/remove-manager-from-branch",
                                "/admin/remove-clerk-from-bank-by-admin",
                                "/admin/add-clerk-to-any-branch",
                                "/admin/branches_with_manager"
                        ).hasAnyRole("admin")

                        .requestMatchers(
                                "/admin/add_an_employee",
                                "/admin/remove_employee_permission",
                                "/admin/add_employee_permission",
                                "/employee/getAllApplications",
                                "/manager/**").hasAnyRole("manager")

                        .requestMatchers(
                                "/employee/getApplicationById",
                                "/employee/getUserApplicationByPhoneOrEmail",
                                "/employee/acceptApplication",
                                "/employee/rejectApplication").hasAnyRole("clerk")

                        .requestMatchers("/employee/employee-login").hasAnyRole("employee")
                        //any one who is authenticated can access /logout
                        .requestMatchers("/bankUser/login", "/user/getXSRfToken", "/logout").authenticated()
                        .requestMatchers("/bankUser/**").hasAnyRole("user")
                        //all the rest are open to public
                        .requestMatchers("/exposed/**","/Sign-up/signup-user").permitAll()
                )
                // redirect to /login if the user is not authenticated  Customizer.withDefaults() enables a security feature using the defaults provided by Spring Security
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }

    // to encode the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
