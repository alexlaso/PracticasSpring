package com.example.pokedex.pruebas.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic().and().cors()
				.disable().csrf().disable().headers().frameOptions().disable().and()
				.authorizeHttpRequests(requests -> requests.antMatchers("/swagger-ui.html").permitAll())
				.authorizeHttpRequests(requests -> requests.antMatchers("/swagger-ui/**").permitAll())
				.authorizeHttpRequests(requests -> requests.antMatchers("/v2/**").permitAll()).authorizeHttpRequests(
						requests -> requests.antMatchers("/v3/**").hasRole("ADMIN").anyRequest().authenticated());
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(userList());
	}

	@SuppressWarnings("deprecation")
	private List<UserDetails> userList() {
		List<UserDetails> list = new ArrayList<>();
		UserDetails user = User.withDefaultPasswordEncoder().username("user")
				.password(encoder().encode("password")).roles("USER").build();
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin")
				.password(encoder().encode("password")).roles("USER", "ADMIN").build();
		list.add(user);
		list.add(admin);
		return list;
	}

	/*
	 * @Override public void configure(AuthenticationManagerBuilder auth) throws
	 * Exception{ InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
	 * builder = auth.inMemoryAuthentication();
	 * 
	 * for (int i=0;i<userList().size();i++) {
	 * builder.withUser(userList().get(i).getUsername()).password(userList().get(i).
	 * getPassword()).authorities((GrantedAuthority[])
	 * userList().get(i).getAuthorities().toArray()); } }
	 */

}