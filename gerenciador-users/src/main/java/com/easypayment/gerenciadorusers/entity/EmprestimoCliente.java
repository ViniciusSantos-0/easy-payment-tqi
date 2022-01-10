package com.easypayment.gerenciadorusers.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmprestimoCliente {

    private long cod;
    private long idCliente;
    private long idEmprestimo;
}
