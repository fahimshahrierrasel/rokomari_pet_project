package com.fahimshahrierrasel.rokomari_demo.security;

import com.fahimshahrierrasel.rokomari_demo.common.TimeProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenHelper {


    private String APP_NAME = "rokomari_demo";

    public SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private int EXPIRES_IN = 300;

    private  String TOKEN_TYPE = "token";
    private String AUTH_HEADER = "jwt_token";

    static final String AUDIENCE_WEB = "web";

    @Autowired
    TimeProvider timeProvider;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String generateToken(String username) {
        String audience = generateAudience();
        return Jwts.builder()
                .setIssuer( APP_NAME )
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(SECRET, SignatureAlgorithm.HS512)
                .compact();
    }

    private String generateAudience() {
        return AUDIENCE_WEB;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        long expiresIn = EXPIRES_IN;
        return new Date(timeProvider.now().getTime() + expiresIn * 1000);
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()));
    }

    public String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */

        String authHeader = getAuthHeaderFromHeader(request);
        String tokenType = getTokenType(request);

        if ( authHeader != null && tokenType.equals("Bearer") ) {
            return authHeader;
        }

        return null;
    }

    public String getTokenType( HttpServletRequest request) {
        return request.getHeader(TOKEN_TYPE);
    }

    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader(AUTH_HEADER);
    }

}