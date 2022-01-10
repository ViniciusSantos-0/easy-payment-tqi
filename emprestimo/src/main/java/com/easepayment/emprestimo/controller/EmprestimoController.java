package com.easepayment.emprestimo.controller;

import com.easepayment.emprestimo.dto.EmprestimoDetailsTdo;
import com.easepayment.emprestimo.dto.EmprestimoListResTdo;
import com.easepayment.emprestimo.dto.EmprestimoReTdo;
import com.easepayment.emprestimo.dto.MessageResTdo;
import com.easepayment.emprestimo.entity.Emprestimo;
import com.easepayment.emprestimo.repository.EmprestimoRepository;
import com.easepayment.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {

    @Autowired
    EmprestimoRepository repository;

    @Autowired
    private EmprestimoService service;

    @GetMapping
    public List<Emprestimo> list()throws IOException{
        System.out.println("recuperando dados do banco");
        return repository.findAll();
    }
    @PostMapping
    public void  save(@RequestBody Emprestimo emprestimo)throws IOException{
        System.out.println("Salvando"+ emprestimo.getId());
        repository.save(emprestimo);
    }

  /*  @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResTdo cadastrarEmprestimo(@RequestBody EmprestimoReTdo emprestimoRequestDTO) {
        return service.cadastrarEmprestimo(emprestimoRequestDTO);
    }

    @GetMapping(value = "/listagem")
    public List<EmprestimoListResTdo> listarEmprestimos(@RequestParam Long id) {
        return service.listagemEmprestimos(id);
    }

    @GetMapping("/listagem/{id}")
    public EmprestimoDetailsTdo findById(@PathVariable Long id) {
        return service.findById(id);
    }*/

}