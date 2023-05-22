package com.elotech.dao.pessoas;

import com.elotech.principal.pessoas.CadastroPessoas;

import java.util.Date;
public record DadosDetalhamentoCadastroPessoas(
        Long id,
        String nomePessoa,
        String cpf,
        Date dataNascimento
) {
    public DadosDetalhamentoCadastroPessoas(CadastroPessoas cadastropessoa) {
        this(cadastropessoa.getId(), cadastropessoa.getNomePessoa(), cadastropessoa.getCpf(), cadastropessoa.getDataNascimento());

    }
}
