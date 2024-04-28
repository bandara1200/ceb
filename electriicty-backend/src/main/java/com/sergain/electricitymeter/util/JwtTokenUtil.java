package com.sergain.electricitymeter.util;

import com.sergain.electricitymeter.model.User;
import com.sergain.electricitymeter.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
public class JwtTokenUtil {

    @Autowired
    private UserRepository userRepository;

    public User checkJwtToken(String token) {
        token = token.replace("Bearer ", "");
        log.info(token);
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i + 1);
        Claims claims = (Claims) Jwts.parser().parse(withoutSignature).getBody();
        String username = (String) claims.get("sub");
        User user = userRepository.findByUsername(username);
        return user;
    }
}
