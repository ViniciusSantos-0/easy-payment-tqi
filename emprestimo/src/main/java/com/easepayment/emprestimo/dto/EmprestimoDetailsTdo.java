package com.easepayment.emprestimo.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDetailsTdo {

    private Long id;

    private Double valor;

    private LocalDate dataPrimeiraParcela;

    private Integer qtdParcelas;
}
