package br.com.colaboradoresapi.persistence.repositories;

import br.com.colaboradoresapi.persistence.entities.Colaborador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageableColaboradorRepository extends PagingAndSortingRepository<Colaborador, Integer> {

    Page<Colaborador> findByNameContaining(String infix, Pageable pageable);
    Page<Colaborador> findByCargoNameContaining(String infix, Pageable pageable);
    Page<Colaborador> findByCompetenciasNameContaining(String infix, Pageable pageable);
    Page<Colaborador> findByTimeNameContaining(String infix, Pageable pageable);
}