package com.quanlyphongkhamvadatlich.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.quanlyphongkhamvadatlich.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.user = user;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAdmin() {
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ADMIN"))
                return true;
        }
        return false;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;

    //     UserPrincipal that = (UserPrincipal) o;
    //     return Objects.equals(this.id, that.id);
    // }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId());
    }

    public String getEmail() {
        return this.user.getEmail();
    }

    public User getUser() {
        return this.user;
    }
}
