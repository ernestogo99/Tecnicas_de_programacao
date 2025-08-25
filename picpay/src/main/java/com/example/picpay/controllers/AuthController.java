package com.example.picpay.controllers;

import com.example.picpay.domain.user.User;
import com.example.picpay.dto.AuthDTO;
import com.example.picpay.dto.LoginResponseDTO;
import com.example.picpay.dto.RegisterDTO;
import com.example.picpay.infra.security.TokenService;
import com.example.picpay.repositories.UserRepository;
import com.example.picpay.util.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication", description = "Endpoints related to user authentication and JWT generation.")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;



    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO){
        var userNamePassword=new UsernamePasswordAuthenticationToken(authDTO.cpf(),authDTO.password());
        var auth=this.authenticationManager.authenticate(userNamePassword);

        var token=tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO ){
        if(this.userRepository.findUserByCpf(registerDTO.cpf())!=null) return ResponseEntity.badRequest().build();
        User newuser=this.userMapper.toRegisterUser(registerDTO);
        this.userRepository.save(newuser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newuser);
    }

}
