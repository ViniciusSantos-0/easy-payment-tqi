package com.easypayment.gerenciadorusers.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Data
@DynamoDBTable(tableName = "cliente")
public class Cliente {

    @DynamoDBHashKey
    private int id;
    @DynamoDBAttribute
    private String nome;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private String cpf;
    @DynamoDBAttribute
    private String rg;
    @DynamoDBAttribute
    private String endereco;
    @DynamoDBAttribute
    private double renda;
    @DynamoDBAttribute
    private String senha;

    public Cliente() {
    }

    public Cliente(int id, String nome, String email, String cpf, String rg, String endereco, double renda, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.renda = renda;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
