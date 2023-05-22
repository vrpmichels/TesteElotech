package com.elotech.controller;

import com.elotech.dao.contatos.DadosAtualizarCadastroContatos;
import com.elotech.dao.contatos.DadosCadastroContatos;
import com.elotech.dao.contatos.DadosDetalhamentoCadastroContatos;
import com.elotech.dao.contatos.DadosListagemCadastroContatos;
import com.elotech.principal.contatos.CadastroContatos;
import com.elotech.principal.contatos.CadastroContatosRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cadastrocontatos")
public class CadastroContatosController {

    @Autowired
    private CadastroContatosRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroContatos dados) {
        repository.save(new CadastroContatos(dados));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCadastroContatos>> listar(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCadastroContatos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarCadastroContatos dados){
        var contato = repository.getReferenceById(dados.id());
        contato.atualizarinformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCadastroContatos(contato));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var contato = repository.getReferenceById(id);
        contato.excluir();

        return ResponseEntity.noContent().build();

    }

}
