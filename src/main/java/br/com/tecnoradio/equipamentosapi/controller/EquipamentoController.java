package br.com.tecnoradio.equipamentosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecnoradio.equipamentosapi.model.Equipamento;
import br.com.tecnoradio.equipamentosapi.model.Resposta;
import br.com.tecnoradio.equipamentosapi.service.EquipamentoService;

@RestController
@CrossOrigin(origins = "*")
public class EquipamentoController {
        @Autowired
    private EquipamentoService equipamentoService;

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody Equipamento equipamento){
        return equipamentoService.cadastrarAlterar(equipamento, "alterar");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Equipamento equipamento){
        return equipamentoService.cadastrarAlterar(equipamento, "cadastrar");
    }

    @GetMapping("/listar")
    public Iterable<Equipamento> listar(){
        return equipamentoService.listar();
    }

    @GetMapping("/")
    public String rota(){
        return "Api de produtos";
    }

    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<Resposta> remover(@PathVariable long codigo){
        return equipamentoService.remover(codigo);
    }
}
