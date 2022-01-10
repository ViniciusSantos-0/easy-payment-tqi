package com.easypayment.gerenciadorusers.controller;

import com.easypayment.gerenciadorusers.dto.JwtDto;
import com.easypayment.gerenciadorusers.dto.MessageDto;
import com.easypayment.gerenciadorusers.security.jwt.JwtProvider;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MainController {
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/hello-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MessageDto> helloAdmin(){
        return ResponseEntity.ok(new MessageDto("Ola ADM"));
    }

    @GetMapping("/hello-user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<MessageDto> hello(){
        return ResponseEntity.ok(new MessageDto("Ola Usuário"));
    }

    @SneakyThrows
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

}
