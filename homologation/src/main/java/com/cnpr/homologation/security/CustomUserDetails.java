package com.cnpr.homologation.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cnpr.homologation.models.ViewUser;



public class CustomUserDetails implements UserDetails{

	 private final ViewUser user;
	 
	 public CustomUserDetails(ViewUser user2) {
	        this.user = user2;
	    }
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Update if using credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // Update if enabling/disabling accounts
    }

    public ViewUser getUser() {
        return user;
    }
}
