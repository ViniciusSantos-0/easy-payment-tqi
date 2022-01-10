package com.easepayment.emprestimo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoListResTdo {

    private Long id;

    private Double valor;

    private Integer qtdParcelas;
}
