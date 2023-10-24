package br.com.tecnoradio.equipamentosapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.tecnoradio.equipamentosapi.model.Equipamento;
import br.com.tecnoradio.equipamentosapi.model.Resposta;
import br.com.tecnoradio.equipamentosapi.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
    
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private Resposta resposta;

    public Iterable<Equipamento> listar(){
        return equipamentoRepository.findAll();
    }
    
        //Método para cadastrar ou alterar produtos
    public ResponseEntity<?> cadastrarAlterar(Equipamento equipamento, String acao){

        if(equipamento.getNome().equals("")){
            resposta.setMensagem("O nome do Produto é obrigatório");
            return new ResponseEntity<Resposta>(resposta, HttpStatus.BAD_REQUEST);
        } else if(equipamento.getCliente().equals("")){
            resposta.setMensagem("O nome do cliente é obrigatório");
            return new ResponseEntity<Resposta>(resposta, HttpStatus.BAD_REQUEST);
        } else {
           if(acao.equals("cadastrar")){
             return new ResponseEntity<Equipamento>(equipamentoRepository.save(equipamento), HttpStatus.CREATED);
           } else {
             return new ResponseEntity<Equipamento>(equipamentoRepository.save(equipamento), HttpStatus.OK);
           }
        }

    }

        // Método para remover produtos

        public ResponseEntity<Resposta> remover(long codigo){
            equipamentoRepository.deleteById(codigo);
    
            resposta.setMensagem("O produto foi removido com sucesso");
            return new ResponseEntity<Resposta>(resposta, HttpStatus.OK);
        }
}
