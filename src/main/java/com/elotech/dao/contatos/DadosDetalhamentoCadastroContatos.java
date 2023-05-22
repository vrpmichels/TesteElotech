package com.elotech.dao.contatos;

import com.elotech.principal.contatos.CadastroContatos;
public record DadosDetalhamentoCadastroContatos(

        Long id,
        String nomeContato,
        String telefone,
        String email
) {
    public DadosDetalhamentoCadastroContatos(CadastroContatos cadastrocontatos) {
        this(cadastrocontatos.getId(), cadastrocontatos.getNomeContato(), cadastrocontatos.getTelefone(), cadastrocontatos.getEmail());

    }
}