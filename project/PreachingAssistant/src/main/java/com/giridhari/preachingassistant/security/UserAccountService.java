package com.giridhari.preachingassistant.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giridhari.preachingassistant.db.model.Type;
import com.giridhari.preachingassistant.db.model.UserAccount;
import com.giridhari.preachingassistant.db.repo.UserAccountRepo;

@Service
public class UserAccountService implements UserDetailsService {
	
	@Autowired
	private UserAccountRepo userAccountRepo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserAccount userAccount = userAccountRepo.findByUsername(username);
		if(userAccount == null) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new User(username, "****", getGrantedAuthorities(userAccount));
	}
	
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(UserAccount userAccount) {
		Collection<? extends GrantedAuthority> authorities;
		ArrayList<Type> authorityList = new ArrayList<>();
		authorityList.add(userAccount.getType());
		authorities = authorityList;
		return authorities;
	}
	
}
