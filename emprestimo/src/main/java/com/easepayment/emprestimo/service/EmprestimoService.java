package com.easepayment.emprestimo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.easepayment.emprestimo.dto.EmprestimoListResTdo;
import com.easepayment.emprestimo.dto.EmprestimoReTdo;
import com.easepayment.emprestimo.dto.EmprestimoDetailsTdo;
import com.easepayment.emprestimo.dto.MessageResTdo;
import com.easepayment.emprestimo.entity.Emprestimo;
import com.easepayment.emprestimo.repository.EmprestimoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    public MessageResTdo cadastrarEmprestimo(EmprestimoReTdo emprestimoRequestDTO) {
        String msg;
        Emprestimo emprestimo = modelMapper.map(emprestimoRequestDTO, Emprestimo.class);
        LocalDate dataLimite = LocalDate.now().plusMonths(3);

        if ((emprestimoRequestDTO.getDataPrimeiraParcela().isBefore(dataLimite))
                || (emprestimoRequestDTO.getDataPrimeiraParcela().equals(dataLimite))) {

            if ((emprestimoRequestDTO.getQtdParcelas() <= 60) && (emprestimoRequestDTO.getQtdParcelas() >= 1)) {

                msg = "Emprestimo Criado com sucesso!";
                repository.save(emprestimo);

            } else
                msg = "A quantidade máxima de parcelas é 60";

        } else
            msg = "Data da primeira parcela não pode ser maior que 60 dias";

        return MessageResTdo.builder().mensagem(msg).build();

    }

    public List<EmprestimoListResTdo> listagemEmprestimos(Long id) {

       // List<Emprestimo> emprestimos = repository.findByCliente_Id(id);
        List<EmprestimoListResTdo> empListagem = new ArrayList<>();


        for (EmprestimoListResTdo e : empListagem ) {
            EmprestimoListResTdo emprestimo = modelMapper.map(e, EmprestimoListResTdo.class);
            empListagem.add(emprestimo);
        }

        return empListagem;
    }

    public EmprestimoDetailsTdo findById(Long id) {

        Emprestimo emprestimo = repository.findById(id).get();
        return modelMapper.map(emprestimo, EmprestimoDetailsTdo.class);

    }

}