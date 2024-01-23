package com.Muchanga.PesquisaDeLivros.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Muchanga.PesquisaDeLivros.user.dtos.AuthenticationDTO;
import com.Muchanga.PesquisaDeLivros.user.dtos.LoginResponseDTO;
import com.Muchanga.PesquisaDeLivros.user.dtos.RegisterDTO;
import com.Muchanga.PesquisaDeLivros.user.infra.security.TokenService;
import com.Muchanga.PesquisaDeLivros.user.models.user.UserModel;
import com.Muchanga.PesquisaDeLivros.user.repository.UserRepository;

import javax.validation.Valid;


@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) !=null)return ResponseEntity.badRequest().build();

        String encryptePassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(null, data.login(), encryptePassword, data.role(), null);
        this.repository.save(newUser);
        return  ResponseEntity.ok().build();
    }
}
