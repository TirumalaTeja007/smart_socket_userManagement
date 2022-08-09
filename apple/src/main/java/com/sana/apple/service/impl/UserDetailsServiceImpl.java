package com.sana.apple.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sana.apple.model.Role;
import com.sana.apple.model.User;
import com.sana.apple.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) {
		Optional<User> user = userRepository.findByUserName(userName);
		if (!user.isPresent())
			throw new UsernameNotFoundException(userName);
		User userObj = user.get();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : userObj.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new org.springframework.security.core.userdetails.User(userObj.getUserName(),
				bCryptPasswordEncoder.encode(userObj.getPassword()), grantedAuthorities);
	}
}