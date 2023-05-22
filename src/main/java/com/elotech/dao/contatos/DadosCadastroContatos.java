package com.elotech.dao.contatos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContatos(

        Long id,
        @NotNull
        String nomeContato,
        @NotNull
        String telefone,
        @NotNull
        @Email
        String email

) {

}

