package ro.msg.learning.shop.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.msg.learning.shop.entities.Customer;

import java.util.Collection;
import java.util.Collections;

public class CustomerDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Integer id;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;


    public CustomerDetails(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static CustomerDetails build(Customer customer) {
        return new CustomerDetails(
                customer.getId(),
                customer.getUsername(),
                customer.getEmailAddress(),
                customer.getPassword()
        );
    }


    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
