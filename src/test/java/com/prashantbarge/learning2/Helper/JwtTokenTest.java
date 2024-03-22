package com.prashantbarge.learning2.Helper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JwtTokenTest {
    JwtToken jwtToken =  new JwtToken();
    @Test
    void createToken() {
       var token =  jwtToken.createToken("liquid","hello",1);
       System.out.println( jwtToken.verifyToken(token));

    }
}