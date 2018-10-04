package com.fahimshahrierrasel.rokomari_demo.controller;

import com.fahimshahrierrasel.rokomari_demo.model.User;
import com.fahimshahrierrasel.rokomari_demo.model.UserTokenState;
import com.fahimshahrierrasel.rokomari_demo.security.TokenHelper;
import com.fahimshahrierrasel.rokomari_demo.security.auth.JwtAuthenticationRequest;
import com.fahimshahrierrasel.rokomari_demo.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
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
    private CustomUserDetailsService userDetailsService;


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
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
    }


}
