package com.example.picpay.services;

import com.example.picpay.domain.user.User;
import com.example.picpay.domain.user.UserType;
import com.example.picpay.dto.UserDTO;
import com.example.picpay.repositories.UserRepository;
import com.example.picpay.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    @Autowired
    private  UserRepository userRepository;

    private final UserMapper userMapper=new UserMapper();


    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType()== UserType.MERCHANT){
            throw  new Exception("Usuário do tipo logista não pode realizar transações");
        }

        if(sender.getBalance().compareTo(amount) <0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception{
       return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User createUser(UserDTO user){
        User newUser=this.userMapper.toUser(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }


}
