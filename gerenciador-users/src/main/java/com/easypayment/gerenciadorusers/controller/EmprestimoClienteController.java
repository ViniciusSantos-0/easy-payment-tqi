package com.easypayment.gerenciadorusers.controller;

import com.easypayment.gerenciadorusers.entity.EmprestimoCliente;
import com.easypayment.gerenciadorusers.service.EmprestimoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoClienteController {

    @Autowired
    private EmprestimoClienteService emprestimoClienteService;

    @GetMapping
    public ResponseEntity<EmprestimoCliente> obteremprestimocliente(@PathVariable Long cod){
        EmprestimoCliente emprestimoCliente = this.emprestimoClienteService.obterCodigo(cod);
        return ResponseEntity.ok(emprestimoCliente);
    }
}
