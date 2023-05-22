package com.elotech.dao.pessoas;


import com.elotech.principal.contatos.CadastroContatos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public record DadosAtualizarCadastroPessoas(

        Long id,
        @NotNull
        String cpf,
        @NotNull
        String nomePessoa,
        @NotNull
        @Past(message = "A data de nascimento não pode ser futura.")
        @DateTimeFormat(pattern = "yyyy-mm-dd")
        Date dataNascimento,
        List<CadastroContatos>contatos
) {
}
