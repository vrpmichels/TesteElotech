package com.elotech.dao.pessoas;

import com.elotech.principal.pessoas.CadastroPessoas;

import java.util.Date;
public record DadosListagemCadastroPessoas(Long id, String nomePessoa, String cpf, Date dataNascimento) {
    public DadosListagemCadastroPessoas(CadastroPessoas cadastropessoa) {
        this(cadastropessoa.getId(), cadastropessoa.getNomePessoa(), cadastropessoa.getCpf(), cadastropessoa.getDataNascimento());
    }

}
