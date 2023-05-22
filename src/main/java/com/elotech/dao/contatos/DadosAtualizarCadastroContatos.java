package com.elotech.dao.contatos;

import com.elotech.principal.contatos.CadastroContatos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCadastroContatos(
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