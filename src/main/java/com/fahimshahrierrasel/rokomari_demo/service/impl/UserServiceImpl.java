package com.fahimshahrierrasel.rokomari_demo.service.impl;

import com.fahimshahrierrasel.rokomari_demo.exception.ResourceNotFoundException;
import com.fahimshahrierrasel.rokomari_demo.model.User;
import com.fahimshahrierrasel.rokomari_demo.repository.UserRepository;
import com.fahimshahrierrasel.rokomari_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username ) throws UsernameNotFoundException {
        User u = userRepository.findByUsername( username );
        return u;
    }

    public User findById( Long id ) throws AccessDeniedException {
        User u = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id));
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }
}