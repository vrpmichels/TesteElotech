package com.elotech.controller;

import com.elotech.controller.CadastroPessoasController;
import com.elotech.dao.pessoas.DadosAtualizarCadastroPessoas;
import com.elotech.dao.pessoas.DadosCadastroPessoas;

import com.elotech.dao.pessoas.DadosListagemCadastroPessoas;

import com.elotech.principal.contatos.CadastroContatos;
import com.elotech.principal.pessoas.CadastroPessoas;
import com.elotech.principal.pessoas.CadastroPessoasRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@SpringBootTest
public class CadastroPessoasControllerTest {

    @InjectMocks
    private CadastroPessoasController cadastroPessoasController;

    @Mock
    private CadastroPessoasRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Test  //metodo garante que foi chamado o comando save 2 vezes
    public void testCadastrar() throws ParseException {

        List<CadastroContatos> listacontatos = new ArrayList<>();
        Date dataNascimento = dateFormat.parse("2020-05-05");
        DadosCadastroPessoas dados = new DadosCadastroPessoas(1L, "123456789", "Fulano de Tal", dataNascimento, listacontatos);

        cadastroPessoasController.cadastrar(dados);

        verify(repository, times(2)).save(any(CadastroPessoas.class));
    }

        @Test  //Esse teste configura uma simulação para o método findAllByAtivoTrue() do repositório, é um ResponseEntity com status HttpStatus.OK. Esse teste garante que o método listar() esteja retornando uma resposta válida.
        public void testListar() throws ParseException {

            Pageable paginacao = Pageable.unpaged();
            List<CadastroPessoas> pessoas = new ArrayList<>();
            List<CadastroContatos> listacontatos = new ArrayList<>();

            Date dataNascimento = dateFormat.parse("2020-05-05");

            CadastroPessoas pessoa1 = new CadastroPessoas(1L, "123456789", "Fulano de Tal", dataNascimento,true, listacontatos);
            CadastroPessoas pessoa2 = new CadastroPessoas(2L, "08910095903", "Jaoa", dataNascimento,true,  listacontatos);
            pessoas.add(pessoa1);
            pessoas.add(pessoa2);

            Page<CadastroPessoas> pagina = new PageImpl<>(pessoas);
            when(repository.findAllByAtivoTrue(paginacao)).thenReturn(pagina);


            ResponseEntity<Page<DadosListagemCadastroPessoas>> response = cadastroPessoasController.listar(paginacao);


            assertEquals(HttpStatus.OK, response.getStatusCode());

        }

    @Test  //Esse teste configura onde o método getReferenceById() do repositório é chamado com o ID obtido dos dados de atualização.
    // A simulação retorna um objeto CadastroPessoas para esse ID. Em seguida é chamado o metodo atualizar
    public void testAtualizar() throws ParseException {

        List<CadastroContatos> listacontatos = new ArrayList<>();

        Date dataNascimento = dateFormat.parse("2020-05-05");

        DadosAtualizarCadastroPessoas dados = new DadosAtualizarCadastroPessoas(1L, "123456789", "Fulano de Tal", dataNascimento, listacontatos);
        CadastroPessoas pessoa = new CadastroPessoas(2L, "08910095903", "Jaoa", dataNascimento,true,  listacontatos);
        when(repository.getReferenceById(dados.id())).thenReturn(pessoa);


        ResponseEntity response = cadastroPessoasController.atualizar(dados);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(pessoa).atualizarinformacoes(dados);
        verify(repository).save(pessoa);
    }


}
