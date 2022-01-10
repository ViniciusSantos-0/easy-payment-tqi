package com.easypayment.gerenciadorusers.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private double renda;
    private String senha;


}
