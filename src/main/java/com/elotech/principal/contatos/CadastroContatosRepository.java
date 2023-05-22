package com.elotech.principal.contatos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroContatosRepository extends JpaRepository<CadastroContatos, Long> {
    Page<CadastroContatos> findAllByAtivoTrue(Pageable paginacao);
}
