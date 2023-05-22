package com.elotech.principal.contatos;

import com.elotech.dao.contatos.DadosAtualizarCadastroContatos;
import com.elotech.dao.contatos.DadosCadastroContatos;


import com.elotech.principal.pessoas.CadastroPessoas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cadastrocontato")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CadastroContatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeContato;
    private String telefone;
    private String email;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private CadastroPessoas pessoa;


    public CadastroContatos(DadosCadastroContatos dados) {

        this.ativo = true;
        this.nomeContato = dados.nomeContato();
        this.telefone = dados.telefone();
        this.email = dados.email();

    }

    public void atualizarinformacoes(DadosAtualizarCadastroContatos dados) {
        if (dados.nomeContato() != null) {
            this.nomeContato = dados.nomeContato();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    public void setpessoaId(Long pessoaId) {
        this.pessoa = pessoa;
    }

    public void setPessoa(CadastroPessoas pessoa) {
        this.pessoa = pessoa;
    }
}
