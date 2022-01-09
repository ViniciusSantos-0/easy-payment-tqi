package com.easypayment.gerenciadorusers.security.jwt;

import com.easypayment.gerenciadorusers.dto.JwtDto;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtProvider {

    private static final String USERNAME_FIELD = "username";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String ROLES = "ROLE_USER";

    @Value("${com.easypayment.jwt.aws.identityPoolUrl}")
    private String identityPoolUrl;

    @Value("${com.easypayment.jwt.aws.expiration}")
    private int expiration;

    @Autowired
    ConfigurableJWTProcessor configurableJWTProcessor;

    private String getToken (String token) {
        return token.startsWith(BEARER)? token.substring(BEARER.length()) : token;
    }

    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        if(token != null){
            JWTClaimsSet claims = configurableJWTProcessor.process(getToken(token), null);
            validateToken(claims);
            String username = getUserName(claims);
            if(username != null){
                // TODO set roles
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(ROLES));
                User user = new User(username, "", authorities);
                return new JwtAuthenticator(authorities, user, claims);
            }

        }
        return null;
    }

    private String getUserName(JWTClaimsSet claims){
        return claims.getClaim(USERNAME_FIELD).toString();
    }

    private void validateToken (JWTClaimsSet claims) throws Exception {
        if(!claims.getIssuer().equals(identityPoolUrl))
            throw  new Exception("JWT no valid");
    }


    public String refreshToken(JwtDto jwtDto) throws ParseException, java.text.ParseException {
        try {
            Jwts.parser().setSigningKey(identityPoolUrl.getBytes()).parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String username = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim(ROLES);

            return Jwts.builder()
                    .setSubject(username)
                    .claim(ROLES, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.RS256, identityPoolUrl.getBytes())
                    .compact();
        }
        return null;
    }


}
