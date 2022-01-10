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
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    private static final String USERNAME_FIELD = "username";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String rolesFiled = "cognito:groups";

    @Value("${com.easypayment.jwt.aws.identityPoolUrl}")
    private String identityPoolUrl;

    @Value("${com.easypayment.jwt.aws.expiration}")
    private int expiration;

    @Autowired
    ConfigurableJWTProcessor configurableJWTProcessor;



    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        if(token != null){
            JWTClaimsSet claims = configurableJWTProcessor.process(getToken(token), null);
            validateToken(claims);
            String username = getUserName(claims);
            if(username != null){
                // TODO set roles
                String roles = getRoles(claims);
                List<GrantedAuthority> authorities =
                        rolesToList(roles).stream().map(rol -> new SimpleGrantedAuthority(rol)).collect(Collectors.toList());
                log.info(authorities.toString());
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
            List<String> roles = (List<String>) claims.getClaim(rolesFiled);

            return Jwts.builder()
                    .setSubject(username)
                    .claim(rolesFiled, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.RS256, identityPoolUrl.getBytes())
                    .compact();
        }
        return null;
    }
    private String getToken (String token) {
        return token.startsWith(BEARER)? token.substring(BEARER.length()) : token;
    }

    private String getRoles(JWTClaimsSet claims) {
        return claims.getClaim(rolesFiled).toString();
    }

    private List<String> rolesToList(String roles) {
        String noSquare = roles.replace("[", "");
        noSquare = noSquare.replace("]", "");
        String noQuotes = noSquare.replace("\"", "");
        String noSpaces = noQuotes.replace(" ", "");
        return List.of(noSpaces.split(","));
    }
}
