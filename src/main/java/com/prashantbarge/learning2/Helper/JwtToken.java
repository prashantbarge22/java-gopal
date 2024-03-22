package com.prashantbarge.learning2.Helper;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

public class JwtToken {

   SecretKey key = Jwts.SIG.HS512.key().build();

   public String createToken(String issuer,String payload, int expiration_after_hour){
     return Jwts.builder()
             .issuer(issuer)
             .subject(payload)
             .signWith(key)
             .expiration(Date.from(new Date().toInstant().plusMillis((long) expiration_after_hour *60*60*1000)))
             .compact();
   }

   public String verifyToken(String jwtToken){
      return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken).toString();
   }

}
