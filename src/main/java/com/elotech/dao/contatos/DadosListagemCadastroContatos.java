package com.elotech.dao.contatos;

import com.elotech.principal.contatos.CadastroContatos;
public record DadosListagemCadastroContatos(Long id, String nomeContato, String telefone, String email) {
    public DadosListagemCadastroContatos(CadastroContatos cadastrocontatos) {
        this(cadastrocontatos.getId(), cadastrocontatos.getNomeContato(), cadastrocontatos.getTelefone(), cadastrocontatos.getEmail());
    }

}
