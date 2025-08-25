package com.example.picpay.util;

import com.example.picpay.domain.user.User;
import com.example.picpay.dto.RegisterDTO;
import com.example.picpay.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public User toUser(UserDTO userDTO){
        return User.builder().firstName(userDTO.firstName()).lastName(userDTO.lastName()).userType(userDTO.type())
                .cpf(userDTO.cpf())
                .email(userDTO.email())
                .balance(userDTO.balance())
                .password(userDTO.password())
                .build();
    }

    public UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getCpf(),
                user.getBalance(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType()
        );
    }

    public User toRegisterUser(RegisterDTO registerDTO){
        return User.builder().cpf(registerDTO.cpf()).password(registerDTO.password()).userType(registerDTO.type()).build();
    }

}
