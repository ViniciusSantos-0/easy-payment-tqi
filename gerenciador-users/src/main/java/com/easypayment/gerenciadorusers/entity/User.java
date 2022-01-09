package com.easypayment.gerenciadorusers.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Getter
@Setter
public class User {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private double renda;
    private String senha;

    public User() {
    }

    public User(String nome, String email, String cpf, String rg, Endereco endereco, double renda, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.renda = renda;
        this.senha = senha;
    }

}
