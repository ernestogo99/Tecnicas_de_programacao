package com.example.picpay.repositories;

import com.example.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   UserDetails findUserByCpf(String cpf);

   Optional<User> findUserById(Long id);
}
