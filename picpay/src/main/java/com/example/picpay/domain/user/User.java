package com.example.picpay.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity(name="users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;


    private String firstName;
    private String lastName;

    private String fullname;

    @Column(name="cpf",nullable = false,unique = true)
    private String cpf;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name="balance")
    private BigDecimal balance;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(){

    }

    @Builder
    public User(String firstName, String lastName, String cpf, String email, String password, BigDecimal balance, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.userType = userType;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userType==UserType.MERCHANT) return List.of(new SimpleGrantedAuthority("MERCHANT"));
        else{
            return List.of(new SimpleGrantedAuthority("COMMON"));
        }
    }

    @Override
    public String getUsername() {
        return cpf;
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
