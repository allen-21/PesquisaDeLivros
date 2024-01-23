package com.Muchanga.PesquisaDeLivros.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.Muchanga.PesquisaDeLivros.user.models.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserDetails findByLogin(String login);
}
