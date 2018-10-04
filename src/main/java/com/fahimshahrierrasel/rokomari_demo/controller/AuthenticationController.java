package com.fahimshahrierrasel.rokomari_demo.controller;

import com.fahimshahrierrasel.rokomari_demo.exception.ResourceNotFoundException;
import com.fahimshahrierrasel.rokomari_demo.model.Authority;
import com.fahimshahrierrasel.rokomari_demo.model.User;
import com.fahimshahrierrasel.rokomari_demo.model.UserRoleName;
import com.fahimshahrierrasel.rokomari_demo.model.UserTokenState;
import com.fahimshahrierrasel.rokomari_demo.repository.AuthorityRepository;
import com.fahimshahrierrasel.rokomari_demo.repository.UserRepository;
import com.fahimshahrierrasel.rokomari_demo.security.TokenHelper;
import com.fahimshahrierrasel.rokomari_demo.security.auth.JwtAuthenticationRequest;
import com.fahimshahrierrasel.rokomari_demo.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    TokenHelper tokenHelper;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthorityRepository authorityRepository;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        User user = (User)authentication.getPrincipal();
        String jws = tokenHelper.generateToken( user.getUsername());
        int expiresIn = tokenHelper.getExpiredIn();

        UserTokenState token = new UserTokenState("Bearer", jws, expiresIn);

        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token.getToken());
        headers.set("jwt_token", token.getJwt_token());
        headers.set("expires_in", String.valueOf(token.getExpires_in()));

        Map<String, String> resBody = new HashMap<>();
        resBody.put("status", "logged_in");
        resBody.put("first_name", user.getFirstName());
        resBody.put("email", user.getEmail());

        // Return the token
        return ResponseEntity.ok()
                .headers(headers)
                .body(resBody);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestHeader("token") String token, @Valid @RequestBody User user) {
        Map<String, String> resBody = new HashMap<>();

        if(token.equals("Bearer") && user != null) {

            Authority authority = authorityRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("ROLE", "id", 1));


            user.setAuthorities(Collections.singletonList(authority));

            user.setPassword(encoder.encode(user.getPassword()));

            User newUser = userRepository.save(user);

            resBody.put("first_name", newUser.getFirstName());
            resBody.put("last_name", newUser.getLastName());
            resBody.put("email", newUser.getEmail());
            resBody.put("mobile", newUser.getMobile());
            resBody.put("status", "success");
        } else {
            resBody.put("status", "not successful");
        }

        return ResponseEntity.ok()
                .body(resBody);
    }


}
