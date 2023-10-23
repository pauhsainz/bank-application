package com.rabobank.bankapplication.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserWithAssociatedBankAccounts implements UserDetails {
    private final User user;
    private final UserDetails userDetails;

    public UserWithAssociatedBankAccounts(User user, UserDetails userDetails) {
        this.user = user;
        this.userDetails = userDetails;
    }

    public User getUser() {
        return user;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public Set<BankAccount> getBankAccounts() {
        return user.getBankAccounts();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

}
