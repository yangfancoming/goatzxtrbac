package com.goat.zxt.security.utils;


import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private  Long expiration;

    /**
     * 创建token
     * @param username 用户名
     */
    public String generateToken(String username) {
        log.info("生成token ---> {}", JSON.toJSONString(username));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token
     */
    public  String getUserNameFromToken(String token){
        return getTokenBody(token).getSubject();
    }


    /**
     *  是否已过期
     * @param token
     */
    public  boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private  Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}

