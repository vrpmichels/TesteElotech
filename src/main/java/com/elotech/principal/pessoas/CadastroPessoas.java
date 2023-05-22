package com.elotech.principal.pessoas;

import com.elotech.dao.pessoas.DadosAtualizarCadastroPessoas;
import com.elotech.dao.pessoas.DadosCadastroPessoas;


import com.elotech.principal.contatos.CadastroContatos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CadastroPessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePessoa;
    private String cpf;
    private Date dataNascimento;
    private boolean ativo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<CadastroContatos> contatos;

    public CadastroPessoas(DadosCadastroPessoas dados) {

        this.ativo = true;
        this.nomePessoa = dados.nomePessoa();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();

    }

    public void atualizarinformacoes(DadosAtualizarCadastroPessoas dados) {
        if (dados.nomePessoa() != null) {
            this.nomePessoa = dados.nomePessoa();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.dataNascimento() != null) {
            this.dataNascimento = dados.dataNascimento();
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    public void setContatos(List<CadastroContatos> contatos) {
        this.contatos = contatos;
    }


}

