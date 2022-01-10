package com.easepayment.emprestimo.controller;

import com.easepayment.emprestimo.entity.Emprestimo;
import com.easepayment.emprestimo.entity.Endereco;
import com.easepayment.emprestimo.repository.EmprestimoRepository;
import com.easepayment.emprestimo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    EnderecoRepository repository;

    @GetMapping
    public List<Endereco> list()throws IOException {
        System.out.println("recuperando dados do banco");
        return repository.findAll();
    }
    @PostMapping
    public void  save(@RequestBody Endereco endereco)throws IOException{
        System.out.println("Salvando"+ endereco.getCidade());
        repository.save(endereco);
    }
}
