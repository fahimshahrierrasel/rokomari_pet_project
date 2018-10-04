package com.fahimshahrierrasel.rokomari_demo.service;

import com.fahimshahrierrasel.rokomari_demo.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}