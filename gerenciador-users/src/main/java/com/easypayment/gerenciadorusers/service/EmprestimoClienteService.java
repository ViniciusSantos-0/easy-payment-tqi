package com.easypayment.gerenciadorusers.service;

import com.easypayment.gerenciadorusers.entity.EmprestimoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EmprestimoClienteService {

    @Autowired
    private WebClient webClient;

    public EmprestimoCliente  obterCodigo(Long codEmpretimo){
        Mono<EmprestimoCliente> monoCliente = this.webClient
                .method(HttpMethod.GET)
                .uri("/emprestimo", codEmpretimo)
                .retrieve()
                .bodyToMono(EmprestimoCliente.class);
        monoCliente.subscribe(e ->{
            System.out.println("Finalizou de verdade");
        });
        System.out.println("Finalizou.");
        return null;
    }
}
