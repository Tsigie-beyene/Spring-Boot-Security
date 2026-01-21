package com.example.springsecurity.config;

import com.example.springsecurity.model.Customer;
import com.example.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankUserDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User details not found for the user" +username));

//        List<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority(customer.getRole()));
//        return new User(customer.getEmail(),customer.getPwd(),authorities);
        List<GrantedAuthority> authorities = customer.getAuthorities().stream().map(authority -> new
                SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
        return new User(customer.getEmail(), customer.getPwd(), authorities);

    }
}


