package com.elotech.principal.pessoas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroPessoasRepository extends JpaRepository<CadastroPessoas, Long> {
    Page<CadastroPessoas> findAllByAtivoTrue(Pageable paginacao);
}

