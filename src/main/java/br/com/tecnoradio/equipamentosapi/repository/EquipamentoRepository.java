package br.com.tecnoradio.equipamentosapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tecnoradio.equipamentosapi.model.Equipamento;

@Repository
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long>{
    
}
