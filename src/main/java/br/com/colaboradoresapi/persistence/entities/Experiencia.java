package br.com.colaboradoresapi.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Experiencia {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = {CascadeType.MERGE})
    private Cargo cargo;

    private String imagem;

    private String atividades;

    private String tecnologias;

}