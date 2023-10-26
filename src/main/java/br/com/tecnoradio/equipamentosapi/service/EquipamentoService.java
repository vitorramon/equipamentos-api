package br.com.tecnoradio.equipamentosapi.service;

import java.util.Optional;

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
    
    public ResponseEntity<?> cadastrarAlterar(Equipamento equipamento, String acao) {
        if (equipamento.getNome().isEmpty()) {
            Resposta resposta = new Resposta();
            resposta.setMensagem("O nome do Produto é obrigatório");
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        } else if (equipamento.getCliente().isEmpty()) {
            Resposta resposta = new Resposta();
            resposta.setMensagem("O nome do cliente é obrigatório");
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                // Verifica se já existe um equipamento com o mesmo nome no banco de dados
                if (equipamentoRepository.existsById(equipamento.getCodigo())) {
                    Resposta resposta = new Resposta();
                    resposta.setMensagem("Já existe um equipamento com esse nome");
                    return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(equipamentoRepository.save(equipamento), HttpStatus.CREATED);
            } else {
                // Verifica se o equipamento com o mesmo código já existe no banco de dados
                Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(equipamento.getCodigo());
                if (!equipamentoExistente.isPresent()) {
                    Resposta resposta = new Resposta();
                    resposta.setMensagem("O equipamento não existe no banco de dados");
                    return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
                }
                Equipamento equipamentoAtualizado = equipamentoExistente.get();
                // Atualiza os campos relevantes do equipamento existente com os novos valores
                equipamentoAtualizado.setNome(equipamento.getNome());
                equipamentoAtualizado.setDescricao(equipamento.getDescricao());
                equipamentoAtualizado.setSerialNumber(equipamento.getSerialNumber());
                equipamentoAtualizado.setInformacoesAdicionais(equipamento.getInformacoesAdicionais());
                equipamentoAtualizado.setCliente(equipamento.getCliente());
                equipamentoAtualizado.setEntreguePor(equipamento.getEntreguePor());
                equipamentoAtualizado.setRecebidoPor(equipamento.getRecebidoPor());
                return new ResponseEntity<>(equipamentoRepository.save(equipamentoAtualizado), HttpStatus.OK);
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
