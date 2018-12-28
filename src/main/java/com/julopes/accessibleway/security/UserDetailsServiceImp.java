package com.julopes.accessibleway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julopes.accessibleway.repository.UserSecurityRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private UserSecurityRepository uSRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = uSRepo.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
				user.getAuthorities());
	}
}
