package br.com.colaboradoresapi.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competencia {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
}