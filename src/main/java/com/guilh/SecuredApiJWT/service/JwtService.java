package com.guilh.SecuredApiJWT.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private static final String secretekey = "f38e996a085ea909c1852dba33fb35dfdf042009ced9354a7829eb7f50a218dc";
    private final long Expiration = 1000 * 60 * 60;

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+Expiration))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token, String username){
        String tokenUser = extractUsername(token);
        return tokenUser.equals(username) && !isExpired(token);
    }

    private boolean isExpired(String token){
        Date exp = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return exp.before(new Date());
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretekey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
