package com.elotech.controller;


import com.elotech.dao.contatos.DadosCadastroContatos;
import com.elotech.principal.contatos.CadastroContatos;
import com.elotech.principal.pessoas.CadastroPessoas;
import com.elotech.principal.pessoas.CadastroPessoasRepository;
import com.elotech.dao.pessoas.DadosListagemCadastroPessoas;
import com.elotech.dao.pessoas.DadosAtualizarCadastroPessoas;
import com.elotech.dao.pessoas.DadosCadastroPessoas;
import com.elotech.dao.pessoas.DadosDetalhamentoCadastroPessoas;


import jakarta.transaction.Transactional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cadastropessoas")
public class CadastroPessoasController {

    @Autowired
    private CadastroPessoasRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPessoas dados) {
        CadastroPessoas pessoa = new CadastroPessoas(dados);
        repository.save(pessoa);

        List<CadastroContatos> contatos = dados.getContatos().stream().map(c -> {
            CadastroContatos contato = new CadastroContatos(new DadosCadastroContatos(c.getId(), c.getNomeContato(), c.getTelefone(), c.getEmail()));
            contato.setPessoa(pessoa);
            return contato;
        }).collect(Collectors.toList());

        pessoa.setContatos(contatos);
        repository.save(pessoa);
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemCadastroPessoas>> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCadastroPessoas::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCadastroPessoas dados){
        var pessoa = repository.getReferenceById(dados.id());
        pessoa.atualizarinformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCadastroPessoas(pessoa));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var pessoa = repository.getReferenceById(id);
        pessoa.excluir();

        return ResponseEntity.noContent().build();

    }

}
