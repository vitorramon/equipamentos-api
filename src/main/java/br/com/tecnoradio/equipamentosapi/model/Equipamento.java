package br.com.tecnoradio.equipamentosapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "equipamentos")
@Getter
@Setter
public class Equipamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String descricao;
    private String serialNumber;
    private String informacoesAdicionais;
    private String cliente;
    private String EntreguePor;
    private String RecebidoPor;

}
