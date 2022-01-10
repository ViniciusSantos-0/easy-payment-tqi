package com.easepayment.emprestimo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoReTdo {

    private Double valor;

    private LocalDate dataPrimeiraParcela;

    private Integer qtdParcelas;

}
